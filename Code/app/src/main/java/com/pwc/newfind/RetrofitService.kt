package com.pwc.newfind

import com.pwc.newfind.bean.CompanyBaseInfoBean
import com.pwc.newfind.bean.CompanyDetailBean
import com.pwc.newfind.bean.IndustryListBean
import com.pwc.newfind.bean.Token
import com.pwc.searchview.bean.CompanyTitleListBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import rx.Observable


interface RetrofitService {
    @GET(Constant.companyIndustry)
    fun industryList(): Observable<IndustryListBean>

    @GET(Constant.companySearch)
    fun companySearchResult(
            @Header("Authorization") authorization: String,
            @Query("keyword") keyword: String
    ): Observable<CompanyTitleListBean>

    @GET(Constant.companyBaseInformation)
    fun companyBaseInformation(
            @Query("companyCode") companyCode: String
    ): Observable<CompanyBaseInfoBean>

    @GET(Constant.detailCompany)
    fun companyDetailInformation(
            @Header("Authorization") authorization: String,
            @Query("companyCode") companyCode: String
    ): Observable<CompanyDetailBean>

    @GET(Constant.getToken)
    fun getUserToken(): Call<String>
}
