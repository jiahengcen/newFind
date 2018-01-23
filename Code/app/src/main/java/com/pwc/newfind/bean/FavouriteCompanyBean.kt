package com.pwc.newfind.bean

/**
 * Created by lhuang126 on 1/23/2018.
 */
class FavouriteCompanyBean {
    var starCompanyList: java.util.ArrayList<CompanyTitleSubBean>? = null

    class CompanyTitleSubBean {

        //"establishDate": "",
        //"fullName": "北京决策信诚科技有限公司",
        //"location": "",
        //"logo": "http://krplus-pic.b0.upaiyun.com/201712/27/60e56d36fee3d729423d7b9c31071d53.png",
        //"name": "我爱卡",
        //"round": "C+轮"

        var fullName: String? = null
        var location: String? = null
        var logo: String? = null
        var name: String? = null
        var round: String? = null
        var establishDate: String? = null
    }
}