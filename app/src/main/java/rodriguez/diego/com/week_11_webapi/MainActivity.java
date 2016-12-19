package rodriguez.diego.com.week_11_webapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processUrl(View view) {
        EditText editText = (EditText) findViewById(R.id.textUrl);
        String urlText = editText.getText().toString();

        if (urlText.equalsIgnoreCase("local")) {
            // if the user types the word "local" load the html from assets folder
            WebView wv = (WebView) findViewById(R.id.web_holder);
            //wv.setInitialScale(60);
            //String myHtmlContent = "<html><body><h1>Hello People!!!!</h1><p>This is content built on the" +
            //      " fly</p></body></html>";
            // method 1 - display html content built on the fly
            //            wv.loadData(myHtmlContent, "text/html", "utf-8");
            // method 2  - load html from the device
            wv.loadUrl("file:///android_asset/localWebPage.html");
        } else if (URLUtil.isValidUrl(urlText)) {
            // the user types a valid URL
            //Uri uriUrl = Uri.parse(urlText);
            // method 3 - launch browser as separate activity
            //Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            //startActivity(launchBrowser);

            // method 4 - launch inside a view
            WebView wv = (WebView) findViewById(R.id.web_holder);
            //wv.setInitialScale(80);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setLoadsImagesAutomatically(true);
            wv.getSettings().setUseWideViewPort(true);
            wv.setWebViewClient(new MyWebViewClient());
            wv.loadUrl(urlText);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            // if return = true, prevents click on hyperlinks from opening a new browser
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // this just shows how the client can intercept certain browser events
            // in this case the completion of page loading
            Toast.makeText(view.getContext(), "Page finished loading ... ", Toast.LENGTH_LONG).show();
        }
    }

}
