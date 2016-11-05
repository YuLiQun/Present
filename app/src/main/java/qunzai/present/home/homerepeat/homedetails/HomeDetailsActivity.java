package qunzai.present.home.homerepeat.homedetails;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;

/**
 * Created by dllo on 16/11/5.
 */
public class HomeDetailsActivity extends BaseActivity {

    private WebView web;
    private String webUrl;


    @Override
    protected int getLayout() {
        return R.layout.activity_home_details;
    }

    @Override
    protected void initView() {
        web = bindView(R.id.web_home_details);

    }

    @Override
    protected void initData() {
        webIntent();
    }

    private void webIntent() {

        Intent intent = getIntent();
        webUrl = intent.getStringExtra("content");

        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(webUrl);
//        web.setWebViewClient( new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.d("ppppp", webUrl);
//
//                return false;
//            }
//
//
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                super.onLoadResource(view, url);
//            }
//        });



    }
}
