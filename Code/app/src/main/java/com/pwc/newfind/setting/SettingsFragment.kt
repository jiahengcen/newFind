package com.pwc.newfind.setting


import android.content.Intent
import android.os.Bundle
import android.os.UserHandle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwc.newfind.R
import com.pwc.newfind.base.UserHelper
import com.pwc.newfind.home.AccountDetailActivity
import com.pwc.newfind.home.LoginActivity
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
        user.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        val email = UserHelper.getUserEmail()
        if (email.length < 2) {
            account_name.text = "未设置昵称"
        } else {
            account_name.text = email
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.user ->
                if (UserHelper.getUserEmail().length < 2) {
                    startActivity(Intent(activity, LoginActivity::class.java))
                } else {
                    startActivity(Intent(activity, AccountDetailActivity::class.java))
                }
            R.id.company ->
                startActivity(Intent(activity, FavouriteCompanyActivity::class.java))
            R.id.industry ->
                startActivity(Intent(activity, FavouriteIndustryActivity::class.java))
        }
    }

}