package com.pwc.newfind

import android.content.Context

import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitHelper private constructor(private val context: Context) {

    internal var client = OkHttpClient()
    private var mRetrofit: Retrofit? = null
    val server: RetrofitService
        get() = mRetrofit!!.create(RetrofitService::class.java)

    init {
        resetApp()
    }

    private fun resetApp() {
        mRetrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.host)
                .build()
    }

    companion object {
        private var instance: RetrofitHelper? = null
        fun getInstance(context: Context): RetrofitHelper {
            if (instance == null) {
                instance = RetrofitHelper(context.applicationContext)
            }
            return instance as RetrofitHelper
        }
    }
}
