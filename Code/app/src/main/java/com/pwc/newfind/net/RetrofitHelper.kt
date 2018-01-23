package com.pwc.newfind.net

import android.content.Context

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitHelper private constructor(private val context: Context) {

    var client = getOkHttpClient()
    private var mRetrofit: Retrofit? = null
    val server: RetrofitService
        get() = mRetrofit!!.create(RetrofitService::class.java)

    init {
        resetApp()
    }

    private fun getOkHttpClient(): OkHttpClient {
        //定制OkHttp
        val httpClientBuilder = OkHttpClient.Builder()
        //设置超时时间
        httpClientBuilder.connectTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        // httpClientBuilder.writeTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        //httpClientBuilder.readTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        //使用拦截器
        httpClientBuilder.addInterceptor(RequestInterceptor())
        // httpClientBuilder.addInterceptor(LogInterceptor())
        return httpClientBuilder.build()
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
