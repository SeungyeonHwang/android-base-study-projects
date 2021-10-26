package com.hwang.textviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { //앱 최초 실행시 수행
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //XML 화면 뷰 연결

        val tv_title = findViewById<TextView>(R.id.tv_title)
        tv_title.text = "Hello World"


    }
}