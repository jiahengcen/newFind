package com.pwc.newfind.finding

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwc.newfind.R
import kotlinx.android.synthetic.main.finding_fragment_layout.*

/**
 * Created by lhuang126 on 1/19/2018.
 */
class FindingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.finding_fragment_layout, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.main_tab_layout)
        val adapter = FindingAdapter((activity as AppCompatActivity).supportFragmentManager)
        tabLayout.tabMode = TabLayout.MODE_FIXED
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val fragment = adapter.instantiateItem(main_content, tab!!.position)
                adapter.setPrimaryItem(main_content, tab.position, fragment)
                adapter.finishUpdate(main_content)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

        })
        return view
    }

}