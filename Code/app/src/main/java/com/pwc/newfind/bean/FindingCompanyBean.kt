package com.pwc.newfind.bean

/**
 * Created by lhuang126 on 2/1/2018.
 */
class FindingCompanyBean {
    //"filter": "userStarIndustry",
    val filter: String? = null
    val result: ArrayList<FindingCompanySubBean>? = null
    //"title": "用户关注行业"
    val title: String? = null

    class FindingCompanySubBean {
        //"establishDate": "",
        val establishDate: String? = null
        //"fullName": "北京世纪中彩网络科技有限公司",
        val fullName: String? = null
        //"location": "",
        val location: String? = null
        //"logo": "https://krplus-pic.b0.upaiyun.com/com_logo_v3/logo_20213.jpg",
        val logo: String? = null
        //"name": "中彩网",
        val name: String? = null
        //"round": "未融资",
        val round: String? = null
        //"starred": false
        val starred: Boolean? = null
    }
}