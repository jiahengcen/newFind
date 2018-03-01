package com.pwc.newfind.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.pwc.newfind.R
import com.pwc.newfind.base.UserHelper
import com.pwc.newfind.bean.ActionLoginBodyBean
import com.pwc.newfind.bean.LoginPostResult
import com.pwc.newfind.net.RetrofitHelper
import kotlinx.android.synthetic.main.login_activity.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by lhuang126 on 2/26/2018.
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = Color.rgb(0xC5, 0x2A, 0x1A)
        setContentView(R.layout.login_activity)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { this@LoginActivity.finish() }
        login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        Log.e("HLA", "Login resume")
        if (UserHelper.getUserEmail().length > 2) {
            finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.login ->
                login()
            R.id.register ->
                register()
        }
    }

    private fun login() {
        val email: String = login_email.text.toString()
        val password: String = login_password.text.toString()
        val loginBan = ActionLoginBodyBean()
        loginBan.email = email
        loginBan.password = password
        if (email.isBlank()) {
            Toast.makeText(this@LoginActivity, "邮箱不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isBlank()) {
            Toast.makeText(this@LoginActivity, "密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }

        RetrofitHelper.getInstance(this)
                .server
                .login(UserHelper.getUserToken(), loginBan)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<LoginPostResult> {
                    override fun onNext(t: LoginPostResult) {
                        if (t.msgId != null && t.msgId == 0) {
                            UserHelper.setUserToken(t.token)
                            UserHelper.setUserEmail(t.email)
                            UserHelper.setUserPhone(t.phone)
                            this@LoginActivity.finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "登录失败，用户名或密码错误", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        Toast.makeText(this@LoginActivity, "登录失败，请重试", Toast.LENGTH_SHORT).show()
                    }

                })
    }

    private fun register() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

}