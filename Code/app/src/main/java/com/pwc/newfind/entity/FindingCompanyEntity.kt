package com.pwc.newfind.entity

/**
 * Created by lhuang126 on 2/1/2018.
 */
class FindingCompanyEntity {
    //"filter": "userStarIndustry",
    var filter: String? = null
    val result: MutableList<FindingCompanySubBean> = mutableListOf()
    //"title": "用户关注行业"
    var title: String? = null

    class FindingCompanySubBean {
        //"establishDate": "",
        var establishDate: String? = null
        //"fullName": "北京世纪中彩网络科技有限公司",
        var fullName: String? = null
        //"location": "",
        var location: String? = null
        //"logo": "https://krplus-pic.b0.upaiyun.com/com_logo_v3/logo_20213.jpg",
        var logo: String? = null
        //"name": "中彩网",
        var name: String? = null
        //"round": "未融资",
        var round: String? = null
        //"starred": false
        var starred: Boolean? = false
    }
}