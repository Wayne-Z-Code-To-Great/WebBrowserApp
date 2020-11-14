package temple.edu.webbrowserapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PageListFragment extends Fragment {

    ArrayList<PageViewerFragment> webTitles;
    ListView listView;
    public PageListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_page_list, container, false);
        listView=(ListView) myView.findViewById(R.id.webTitlesList);

        return myView;
    }

    static class CustomPagerAdatper extends PagerAdapter {
        private final ArrayList<String> pages;

        CustomPagerAdatper(ArrayList<String> pages) {
            this.pages = pages;
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }
}