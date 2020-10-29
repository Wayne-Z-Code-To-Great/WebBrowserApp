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

public class PageViewerFragment extends Fragment {
    WebView webView;
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
        webView=(WebView) v.findViewById(R.id.webView);
        webView.canGoBack();
        webView.canGoForward();
        webView.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                parentActivity.sentlink(url);
            }
        });
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true); //enable javascript
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        parentActivity.sentlink(webView.getUrl());
        webView.loadUrl("https:temple.edu");

        if(savedInstanceState!=null) {
            webView.restoreState(savedInstanceState);
        }
        return v;
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }


    public void gettingUrl(String message) {
        String url;
        url=message;
        webView.loadUrl(url);
    }

    public void forwardOrback(int condition) {
        if(condition==1) {
            webView.goBack();
        } else {
            webView.goForward();
        }
    }

    interface sentCurrentUrlInterface {
        void sentlink(String s);
    }

}