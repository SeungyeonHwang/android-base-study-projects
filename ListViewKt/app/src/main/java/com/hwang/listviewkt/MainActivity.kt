package com.hwang.listviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Sample Data Model
    var UserList = arrayListOf<User>(
        User(R.drawable.android, "HWANG", "30", "안녕하세요1"),
        User(R.drawable.android, "HWANG1", "31", "안녕하세요2"),
        User(R.drawable.android, "HWANG2", "32", "안녕하세요3"),
        User(R.drawable.android, "HWANG3", "33", "안녕하세요4"),
        User(R.drawable.android, "HWANG4", "34", "안녕하세요5"),
        User(R.drawable.android, "HWANG5", "35", "안녕하세요6")
    )

    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티의 실행 지점
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val item = arrayOf("사과", "배", "딸기", "키위", "승연")
//        val listView = findViewById<ListView>(R.id.listView)
//        // context : 한 액티비티의 모든 정보를 담고 있음
//        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)

        val Adapter = UserAdapter(this, UserList)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = Adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
    }
}