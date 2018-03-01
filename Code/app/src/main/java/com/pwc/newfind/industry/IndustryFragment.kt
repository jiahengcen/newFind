package com.pwc.newfind.industry

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



/**
 * Created by lhuang126 on 1/19/2018.
 */
class IndustryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val textView = TextView(activity);
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        textView.layoutParams = params
        textView.text = "功能尚在开发"
        textView.gravity = Gravity.CENTER
        textView.textSize = 20f
        return textView
    }


}