package com.pwc.newfind

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
     * 公司团队信息：/company/member?fullName=北京决策信诚科技有限公司
     */
    const val companyMember = "$host/company/member?fullName="
    /**
     * 公司融资信息：/company/funding?companyCode=suiyuejiezi
     */
    const val companyFunding = "$host/company/funding?companyCode="

    /**
     * 公司详情信息http://47.100.119.94:8081/v1/company/detail?companyCode=zuitaokeji
     */
    const val detailCompany = "company/detail"

    const val getToken="user/get_token"
    /**
     * 操作关注的公司
     */
    const val actionStarCompany="user/starCompany"
}

