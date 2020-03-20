package com.nijo.creditclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class MainActivity extends AppCompatActivity {

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);

        this.webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath("/data/data" + getPackageName() + "/cache");
        webSettings.setSaveFormData(true);
        webSettings.setSavePassword(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl(getResources().getString(R.string.home_url));

        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(webView,true);
            setTaskDescription(
                    new ActivityManager.TaskDescription(
                            null, // Leave the default title.
                            BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_credit_club),
                            ContextCompat.getColor(this, R.color.ccAccent) // Leave the default color
                    )
            );
        } else {
            CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(this);
            cookieSyncManager.sync();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.reload();
    }

    @Override
    public void onStart() {
        super.onStart();
//        webView.loadUrl("https://clubs.moneysavingexpert.com/creditclub/login");  // if you want to directly load the login page --- buggy
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }




}

