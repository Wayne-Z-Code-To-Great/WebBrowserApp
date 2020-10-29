package temple.edu.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class BrowserActivity extends AppCompatActivity implements  PageControlFragment.buttonClickInterface{
    PageControlFragment controlFragment=new PageControlFragment();
    PageViewerFragment viewerFragment=new PageViewerFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.page_control_container, controlFragment, "Url_text");
        transaction.addToBackStack(null);
        transaction.commit();
        FragmentTransaction transaction2=manager.beginTransaction();
        transaction2.add(R.id.page_viewer_container, viewerFragment);
        transaction2.addToBackStack(null);
        transaction2.commit();
    }

    @Override
    public void buttonClick(String Url) {
        viewerFragment.gettingUrl(Url);
    }

    @Override
    public void buttonClick(int i) {
        viewerFragment.forwardOrback(i);
    }
}