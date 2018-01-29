package com.pwc.newfind.bean

/**
 * Created by lhuang126 on 1/26/2018.
 */
class ResearchBean {
    val research: ArrayList<SubResearchBean>? = null

    class SubResearchBean {
        //"attachment": "https://krplus-normal.b0.upaiyun.com/industry_report/d2ddd9f10f9c740e9c203a26df238103.pdf",
        //"link": "http://www.199it.com/archives/679753.html",
        //"source": "199IT",
        //"time": "Tue, 23 Jan 2018 14:01:00 GMT",
        //"title": "德勤&中国电信：电信行业洞察（附下载）"
        var id: String? = null
        val attachment: String? = null
        val link: String? = null
        val source: String? = null
        val time: String? = null
        val title: String? = null
        var starred: Boolean? = null
    }
}