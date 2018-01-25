package com.pwc.newfind.finding


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwc.newfind.R

/**
 * Created by lhuang126 on 1/19/2018.
 */
class FindingFragment : Fragment() {

    var mFragment1: ListFragment? = null

    var mFragment2: ListFragment? = null

    var mFragment3: ListFragment? = null

    private val mPagerAdapter: PagerAdapter by lazy {
        PagerAdapter(childFragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.finding_fragment_layout, container, false)
        val mViewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val mTabLayout = view.findViewById<TabLayout>(R.id.toolbar_tab)

        mViewPager.offscreenPageLimit = 2
        mFragment1 = ListFragment()
        mFragment1!!.initData('a', 'z')
        mFragment2 = ListFragment()
        mFragment2!!.initData('A', 'Z')
        mFragment3 = ListFragment()
        mFragment3!!.initData('c', 'x')
        mViewPager.adapter = mPagerAdapter
        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))
        return view
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


        override fun getItem(position: Int): android.support.v4.app.Fragment? {
            if (position == 0) {
                return mFragment1
            } else if (position == 1) {
                return mFragment2
            } else if (position == 2) {
                return mFragment3
            }

            return null
        }

        override fun getCount(): Int {
            return 3
        }

    }


}


