package com.android.gank_demo.ui.module.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.gank_demo.R;
import com.android.gank_demo.frame.FrameActivity;
import com.android.gank_demo.model.entity.GankDataModel;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.gank_demo.model.annotation.IntentParameter.INTENT_PARMS_WEB_DATA;

public class WebActivity extends FrameActivity {

    @BindView(R.id.toolbar_actionbar)
    Toolbar mToolbarActionbar;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.web_view)
    WebView mWebView;

    public static Intent getCallToWebBrowserIntent(@NonNull Context context, @NonNull GankDataModel gankDataModel) {
        Intent callingIntent = new Intent(context, WebActivity.class);
        callingIntent.putExtra(INTENT_PARMS_WEB_DATA, gankDataModel);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        GankDataModel model = getIntent().getParcelableExtra(INTENT_PARMS_WEB_DATA);
        initializeView();
        initializeViewSettings();
        initializeData(model);
    }

    private void initializeView() {
        //支持获取手势焦点，输入用户名、密码或其他
        mWebView.requestFocusFromTouch();
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    mProgressBar.setProgress(progress);
                } else {
                    mProgressBar.setProgress(100);
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            //获取Web页中的title用来设置自己界面中的title
            //当加载出错的时候，比如无网络，这时onReceiveTitle中获取的标题为 找不到该网页,
            //因此建议当触发onReceiveError时，不要使用获取到的title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                setTitle(title);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initializeViewSettings() {
        WebSettings webSettings = mWebView.getSettings();
        // 支持js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);  // 支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");// 设置编码格式
    }


    private void initializeData(GankDataModel model) {
        if (model == null) return;
        mWebView.loadUrl(model.getUrl());
    }
}
