package com.example.webviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true // JS 액션 허용
        /**
         * 앱 뷰에서 새 창이 뜨지 않도록 방지하는 구문
         */
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        /**
         * 앱 뷰에서 새 창이 뜨지 않도록 방지하는 구문
         */
        webView.loadUrl("https://www.naver.com") // 링크 주소를 Load 함
    }

    //Back Button Logic -> 웹뷰 뒤로가기 처리
    override fun onBackPressed() { // 백버튼 누를 시 수행할 로직 구현
        if (webView.canGoBack()) {
            // 웹사이트에서 뒤로 갈 페이지 존재 한다면
            webView.goBack() // 웹사이트 뒤로가기 Action
        } else {
            super.onBackPressed() // 본래의 백버튼 기능 수행
        }
    }
}