package com.pwc.newfind.net

import com.pwc.newfind.bean.*
import com.pwc.searchview.bean.CompanyTitleListBean
import retrofit2.Call
import retrofit2.http.*
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
            @Query("fullName") fullName: String
    ): Observable<CompanyBaseInfoBean>

    @GET(Constant.detailCompany)
    fun companyDetailInformation(
            @Header("Authorization") authorization: String,
            @Query("fullName") fullName: String
    ): Observable<CompanyDetailBean>

    @GET(Constant.getToken)
    fun getUserToken(): Call<String>

    @GET(Constant.getStarCompany)
    fun getStarCompany(
            @Header("Authorization") authorization: String
    ): Observable<FavouriteCompanyBean>

    @POST(Constant.actionStarCompany)
    fun actionStarCompany(
            @Header("Authorization") authorization: String,
            @Body action: ActionStartCompanyBean
    ): Observable<PostResult>

    @POST(Constant.actionStarIndustry)
    fun actionStarIndustry(
            @Header("Authorization") authorization: String,
            @Body action: ActionStartIndustryBean
    ): Observable<PostResult>

    @GET(Constant.actionStarIndustry)
    fun getStarIndustry(
            @Header("Authorization") authorization: String
    ): Observable<FavouriteIndustryBean>

    @GET(Constant.subscription)
    fun getNews(
            @Header("Authorization") authorization: String
    ): Observable<NewsBean>

    @GET(Constant.research)
    fun getReport(
            @Header("Authorization") authorization: String
    ): Observable<ResearchBean>

    @POST(Constant.actionStarResearch)
    fun actionStarResearch(
            @Header("Authorization") authorization: String,
            @Body action: ActionStartReportBean
    ): Observable<PostResult>

    @GET(Constant.getFindingList)
    fun getFindingTitleList(
            @Header("Authorization") authorization: String
    ): Observable<FindingTitleBean>

    @GET(Constant.getFindingCompany)
    fun getFindingTitles(
            @Header("Authorization") authorization: String,
            @Query("filter") filter: String
    ): Observable<FindingCompanyBean>

    @GET(Constant.getFindingCompanyDefault)
    fun getFindingTitlesDefault(
            @Header("Authorization") authorization: String
    ): Observable<FindingCompanyBean>

    /**
     * 返回authorization
     */
    @POST(Constant.login)
    fun login(
            @Body action: ActionLoginBodyBean
    ): Observable<LoginPostResult>

    /**
     * 注册成功时会生成新的authorization
     * 返回注册结果，成功，失败
     */
    @POST(Constant.register)
    fun register(
            @Body action: ActionRegisterBodyBean
    ): Observable<RegisterPostResult>
}
