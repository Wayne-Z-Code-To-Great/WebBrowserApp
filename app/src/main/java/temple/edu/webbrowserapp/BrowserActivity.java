package temple.edu.webbrowserapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.buttonClickInterface, PageViewerFragment.sentCurrentUrlInterface, BrowserControlFragment.imageButtonClickInterface,
PagerFragment.PagerInterface, PageListFragment.PageListInterface {


    PageControlFragment controlFragment;
    PagerFragment pagerFragment;
    BrowserControlFragment browserControlFragment;
    PageListFragment pageListFragment;
    ArrayList<PageViewerFragment> pages;


    FragmentManager manager;
    private final String PAGE_KEY= "pages";
    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();

        Fragment temporary;

        if(savedInstanceState!=null)
            pages=(ArrayList) savedInstanceState.getSerializable(PAGE_KEY);
        else
            pages=new ArrayList<>();

        orientation=getResources().getConfiguration().orientation;



        if((temporary = manager.findFragmentById(R.id.page_control_container)) instanceof PageControlFragment) {
            controlFragment=(PageControlFragment) temporary;
        } else {
            controlFragment=new PageControlFragment();
            manager.beginTransaction().add(R.id.page_control_container, controlFragment).commit();
        }


        if((temporary=manager.findFragmentById(R.id.browser_control_container)) instanceof BrowserControlFragment) {
            browserControlFragment=(BrowserControlFragment) temporary;
        } else {
            browserControlFragment=new BrowserControlFragment();
            manager.beginTransaction().add(R.id.browser_control_container, browserControlFragment).commit();
        }


        if((temporary=manager.findFragmentById(R.id.page_viewer_container)) instanceof PagerFragment) {
            pagerFragment=(PagerFragment) temporary;
        } else {
            pagerFragment=pagerFragment.newInstance(pages);
            manager.beginTransaction().add(R.id.page_viewer_container, pagerFragment).commit();
        }


        if(orientation== Configuration.ORIENTATION_LANDSCAPE) {
            if((temporary=manager.findFragmentById(R.id.page_list_container)) instanceof PageListFragment) {
                pageListFragment=(PageListFragment) temporary;
            } else {
                pageListFragment=PageListFragment.newInstance(pages);
                manager.beginTransaction().add(R.id.page_list_container, pageListFragment).commit();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PAGE_KEY, pages);
    }


    @Override
    public void buttonClick(String Url) {
        if(pages.size()>0)
            pagerFragment.go(Url);
        else {
            pages.add(new PageViewerFragment());
            notifyWebsitesChanged();
            pagerFragment.showTab(pages.size()-1);
        }
    }

    @Override
    public void buttonClickforward() {
        pagerFragment.forward();
    }

    @Override
    public void buttonClickback() {
        pagerFragment.back();
    }

    @Override
    public void sentlink(String s) {
        controlFragment.displayCurrentUrl(s);
    }

    @Override
    public void sentTitle(String s) {
        this.setTitle(s);
    }

    private void notifyWebsitesChanged() {
        pagerFragment.notifyWebsitesChanged();
        if(orientation==Configuration.ORIENTATION_LANDSCAPE)
            pageListFragment.notifyWebsitesChanged();
    }


    @Override
    public void imageButtonClick() {
        pages.add(new PageViewerFragment());
        notifyWebsitesChanged();
        pagerFragment.showTab(pages.size()-1);
    }

    @Override
    public void titleClickAction(int position) {
        pagerFragment.showTab(position);
    }


    @Override
    public void updateUrl(String url) {
        controlFragment.displayCurrentUrl(url);
    }

    @Override
    public void updateTitle(String title) {
        this.setTitle(title);
    }
}