package com.pwc.newfind.bean

/**
 * Created by lhuang126 on 1/24/2018.
 */
class NewsBean {
    val news: ArrayList<NewsSubBean>? = null

    //"fullName": "爱彼迎信息科技（北京）有限公司",
    //"logo": "http://krplus-pic.b0.upaiyun.com/201706/02/f1c7309681a65a6d008319b1ea214884.png",
    //"name": "Airbnb",
    //"publish_date": "2018-01-04 08:25:00",
    //"title": "2017年Airbnb房客突破300万　拥有约400万房源",
    //"url": "http://www.traveldaily.cn/article/119571",
    //"website": "环球旅讯"
    class NewsSubBean {
        val fullName: String? = null
        val logo: String? = null
        val name: String? = null
        val publish_date: String? = null
        val title: String? = null
        val url: String? = null
        val website: String? = null
    }
}