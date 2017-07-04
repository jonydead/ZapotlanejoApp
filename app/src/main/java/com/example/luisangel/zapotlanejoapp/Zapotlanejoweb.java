package com.example.luisangel.zapotlanejoapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.webkit.WebViewClient;

public class Zapotlanejoweb extends AppCompatActivity {
    public String url = "";
    public WebView webView;
    private ProgressBar progressBar;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapotlanejoweb);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = " ";

        webView = (WebView) this.findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new HelpClient());

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                frameLayout.setVisibility(View.VISIBLE);
                progressBar.setProgress(progress);
                setTitle("Cargando...");

                if (progress == 100) {
                    frameLayout.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                }
                super.onProgressChanged(view, progress);
            }
        });
        webView.setVerticalScrollBarEnabled(false);
        progressBar.setProgress(0);

        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
        }
        switch (title) {
            case "Historia Zapotlanejo":
                url = "http://zapotlanejo.gob.mx/2016/menu/zapotlanejo/historia";
                webView.loadUrl(url);
                break;
            case "Turismo":
                url = "http://zapotlanejo.gob.mx/2016/menu/zapotlanejo/lugares%20a%20visitar";
                webView.loadUrl(url);
                break;
            case "Trámites y Servicios":
                url = "http://zapotlanejo.gob.mx/2016/menu/tramites%20y%20servicios";
                webView.loadUrl(url);
                break;
            case "Recaudadoras":
                url = "http://zapotlanejo.gob.mx/2016/menu/gobierno/recaudadoras";
                webView.loadUrl(url);
                break;
            case "Gaceta municipal":
                url = "http://zapotlanejo.gob.mx/2016/menu/gobierno/gaceta%20municipal";
                webView.loadUrl(url);
                break;
            case "Obras Publicas":
                url = "http://zapotlanejo.gob.mx/2016/menu/obra%20p%C3%9Ablica";
                webView.loadUrl(url);
                break;
            case "Directorio":
                url = "http://zapotlanejo.gob.mx/2016/menu/gobierno/directorio";
                webView.loadUrl(url);
                break;
            case "Mapa del municipio":
                url = "http://zapotlanejo.gob.mx/2016/menu/zapotlanejo/mapa%20del%20municipio";
                webView.loadUrl(url);
                break;
        }

    }

    @Override
    public boolean onKeyDown(int KeyCode, KeyEvent event){
        webView=(WebView) findViewById(R.id.webview);
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (KeyCode){
                case KeyEvent.KEYCODE_BACK:
                    if(webView.canGoBack()){
                        webView.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(KeyCode, event);
    }

    private class HelpClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}


