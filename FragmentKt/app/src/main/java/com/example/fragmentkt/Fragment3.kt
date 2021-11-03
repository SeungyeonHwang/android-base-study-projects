package com.example.fragmentkt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment3 : Fragment() {

    //Fragment 처음 실행 될때 내부 코드 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // MainActivity의 SetContentView와 동일한 행위
        val view = inflater.inflate(R.layout.frag3, container, false)
        return view
    }
}