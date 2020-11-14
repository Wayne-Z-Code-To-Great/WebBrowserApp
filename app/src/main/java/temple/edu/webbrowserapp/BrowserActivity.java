package temple.edu.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.ArrayList;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.buttonClickInterface, PageViewerFragment.sentCurrentUrlInterface, BrowserControlFragment.imageButtonClickInterface,
PagerFragment.getCurrentWebViewInterface{
    PageControlFragment controlFragment=new PageControlFragment();
    PagerFragment pagerFragment=new PagerFragment();
    BrowserControlFragment browserControlFragment=new BrowserControlFragment();
    ArrayList<PageViewerFragment> fragments;
    PageViewerFragment currentFragment;
    FragmentManager manager;
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
    }

    @Override
    public void buttonClick(String Url) {
        currentFragment.gettingUrl(Url);
    }

    @Override
    public void buttonClick(int i) {
        currentFragment.forwardOrback(i);
        pagerFragment.notifyAll();
    }

    @Override
    public void sentlink(String s) {
        controlFragment.displayCurrentUrl(s);
    }

    @Override
    public void imageButtonClick() {
        pagerFragment.addTab();
    }

    @Override
    public void getCurrentWebView(int position) {
        currentFragment=pagerFragment.fragments.get(position);
        this.setTitle(currentFragment.currentTitle);
        controlFragment.displayCurrentUrl(currentFragment.currentUrl);
    }
}