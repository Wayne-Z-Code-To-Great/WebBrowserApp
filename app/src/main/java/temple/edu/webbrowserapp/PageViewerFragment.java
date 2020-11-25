package temple.edu.webbrowserapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PageViewerFragment extends Fragment{
    WebView webView;
    private String currentUrl;
    private String currentTitle;
    sentCurrentUrlInterface parentActivity;
    public PageViewerFragment() {
        // Required empty public constructor
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof sentCurrentUrlInterface) {
            parentActivity=(sentCurrentUrlInterface) context;
        } else {
            throw new RuntimeException("You must implement this fragment");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_page_viewer, container, false);
        webView= v.findViewById(R.id.webView);
        webView.canGoBack();
        webView.canGoForward();
        webView.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                currentUrl=url;
            }
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                currentTitle=view.getTitle();
            }
        });
//        currentTitle=webView.getTitle();
//        currentUrl=webView.getUrl();
//        parentActivity.sentTitle(currentTitle);
//        parentActivity.sentlink(currentUrl);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true); //enable javascript
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        if(savedInstanceState!=null) {
            webView.restoreState(savedInstanceState);
            savedInstanceState.getString("url", currentUrl);
            savedInstanceState.getString("title", currentTitle);
        }
        return v;
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
        outState.putString("url", currentUrl);
        outState.putString("title", currentTitle);
    }


    public void gettingUrl(String message) {
        currentUrl=message;
        webView.loadUrl(currentUrl);
        parentActivity.sentTitle(currentTitle);
        parentActivity.sentlink(currentUrl);
    }

    public void forward() {
        webView.goForward();
        currentUrl=webView.getUrl();
        parentActivity.sentTitle(currentTitle);
        parentActivity.sentlink(currentUrl);
    }

    public void back() {
        webView.goBack();
        currentUrl=webView.getUrl();
        parentActivity.sentTitle(currentTitle);
        parentActivity.sentlink(currentUrl);
    }



    public String getCurrentTitle() {
        return webView.getTitle();
    }

    public String getCurrentUrl() {
        return webView.getUrl();
    }

    interface sentCurrentUrlInterface {
        void sentlink(String s);
        void sentTitle(String s);
    }

}