package com.example.sharedkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // 해당 액티비티가 처음 실행 될때 한번 수행하는 곳(초기화)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: 저장된 데이터를 로드
        loadData() // edit text 저장되어 있던 값을 setText
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        et_hello.setText(
            // 1번쨰 인자에서는 key, 2번째 인자에서는 key 값에 데이터가 존재하지 않을 경우 대체
            pref.getString(
                "name",
                ""
            )
        )
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0) // 내부 디렉토리에 pref 폴더 추가
        val edit = pref.edit() // 수정모드
        edit.putString("name", et_hello.text.toString()) // 1번째 인자 키값, 2번째 인자는 실제 값
        edit.apply() // 값을 저장 완료
    }

    override fun onDestroy() { // 액티비티가 종료되는 시점이 다가올 때 호출(종료 되는 시점의 행위)
        super.onDestroy()

        saveData() // edit text 값을 저장
    }
}