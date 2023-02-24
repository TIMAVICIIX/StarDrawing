package com.example.stardrawing.community

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stardrawing.R
import com.example.stardrawing.base.BaseFragment
import com.example.stardrawing.tool.TotalVal
import com.example.stardrawing.tool.`interface`.FragmentCallBack
import com.example.stardrawing.tool.toasttool.TimToast.toastItShortLength

class CommunityFragment:BaseFragment(){

    private lateinit var fragmentCallBack: FragmentCallBack
    private var judgeSuccess:Boolean=false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community,container,false)
    }

}