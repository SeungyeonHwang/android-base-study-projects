package com.example.logkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    var a: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a = 2

        if (a == 2) {
            Log.d("a : 2", a.toString())
        } else if (a == 1) {
            Log.d("a : 1", a.toString())
        }
    }
}