package com.pwc.newfind.entity

/**
 * Created by lhuang126 on 1/14/2018.
 */
class CompanyDetailEntity {
    //"description": "IT桔子是一个关注TMT领域创业与投资的数据库，致力于为用户提供结构化的公司数据和商业信息。"
    var description: String? = null
    //"establishDate": "2013-07-22"
    var establishDate: String? = null
    //"fullName": "北京岁月桔子科技有限公司"
    var fullName: String? = null
    //"industry": "科学研究和技术服务业"
    var industry: String? = null
    //"legalPerson": "文飞翔"
    var legalPerson: String? = null
    //"location": "北京"
    var location: String? = null
    //"logo":"https://www.xiniudata.com/file/59390aa5f04e153266cb752a"
    var logo: String? = null
    //"name": "IT桔子"
    var name: String? = null
    //"round": "B轮"
    var round: String? = null
    //"website": "https://itjuzi.com/"
    var website: String? = null
    //"tags": [
    //"\u77e5\u540d\u98ce\u6295",
    //"\u7f51\u6613\u7cfb",
    //"\u76db\u5927\u7cfb",
    //"\u6570\u636e\u670d\u52a1",
    //"\u521b\u4e1a\u670d\u52a1",
    //"\u5927\u6570\u636e",
    //"\u6570\u636e\u5206\u6790",
    //"\u4f01\u4e1a\u670d\u52a1",
    //"\u91d1\u878d",
    //"\u6570\u636e"
    //]
    var tags: ArrayList<String>? = null
    var marginInformation: MutableList<Funding> = mutableListOf()
    var members: MutableList<String> = mutableListOf()

    class Funding constructor(funding: String?, investment: String?, investors: String?, round: String?) {

        //"fundingDate": "2011-04-01",
        var fundingDate: String? = funding
        //"investment": 2500000,
        var investment: String? = investment
        //"investors": "",
        var investors: String? = investors
        //"round": "\u79cd\u5b50\u8f6e"
        var round: String? = round

    }

    class Member constructor(description: String?, education: String?, name: String?, photo: String?, position: String?, work: String?) {
        //description	"彭禹斯，IT桔子的联合创始人，热爱code，热爱互联网。此前曾在雅虎研究院、天使汇等工作。"
        var description: String? = description
        //education	""
        var education: String? = education
        //name	"彭禹斯"
        var name: String? = name
        //photo	"https://www.xiniudata.com/file/5656bceee4861d2a64c5a514"
        var photo: String? = photo
        //position	"联合创始人&CTO"
        var position: String? = position
        //work	"雅虎、天使汇"
        var work: String? = work
    }
}