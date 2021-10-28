package com.hwang.listviewkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter(val context: Context, val UserList: ArrayList<User>) : BaseAdapter() {

    //View를 가지고 왔을때 어떻게 뿌려줄 것인가?
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //View를 xml상의 그림을 메모리로 올려준다 -> 컬럼 별 뷰를 어떻게 뿌릴지?(항목 XML)
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_user, null)

        //UserModel 따라 준다
        val profile = view.findViewById<ImageView>(R.id.iv_profile)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val age = view.findViewById<TextView>(R.id.tv_age)
        val greet = view.findViewById<TextView>(R.id.tv_greet2)

        //position -> Index(0 ~)
        val user = UserList[position]

        profile.setImageResource(user.profile)
        name.text = user.name
        age.text = user.age
        greet.text = user.greet

        return view
    }

    //어떤 아이템을 끌고 올꺼냐?
    override fun getItem(postion: Int): Any {
        return UserList[postion]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return UserList.size
    }
}