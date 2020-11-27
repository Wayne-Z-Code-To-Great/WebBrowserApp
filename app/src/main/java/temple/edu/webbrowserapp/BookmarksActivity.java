package temple.edu.webbrowserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity implements BookmarkListAdapter.BookmarkAdapterInterface {
    ArrayList<String> titles;
    ArrayList<String> addresses;
    BookmarkListAdapter arrayAdapter;
    ListView bookmark;
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

    }
}