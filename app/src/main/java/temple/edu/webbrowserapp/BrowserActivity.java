package temple.edu.webbrowserapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.buttonClickInterface, PageViewerFragment.sentCurrentUrlInterface, BrowserControlFragment.imageButtonClickInterface,
PagerFragment.getCurrentWebViewInterface, PageListFragment.updateTitleToWebView{
    PageControlFragment controlFragment=new PageControlFragment();
    PagerFragment pagerFragment=new PagerFragment();
    BrowserControlFragment browserControlFragment=new BrowserControlFragment();
    PageViewerFragment currentFragment=new PageViewerFragment();
    PageListFragment pageListFragment=new PageListFragment();
    FragmentManager manager;
    int orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();

        Fragment temporary;



        if((temporary = manager.findFragmentById(R.id.page_control_container)) instanceof PageControlFragment) {
            controlFragment=(PageControlFragment) temporary;
        } else {
            controlFragment=new PageControlFragment();
            manager.beginTransaction().add(R.id.page_control_container, controlFragment).commit();
        }

        if((temporary=manager.findFragmentById(R.id.page_viewer_container)) instanceof PagerFragment) {
            pagerFragment=(PagerFragment) temporary;
        } else {
            pagerFragment=new PagerFragment();
            manager.beginTransaction().add(R.id.page_viewer_container, pagerFragment).commit();
        }

        if((temporary=manager.findFragmentById(R.id.browser_control_container)) instanceof BrowserControlFragment) {
            browserControlFragment=(BrowserControlFragment) temporary;
        } else {
            browserControlFragment=new BrowserControlFragment();
            manager.beginTransaction().add(R.id.browser_control_container, browserControlFragment).commit();
        }

        orientation=getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_LANDSCAPE) {
            if((temporary=manager.findFragmentById(R.id.page_list_container)) instanceof PageListFragment) {
                pageListFragment=(PageListFragment) temporary;
            } else {
                pageListFragment=new PageListFragment();
                manager.beginTransaction().add(R.id.page_list_container, pageListFragment).commit();
            }
        }



    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,"currentPagerFragment", pagerFragment);
        getSupportFragmentManager().putFragment(outState, "currentControlFragment", controlFragment);
        getSupportFragmentManager().putFragment(outState, "currentBrowserControlFragment", browserControlFragment);
        manager.beginTransaction().add(currentFragment, "fragment1");
        getSupportFragmentManager().putFragment(outState, "myCurrentFragment", currentFragment);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pagerFragment=(PagerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "currentPagerFragment");
        controlFragment=(PageControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "currentControlFragment");
        browserControlFragment=(BrowserControlFragment) getSupportFragmentManager().getFragment(savedInstanceState, "currentBrowserControlFragment");
        currentFragment=(PageViewerFragment) getSupportFragmentManager().getFragment(savedInstanceState, "fragment1");
    }

    @Override
    public void buttonClick(String Url) {
        currentFragment.gettingUrl(Url);
    }

    @Override
    public void buttonClick(int i) {
        currentFragment.forwardOrback(i);
    }

    @Override
    public void sentlink(String s) {
        controlFragment.displayCurrentUrl(s);
    }

    @Override
    public void sentTitle(String s) {
        this.setTitle(s);
    }

    @Override
    public void sentTitleToList(String s) {
        if(orientation==Configuration.ORIENTATION_LANDSCAPE)
            pageListFragment.webTitles.add(s);
    }

    @Override
    public void imageButtonClick() {
        pagerFragment.addTab();
    }

    @Override
    public void getCurrentWebView(int position) {
        currentFragment=pagerFragment.fragments.get(pagerFragment.viewPager.getCurrentItem());
        this.setTitle(pagerFragment.fragments.get(pagerFragment.viewPager.getCurrentItem()).currentTitle);
        controlFragment.displayCurrentUrl(pagerFragment.fragments.get(pagerFragment.viewPager.getCurrentItem()).currentUrl);
    }



    public void titleClickAction(int position) {
//        pageListFragment.webTitles.add(pagerFragment.fragments.get(pagerFragment.viewPager.getCurrentItem()).currentUrl);
    }


}