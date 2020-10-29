package temple.edu.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements PageControlFragment.buttonClickInterface, PageViewerFragment.sentCurrentUrlInterface{
    PageControlFragment controlFragment=new PageControlFragment();
    PageViewerFragment viewerFragment=new PageViewerFragment();
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

        if((temporary=manager.findFragmentById(R.id.page_viewer_container)) instanceof PageViewerFragment) {
            viewerFragment=(PageViewerFragment) temporary;
        } else {
            viewerFragment=new PageViewerFragment();
            manager.beginTransaction().add(R.id.page_viewer_container, viewerFragment).commit();
        }
    }

    @Override
    public void buttonClick(String Url) {
        viewerFragment.gettingUrl(Url);
    }

    @Override
    public void buttonClick(int i) {
        viewerFragment.forwardOrback(i);
    }

    @Override
    public void sentlink(String s) {
        controlFragment.displayCurrentUrl(s);
    }
}