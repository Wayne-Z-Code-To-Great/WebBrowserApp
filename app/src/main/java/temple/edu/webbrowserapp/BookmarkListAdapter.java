package temple.edu.webbrowserapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BookmarkListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<String> titles;
    BookmarkAdapterInterface parentActivity;

    public BookmarkListAdapter(@NonNull Context context, ArrayList<String> titles) {
        super(context, 0, titles);
        this.context=context;
        this.titles=titles;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public String getItem(int i) {
        return titles.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item=getItem(position);
        parentActivity=(BookmarkAdapterInterface) this.context;
        if(convertView==null) {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.adapter_bookmark_list, parent, false);
        }
        TextView title_txt= convertView.findViewById(R.id.bookmarkTitle);
        ImageButton title_button= convertView.findViewById(R.id.deleteButton);
        title_txt.setText(item);

        title_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.bookmarkSelected(position);
            }
        });
        title_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.bookmarkdeleted(position);
            }
        });

        return convertView;
    }

    interface BookmarkAdapterInterface {
        void bookmarkSelected(int i);
        void bookmarkdeleted(int i);
    }
}
