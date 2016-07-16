package org.example.browserview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText urlText;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // すべてのユーザーインターフェース要素のハンドルを取得
        webView = (WebView)findViewById(R.id.web_view);
        // Webビュー表示のみにする
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        urlText = (EditText)findViewById(R.id.url_field);
        goButton = (Button)findViewById(R.id.go_button);
        // イベントハンドラをセット
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openBrowser();
            }
        });
        urlText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    openBrowser();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * テキストボックスで指定されたURLを使ってブラウザーを開く
     */
    private void openBrowser() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(urlText.getText().toString());
    }

}
