@file:Suppress("DEPRECATION")

package com.example.stardrawing.base

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.stardrawing.tool.`interface`.FragmentCallBack

class TimFragmentPagerAdapter(
    timFragmentList: ArrayList<BaseFragment>,
    timFragmentManager: FragmentManager
) : FragmentPagerAdapter(timFragmentManager) {

    private val fragmentList = timFragmentList


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
    }

    override fun getCount(): Int = fragmentList.size



}