package com.hwang.intentkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_a = findViewById<Button>(R.id.btn_a)
        btn_a.setOnClickListener {

            val intent = Intent(this, SubActivity::class.java) // 다음 화면으로 이동하기 위한 인텐트 객체 생성
            // 데이터 넘기기 위해 담아준다(msg 라는 key로 잠근다)
            intent.putExtra(
                "msg",
                findViewById<TextView>(R.id.tv_sendMsg).text.toString()
            )
            startActivity(intent) //intent에 저장되어있는 액티비티로 이동한다
            finish() //자기 자신의 액티비티를 파괴함
        }
    }
}