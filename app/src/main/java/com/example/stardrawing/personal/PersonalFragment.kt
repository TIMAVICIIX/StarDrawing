package com.example.stardrawing.personal

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

class PersonalFragment:BaseFragment(){

    private lateinit var fragmentCallBack: FragmentCallBack

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal,container,false)
    }

}