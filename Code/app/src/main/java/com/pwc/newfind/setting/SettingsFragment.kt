package com.pwc.newfind.setting


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwc.newfind.R
import kotlinx.android.synthetic.main.settings_fragment.*

/**
 * Created by lhuang126 on 1/14/2018.
 */
class SettingsFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        company.setOnClickListener(this)
        industry.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.company ->
                startActivity(Intent(activity, FavouriteCompanyActivity::class.java))
            R.id.industry ->
                startActivity(Intent(activity, FavouriteIndustryActivity::class.java))
        }
    }

}