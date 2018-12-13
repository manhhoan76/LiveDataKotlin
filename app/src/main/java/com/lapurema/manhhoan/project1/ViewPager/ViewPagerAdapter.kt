package com.lapurema.manhhoan.project1.ViewPager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val mFragmentList = ArrayList<Fragment>()
//    fun ViewPagerAdapter(manager: FragmentManager) {
//        super.
//    }

    override fun getItem(p0: Int): Fragment {
        return mFragmentList.get(p0)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }


    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }
}