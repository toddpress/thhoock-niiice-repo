package app.leftovers.hackathon.sparc.com.leftovers;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by tylerroach on 8/23/14.
 */
public class WebViewActivity extends Activity{

    private WebView webView;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Bundle bundle = getIntent().getExtras();
        mUrl = bundle.getString("URL");

        webView = (WebView)findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(mUrl);

    }

}
