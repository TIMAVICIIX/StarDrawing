package com.example.stardrawing

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.stardrawing.base.BaseActivity
import com.example.stardrawing.base.BaseFragment
import com.example.stardrawing.base.TimDepthPageTransformer
import com.example.stardrawing.base.TimFragmentPagerAdapter
import com.example.stardrawing.board.BoardFragment
import com.example.stardrawing.center.CenterFragment
import com.example.stardrawing.community.CommunityFragment
import com.example.stardrawing.painting.PaintingFragment
import com.example.stardrawing.personal.PersonalFragment
import com.example.stardrawing.tool.`interface`.FragmentCallBack
import com.example.stardrawing.tool.TotalVal

class MainView : BaseActivity(), FragmentCallBack, View.OnClickListener,
    ViewPager.OnPageChangeListener {

    private lateinit var guideCenterBtn: Button
    private lateinit var guideBoardBtn: Button
    private lateinit var guidePaintingBtn: Button
    private lateinit var guideCommunityBtn: Button
    private lateinit var guidePersonalBtn: Button

    private lateinit var isCheckedList: ArrayList<Boolean>

    private lateinit var centerBtnBackground: ImageView

    private lateinit var fragmentList: ArrayList<BaseFragment>
    private lateinit var fragmentViewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)
        initVar()


    }

    override fun onClick(v: View?) {

        for (isNo in isCheckedList) {
            Log.d("isCheckedList:", "$isNo")
        }
        Log.d("isCheckedList:", "\n")

        when (v?.id) {

            R.id.guideBoardBtn -> changeIconAndState(TotalVal.REPLACE_TO_BOARD_FRAGMENT)
            R.id.guidePaintingBtn -> changeIconAndState(TotalVal.REPLACE_TO_PAINTING_FRAGMENT)
            R.id.guideCenterBtn -> changeIconAndState(TotalVal.REPLACE_TO_CENTER_FRAGMENT)
            R.id.guideCommunityBtn -> changeIconAndState(TotalVal.REPLACE_TO_COMMUNITY_FRAGMENT)
            R.id.guidePersonalBtn -> changeIconAndState(TotalVal.REPLACE_TO_PERSONAL_FRAGMENT)

        }

    }


    override fun onFragmentCallBack(text: Int) {
        changeIconAndState(text)
    }

    override fun onPageSelected(position: Int) {
        changeIconInAdapter(position)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (supportFragmentManager.backStackEntryCount != 0){
                supportFragmentManager.popBackStack()
            }else{
                exitDouble(2000,"再按一次退出应用")
            }
            return true
        }
       return super.onKeyDown(keyCode, event)
    }

    /*override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                touchMoveX = event.x
                touchMoveY = event.y
                Log.d("MainView:","Right")
            }

            MotionEvent.ACTION_MOVE -> {
                val mY = event.y
                val mX = event.x

                val disW = mX - touchMoveX
                val disH = mY - touchMoveY
                Log.d("MainView:","Right")

                if (kotlin.math.abs(disW) > kotlin.math.abs(disH)) {
                    if (disW > 0 && iconMove > 0) {
                        Log.d("MainView:","Right")
                        iconMove--
                        changeIconInAdapter(iconMove)
                    } else if (disW < 0 && iconMove < 4) {
                        Log.d("MainView:","Left")
                        iconMove++
                        changeIconInAdapter(iconMove)
                    }
                }

                touchMoveY = mY
                touchMoveX = mX
            }

        }

        return super.onTouchEvent(event)
    }*/

    private fun changeIconInAdapter(index: Int) {

        if (!isCheckedList[index]) {
            exceptList(index)
            exceptIcon(index)
        }

    }

    private fun changeIconAndState(index: Int) {
        if (!isCheckedList[index]) {
            exceptList(index)
            exceptIcon(index)
            replaceFragment(index)
        }
    }

    private fun initVar() {

        /**************导航键对象实例化↓***************/
        guideCenterBtn = findViewById(R.id.guideCenterBtn)
        guideBoardBtn = findViewById(R.id.guideBoardBtn)
        guideCommunityBtn = findViewById(R.id.guideCommunityBtn)
        guidePaintingBtn = findViewById(R.id.guidePaintingBtn)
        guidePersonalBtn = findViewById(R.id.guidePersonalBtn)

        centerBtnBackground = findViewById(R.id.centerBtnView)

        fragmentList = ArrayList(5)
        fragmentList.add(BoardFragment())
        fragmentList.add(PaintingFragment())
        fragmentList.add(CenterFragment())
        fragmentList.add(CommunityFragment())
        fragmentList.add(PersonalFragment())

        fragmentViewPager = findViewById(R.id.center_pager)
        /***************导航对象实例化↑*****************/

        /***************按钮与fragment之间选中关系实例化↓**************/
        isCheckedList = ArrayList(5)
        repeat(5) {
            isCheckedList.add(false)
        }

        /***************默认中心界面*****************/
        isCheckedList[TotalVal.REPLACE_TO_CENTER_FRAGMENT] = true
        for (isNo in isCheckedList) {
            Log.d("isCheckedList:", "$isNo")
        }
        Log.d("isCheckedList:", "\n")


        fragmentViewPager.adapter = TimFragmentPagerAdapter(fragmentList, supportFragmentManager)
        fragmentViewPager.setPageTransformer(true, TimDepthPageTransformer())
        fragmentViewPager.currentItem = 2

        /*replaceFragment(CenterFragment())*/
        fragmentViewPager.addOnPageChangeListener(this)
    }


    //fun :该函数让被点击到的导航键改变被点击状态
    private fun exceptList(index: Int) {
        isCheckedList.fill(false)
        isCheckedList[index] = true
    }

    //fun :该函数在exceptList()函数的基础上对应用图标重改
    private fun exceptIcon(index: Int) {

        guideBoardBtn.setBackgroundResource(R.drawable.main_view_boardbtn_disable)
        guidePaintingBtn.setBackgroundResource(R.drawable.main_view_paintingbtn_disable)
        guideCommunityBtn.setBackgroundResource(R.drawable.main_view_communitybtn_disable)
        guidePersonalBtn.setBackgroundResource(R.drawable.main_view_personalbtn_disable)

        centerBtnBackground.setBackgroundResource(R.drawable.main_view_centerbtn_backview_disable)

        when (index) {

            TotalVal.REPLACE_TO_BOARD_FRAGMENT -> guideBoardBtn.setBackgroundResource(R.drawable.main_view_boardbtn_able)
            TotalVal.REPLACE_TO_PAINTING_FRAGMENT -> guidePaintingBtn.setBackgroundResource(R.drawable.main_view_paintingbtn_able)
            TotalVal.REPLACE_TO_CENTER_FRAGMENT -> centerBtnBackground.setBackgroundResource(R.drawable.main_view_centerbtn_backview_able)
            TotalVal.REPLACE_TO_COMMUNITY_FRAGMENT -> guideCommunityBtn.setBackgroundResource(R.drawable.main_view_communitybtn_able)
            TotalVal.REPLACE_TO_PERSONAL_FRAGMENT -> guidePersonalBtn.setBackgroundResource(R.drawable.main_view_personalbtn_able)

        }

    }

    //fun :变换fragment，状态改变来源于导航键的选中与否
    private fun replaceFragment(index: Int) {
        //val fragmentManager = supportFragmentManager
        //val transition = fragmentManager.beginTransaction()

        /******!!!容易内存泄露，后期修改*********/
        // transition.replace(R.id.fragmentEmpty, fragment)
        //transition.commit()
        fragmentViewPager.currentItem = index
    }

}