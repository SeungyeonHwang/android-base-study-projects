package com.example.recyclerkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileList = arrayListOf(
            Profiles(R.drawable.man, "HWANG1", 30, "Android App Developer"),
            Profiles(R.drawable.man, "HWANG2", 23, "IOS App Developer"),
            Profiles(R.drawable.woman, "HWANG3", 24, "Android App Developer"),
            Profiles(R.drawable.woman, "HWANG4", 25, "IOS App Developer"),
            Profiles(R.drawable.woman, "HWANG5", 26, "IOS App Developer"),
            Profiles(R.drawable.man, "HWANG6", 27, "Android App Developer"),
            Profiles(R.drawable.woman, "HWANG7", 33, "IOS App Developer"),
            Profiles(R.drawable.man, "HWANG8", 44, "Android App Developer"),
            Profiles(R.drawable.man, "HWANG9", 35, "IOS App Developer"),
            Profiles(R.drawable.woman, "HWANG10", 34, "Android App Developer"),
            Profiles(R.drawable.man, "HWANG11", 23, "IOS App Developer")
        )

        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true) // 성능 개선

        rv_profile.adapter = ProfileAdapter(profileList)
    }
}