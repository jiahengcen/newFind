package com.pwc.newfind.home

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.pwc.newfind.R
import com.pwc.newfind.base.Application
import com.pwc.newfind.base.UserHelper
import com.pwc.newfind.bean.ActionRegisterBodyBean
import com.pwc.newfind.bean.RegisterPostResult
import com.pwc.newfind.net.RetrofitHelper

import kotlinx.android.synthetic.main.register_activity.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by lhuang126 on 2/27/2018.
 */
class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = Color.rgb(0xC5, 0x2A, 0x1A)
        setContentView(R.layout.register_activity)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { this@RegisterActivity.finish() }
        register.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.register ->
                register()
        }
    }

    private fun register() {
        val email = register_email.text.toString()
        val password = register_password.text.toString()
        val confirmPassword = register_password2.text.toString()
        val phone = register_phone.text.toString()
        if (email.isBlank()) {
            Toast.makeText(this@RegisterActivity, "邮箱不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isBlank() or (password.length < 6)) {
            Toast.makeText(this@RegisterActivity, "密码不能小于六位", Toast.LENGTH_SHORT).show()
            return
        }
        if (password == confirmPassword) {
            val actionBean = ActionRegisterBodyBean()
            actionBean.email = email
            actionBean.password = password
            actionBean.phone = phone
            RetrofitHelper.getInstance(this)
                    .server
                    .register(UserHelper.getUserToken(),actionBean)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<RegisterPostResult> {
                        override fun onError(e: Throwable?) {
                            Toast.makeText(this@RegisterActivity, "注册失败", Toast.LENGTH_SHORT).show()
                        }

                        override fun onNext(t: RegisterPostResult) {
                            if (t.msgId != null && t.msgId == 0) {
                                Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
                                val email = register_email.text.toString()
                                val phone = register_phone.text.toString()
                                UserHelper.setUserEmail(email)
                                UserHelper.setUserPhone(phone)
                                this@RegisterActivity.finish()
                            } else {
                                Toast.makeText(this@RegisterActivity, "注册失败", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onCompleted() {
                        }
                    })
        } else {
            Toast.makeText(this@RegisterActivity, "两次密码不一致", Toast.LENGTH_SHORT).show()
        }
    }
}