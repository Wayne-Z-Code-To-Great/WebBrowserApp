package temple.edu.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageControlFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "URL_text";
    buttonClickInterface parentActivity;

    // TODO: Rename and change types of parameters

    private String mURL_text;

    public PageControlFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof buttonClickInterface) {
            parentActivity= (buttonClickInterface) context;
        } else {
            throw new RuntimeException("You must implement this fragment");
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PageControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageControlFragment newInstance(String link) {
        PageControlFragment fragment = new PageControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, link);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mURL_text = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.fragment_page_control, container, false);
        EditText url_text=myView.findViewById(R.id.Url);
        ImageButton goButton=myView.findViewById(R.id.enter_Button);
        ImageButton backButton=myView.findViewById(R.id.back_Button);
        ImageButton forwardButton=myView.findViewById(R.id.forward_Button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.buttonClick(url_text.getText().toString());

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.buttonClick((1));
            }
        });
        forwardButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.buttonClick(2);
            }
        }));

        return myView;
    }

    interface buttonClickInterface {
        void buttonClick(String Url);
        void buttonClick(int i);
    }
}