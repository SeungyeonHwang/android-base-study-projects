package com.hwang.intentkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        // 객체 값 가지고 있으면
        if (intent.hasExtra("msg")) {
            findViewById<TextView>(R.id.tv_getMsg).text = intent.getStringExtra("msg")
        }
    }
}