package temple.edu.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;


public class PagerFragment extends Fragment {

    ArrayList<PageViewerFragment> fragments;
    ViewPager viewPager;
    getCurrentWebViewInterface parentActivity;

    public PagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PagerFragment.getCurrentWebViewInterface) {
            parentActivity= (PagerFragment.getCurrentWebViewInterface) context;
        } else {
            throw new RuntimeException("You must implement this fragment");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_pager, container, false);
        viewPager=(ViewPager) myView.findViewById(R.id.viewPager);
        if(savedInstanceState!=null) {
            fragments=(ArrayList<PageViewerFragment>)savedInstanceState.getSerializable("myViewFragments");
        } else {
            fragments=new ArrayList<>();
            fragments.add(new PageViewerFragment());
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                parentActivity.getCurrentWebView(position);
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        });
        ViewPager.OnPageChangeListener pageChangeListener=new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if(lastPosition>position){
//                    lastPosition-=2;
//                } else if(lastPosition<position) {
//                    lastPosition+=2;
//                }
//                if(lastPosition>=0 && lastPosition<fragments.size()) {
//                    parentActivity.getCurrentWebView(lastPosition);
//                } else {
//                    parentActivity.getCurrentWebView(position);
//                }
                parentActivity.getCurrentWebView(viewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        viewPager.addOnPageChangeListener(pageChangeListener);

        FragmentStateAdapter fsa=new MyFragmentStateAdapter(getActivity());
        return myView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("myViewFragments",fragments);
    }

    public void addTab() {
        fragments.add(new PageViewerFragment());
        Objects.requireNonNull(viewPager.getAdapter()).notifyDataSetChanged();
        viewPager.setCurrentItem(fragments.size()-1);
        parentActivity.getCurrentWebView(viewPager.getCurrentItem());
    }


    private class MyFragmentStateAdapter extends FragmentStateAdapter{
        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return null;
        }

        @Override
        public int getItemCount() {
            return 0;
        }
        public int getItemPosition(@NonNull Object object) {
            if(fragments.contains(object))
                return fragments.indexOf(object);
            else
                return -1;
        }
    }
    interface getCurrentWebViewInterface {
        void getCurrentWebView(int position);

    }
}