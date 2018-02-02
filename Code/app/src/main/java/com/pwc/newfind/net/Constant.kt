package com.pwc.newfind.net

/**
 * Created by lhuang126 on 1/11/2018.
 */
object Constant {
    const val host = "http://47.100.119.94:8081/v1/"
    /**
     * /company/industryList 这个是获取全部行业列表
     */
    const val companyIndustry = "company/industryList"
    /**
     * 根据公司名或全名进行模糊搜索：keyword=唱吧
     * search接口进行了更新，可以接受两个参数：
     * /company/search?keyword=xxx&tag=健康|知名风投，多个tag用|分隔，不同tag之间的关系是or，但keyword与tag同时存在时关系是and
     *
     */
    const val companySearch = "company/search"
    //const val companySearch = "company/search?keyword="
    /**
     * 公司基本信息：/company/info?fullName=suiyuejiezi
     */
    const val companyBaseInformation = "company/info"


    /**
     * 公司详情信息http://47.100.119.94:8081/v1/company/detail?companyCode=zuitaokeji
     */
    const val detailCompany = "company/detail"

    const val getToken = "user/get_token"
    /**
     * 操作关注的公司
     */
    const val actionStarCompany = "user/starCompany"
    /**
     * 获取关注的公司
     */
    const val getStarCompany = "user/starCompany"
    /**
     * 获取新闻列表
     */
    const val subscription = "user/news"
    const val actionStarIndustry = "user/starIndustry"
    /**
     * 获得研报
     */
    const val research = "research/get"
    /**
     * 关注或取消关注研报
     */
    const val actionStarResearch = "user/starResearch"
    /**
     * 获取列表
     */
    const val getFindingList = "discover/filterList"
    /**
     *     /discover/get?filter=xxx
     */
    const val getFindingCompany = "discover/get"
    /**
     *     /discover/get
     */
    const val getFindingCompanyDefault = "discover/get"
}

