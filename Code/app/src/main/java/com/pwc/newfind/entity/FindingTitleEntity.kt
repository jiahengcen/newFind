package com.pwc.newfind.entity

/**
 * Created by lhuang126 on 1/30/2018.
 */
class FindingTitleEntity {
    var tileList: MutableList<FilterBean> = mutableListOf()

    class FilterBean {
        //"filter": "userStarIndustry",
        var filter: String? = null
        //"title": "用户关注行业"
        var title: String? = null
    }
}