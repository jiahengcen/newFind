package com.pwc.newfind.detail

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.pwc.newfind.R

/**
 * Created by lhuang126 on 1/17/2018.
 */
class CompanyMemberView(context: Context) : FrameLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.company_member_card_view, this, false)
    }
}