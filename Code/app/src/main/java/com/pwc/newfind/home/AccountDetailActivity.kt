package com.pwc.newfind.home

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.pwc.newfind.R
import com.pwc.newfind.base.UserHelper
import kotlinx.android.synthetic.main.account_detail_activity.*

/**
 * Created by lhuang126 on 2/28/2018.
 */
class AccountDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.rgb(0xC5, 0x2A, 0x1A)
        setContentView(R.layout.account_detail_activity)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        account_email.text = UserHelper.getUserEmail()
        account_phone.text = UserHelper.getUserPhone()
        logout.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.logout -> {
                UserHelper.logout()
                finish()
            }
        }
    }
}