package temple.edu.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PageListFragment extends Fragment {

    ArrayList<String> webTitles;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    updateTitleToWebView parentActivity;
    public PageListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof updateTitleToWebView) {
            parentActivity = (updateTitleToWebView) context;
        } else {
            throw new RuntimeException("You must implement ButtonClickInterface to attach this fragment");
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_page_list, container, false);
        listView=(ListView) myView.findViewById(R.id.webTitlesList);
        if(savedInstanceState!=null) {
            webTitles=(ArrayList<String>) savedInstanceState.getSerializable("Title_list");
            arrayAdapter=new ArrayAdapter<String>(getActivity(), R.layout.simple_textview, webTitles);
            listView.setAdapter(arrayAdapter);
        } else {
            webTitles=new ArrayList<String>();
            arrayAdapter=new ArrayAdapter<String>(getActivity(), R.layout.simple_textview, webTitles);
            listView.setAdapter(arrayAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentActivity.titleClickAction(position);
            }
        });
        return myView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Title_list", webTitles);

    }

    interface updateTitleToWebView {
        void titleClickAction(int position);
    }

    public void addTitleToList(String title) {
        webTitles.add(title);
    }
}