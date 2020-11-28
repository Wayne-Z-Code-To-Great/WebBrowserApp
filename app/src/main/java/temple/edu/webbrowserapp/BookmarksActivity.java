package temple.edu.webbrowserapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity implements BookmarkListAdapter.BookmarkAdapterInterface {
    ArrayList<String> titles;
    ArrayList<String> addresses;
    BookmarkListAdapter arrayAdapter;
    ListView bookmark;
    Button closeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        Intent receivedIntent=getIntent();

        titles=receivedIntent.getStringArrayListExtra("bookmarktitles");
        addresses=receivedIntent.getStringArrayListExtra("bookmarkaddresses");

        bookmark=findViewById(R.id.bookmark_titles_list);
        arrayAdapter=new BookmarkListAdapter(this, titles);
        bookmark.setAdapter(arrayAdapter);
        closeButton=findViewById(R.id.bookmark_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void bookmarkSelected(int i) {
        String url=addresses.get(i);
        if(!url.contains("https"))
            url= titles.get(i);
        Intent sentIntent=new Intent();
        sentIntent.putExtra("sentBackAddress", url);
        setResult(RESULT_OK, sentIntent);
        finish();
    }

    @Override
    public void bookmarkdeleted(int i) {
        Dialog determine = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Delete "+titles.get(i)+"?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        titles.remove(i);
                        addresses.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        SharedPreferences sharedPreferences = getSharedPreferences("SavedData", MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        Gson gson=new Gson();
                        String title_json=gson.toJson(addresses);
                        String address_json=gson.toJson(titles);
                        editor.putString("titles_bookmark_set", title_json);
                        editor.putString("addresses_bookmark_set", address_json);
                        editor.commit();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}