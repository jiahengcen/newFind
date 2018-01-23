package com.pwc.newfind.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by lhuang126 on 1/23/2018.
 */
/**
 * 请求拦截器，修改请求header
 */
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                //.addHeader("Access-Control-Allow-Origin", "*")
                //.addHeader("Access-Control-Allow-Headers", "X-Requested-With")
                //.addHeader("Vary", "Accept-Encoding")
                //.addHeader("Cookie", "add cookies here")
                .build()
        // Log.v("HLA", "request:" + request.toString());
        //Log.v("HLA", "request headers:" + request.headers().toString());
        return chain.proceed(request);
    }
}
