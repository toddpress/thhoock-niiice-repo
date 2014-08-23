package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by tylerroach on 8/23/14.
 */
public class WebViewActivity extends Activity{

    private WebView webView;
    private String mUrl;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        progress = (ProgressBar)findViewById(R.id.wvProgressBar);

        progress.setVisibility(WebView.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
        mUrl = bundle.getString("webviewUrl");

        Log.v("url", mUrl);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(mUrl);


        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog = new ProgressDialog(WebViewActivity.this);

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                progress.setVisibility(WebView.VISIBLE);
            }
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return true;
            }

            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.INVISIBLE);

            }

        });


    }


    public void onBackPressed() {
        finish();
    }




}
