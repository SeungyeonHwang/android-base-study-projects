package com.example.recyclerkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(val profileList: ArrayList<Profiles>) :
    RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>() {

    // plug로 연결될 화면을 붙임
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAdapter.CustomViewHolder {
        //View 생성
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) // 어댑터랑 연결된 Activity의 context
        //View 전달 -> CustomViewHolder 생성
        return CustomViewHolder(view).apply {
            //ViewHolder의 View
            itemView.setOnClickListener {
                //current position
                val curPos = adapterPosition
                val profile: Profiles = profileList[curPos]
                Toast.makeText(
                    parent.context,
                    "이름 : ${profile.name}\n나이 : ${profile.age}\n직업 : ${profile.job}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // list 총 갯수
    override fun getItemCount(): Int {
        return profileList.size
    }

    // onCreateViewHolder -> 실제 연결(지속적 호출)
    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {
        holder.gender.setImageResource(profileList.get(position).gender) // 클릭한 위치, 현재의 포지션
        holder.name.text = profileList.get(position).name
        holder.age.text = profileList.get(position).age.toString()
        holder.job.text = profileList.get(position).job
    }

    //inner class
    //Holder -> 꽉 잡아주는 역할
    //class 뒤 -> 상속
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gender = itemView.findViewById<ImageView>(R.id.imageView) // 성별
        val name = itemView.findViewById<TextView>(R.id.tv_name) // 이름
        val age = itemView.findViewById<TextView>(R.id.tv_age) // 나이
        val job = itemView.findViewById<TextView>(R.id.tv_job) // 직업
    }

}