package com.pwc.newfind.detail

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.pwc.newfind.R
import com.pwc.newfind.entity.CompanyDetailEntity
import kotlinx.android.synthetic.main.company_profile_card_view.*
import kotlinx.android.synthetic.main.company_profile_card_view.view.*

/**
 * Created by lhuang126 on 1/16/2018.
 */
class CompanyProfileView constructor(context: Context) : FrameLayout(context) {

    init {
        addView(LayoutInflater.from(context).inflate(R.layout.company_profile_card_view, this, false))
    }

    fun setData(entity: CompanyDetailEntity) {
        description.text = entity.description
        fullCompanyName.text = entity.fullName
        legalPerson.text = entity.legalPerson
        establishDate.text = entity.establishDate
    }
}