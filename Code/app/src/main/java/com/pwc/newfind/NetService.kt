package com.pwc.newfind

import com.pwc.newfind.bean.IndustryListBean
import retrofit2.http.GET
import rx.Observable

/**
 * Created by lhuang126 on 1/11/2018.
 */

interface NetService {
    @GET(Constant.companyIndustry)
    fun industryList(
            //@Query("username") String username,
            //@Query("password") String password
    ): Observable<IndustryListBean>
//    interface MyService {
//        @GET("user/login" )
//        Observable<UserInfo> login(
//        @Query("username") String username,
//        @Query("password") String password
//        );
//    }



}
