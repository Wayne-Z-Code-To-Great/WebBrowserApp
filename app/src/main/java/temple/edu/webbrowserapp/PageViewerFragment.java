package temple.edu.webbrowserapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PageViewerFragment extends Fragment {
    String url;
    WebView webView;

    public PageViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_page_viewer, container, false);
        webView=(WebView) v.findViewById(R.id.webView);
        webView.canGoBack();
        webView.canGoForward();
        webView.setWebViewClient(new myWebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true); //enable javascript
        webView.loadUrl(url);
        return v;
    }



    private class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public void gettingUrl(String message) {
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
}