package com.example.nasko.maptabs2;

/**
 * Created by agizdov on 4/7/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TabFragment2 extends Fragment {

    WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //inflate UI with fragment
        View v = inflater.inflate(R.layout.tab_fragment_2, container, false);
        mWebView = (WebView) v.findViewById(R.id.webView);

        // grab parameters from bundle
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            mWebView.loadUrl("http://google.com");
            /*String url = bundle.getString("passedKey");
            mWebView.loadUrl(url);
            TabFragment2 articleFrag = (TabFragment2) getFragmentManager().findFragmentById(R.id.linearlayout);;
            getFragmentManager().beginTransaction()
                    .detach(articleFrag)
                    .attach(articleFrag)
                    .commit(); */

        }
        else {
            mWebView.loadUrl("http://stackoverflow.com/questions/8748444/passing-strings-between-activities-in-android");
        }

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        //return view to Pager adapter
        return v;
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public void goBack() {
        mWebView.goBack();
    }

    public void displayLink (String url) {

        TabFragment2 articleFrag = (TabFragment2)
                getFragmentManager().findFragmentById(R.id.linearlayout);
        mWebView.loadUrl(url);
        getFragmentManager().beginTransaction()
                .detach(articleFrag)
                .attach(articleFrag)
                .commit();
    }




}
