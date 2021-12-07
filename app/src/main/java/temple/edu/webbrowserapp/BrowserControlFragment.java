package temple.edu.webbrowserapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class BrowserControlFragment extends Fragment {

    imageButtonClickInterface parentActivity;
    ImageButton imageButton;
    ImageButton saveBookmarkButton;
    ImageButton loadBookmarkButton;
    public BrowserControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof imageButtonClickInterface) {
            parentActivity=(imageButtonClickInterface) context;
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
        View myView=inflater.inflate(R.layout.fragment_browser_control, container, false);
        imageButton= myView.findViewById(R.id.addTapButton);
        saveBookmarkButton=myView.findViewById(R.id.saveBookmarkButton);
        loadBookmarkButton=myView.findViewById(R.id.loadBookmarkButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.imageButtonClick();
            }
        });
        saveBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.saveBookmark();
            }
        });
        loadBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.openBookmark();
            }
        });
        return myView;
    }

    interface imageButtonClickInterface {
        void imageButtonClick();
        void saveBookmark();
        void openBookmark();
    }
}