package com.hwang.edittextbuttonkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티가 최초 실행 되면 이 곳을 실행한다
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_getText = findViewById<Button>(R.id.btn_getText)

        btn_getText.setOnClickListener { // 에딧 텍스트에 입력되어 있는 값을 가지고 와서 텍스트 뷰에 뿌려준다
            val resultText = findViewById<EditText>(R.id.et_id).text.toString() //Edit Text Value
            val tv_result = findViewById<TextView>(R.id.tv_result)
            tv_result.text = resultText //Set Value
        }

    }
}