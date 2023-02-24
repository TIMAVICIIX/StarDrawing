package com.example.stardrawing.painting

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.stardrawing.R
import com.example.stardrawing.base.BaseFragment
import com.example.stardrawing.tool.`interface`.FragmentCallBack
import com.example.stardrawing.tool.activitytool.TimActivity.activityStart


class PaintingFragment : BaseFragment() {

    private lateinit var createNewPaintBtn: Button
    private lateinit var loadingPaintBtn: Button
    private lateinit var sharePaintBtn: Button

    private lateinit var attachingActivity:Activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_painting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVal(view)

        createNewPaintBtn.setOnClickListener {
            attachingActivity.activityStart<NewPaintingActivity>()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context !is FragmentCallBack) {
            throw java.lang.IllegalArgumentException("CenterFragment:Don't init interface")
        } else {
            attachingActivity = context as Activity
        }

    }


    private fun initVal(view:View){

        createNewPaintBtn=view.findViewById(R.id.painting_view_createPaintingBtn)
        loadingPaintBtn=view.findViewById(R.id.painting_view_loadingPaintingBtn)
        sharePaintBtn=view.findViewById(R.id.painting_view_sharePaintingBtn)

    }
}