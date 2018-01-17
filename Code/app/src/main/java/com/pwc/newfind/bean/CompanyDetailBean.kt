package com.pwc.newfind.bean

/**
 * Created by lhuang126 on 1/16/2018.
 */
class CompanyDetailBean {
    val comp: ArrayList<CompareCompany>? = null
    val funding: ArrayList<Funding>? = null
    var info: Information? = null
    var member: ArrayList<Member>? = null

    class CompareCompany {
        //"establishDate": "2014-04-18",
        var establishDate: String? = null
        //"fullName": "\u6df1\u5733\u5e02\u7406\u7ea6\u4e91\u4fe1\u606f\u7ba1\u7406\u6709\u9650\u516c\u53f8",
        var fullName: String? = null
        //"industry": "\u79d1\u5b66\u7814\u7a76\u548c\u6280\u672f\u670d\u52a1\u4e1a",
        var industry: String? = null
        //"location": "\u6df1\u5733",
        var location: String? = null
        //"logo": "https://www.xiniudata.com/file/5970762fb1d37e273190dcce",
        var logo: String? = null
        //"name": "\u7406\u7ea6\u4e91",
        var name: String? = null
        //"round": null
        var round: String? = null
    }

    class Funding {
        //"fundingDate": "2011-04-01",
        var fundingDate: String? = null
        //"investment": 2500000,
        var investment: String? = null
        //"investors": "",
        var investors: String? = null
        //"round": "\u79cd\u5b50\u8f6e"
        var round: String? = null

    }

    class Information {
        //"description": "Teambition\u662f\u4e00\u6b3e\u56e2\u961f\u534f\u4f5c\u5de5\u5177\uff0c\u901a\u8fc7\u5e2e\u52a9\u4f01\u4e1a\u548c\u56e2\u961f\u8f7b\u677e\u5171\u4eab\u548c\u8ba8\u8bba\u5de5\u4f5c\u4e2d\u7684\u4efb\u52a1\u3001\u6587\u4ef6\u3001\u5206\u4eab\u3001\u65e5\u7a0b\u7b49\u5185\u5bb9\uff0c\u8ba9\u56e2\u961f\u534f\u4f5c\u7115\u53d1\u65e0\u9650\u53ef\u80fd\u3002Teambition \u5728\u7f51\u9875\u3001\u684c\u9762\u3001\u79fb\u52a8\u73af\u5883\u90fd\u6253\u9020\u4e86\u5e94\u7528\uff0c\u4f7f\u7528\u6237\u968f\u65f6\u968f\u5730\u5b9e\u73b0\u56e2\u961f\u534f\u4f5c\u3002",
        var description: String? = null
        //"establishDate": "2011-04-17",
        var establishDate: String? = null
        //"fullName": "\u4e0a\u6d77\u6c47\u7ffc\u4fe1\u606f\u79d1\u6280\u6709\u9650\u516c\u53f8",
        var fullName: String? = null
        //"industry": "\u79d1\u5b66\u7814\u7a76\u548c\u6280\u672f\u670d\u52a1\u4e1a",
        var industry: String? = null
        //"legalPerson": "\u9f50\u4fca\u5143",
        var legalPerson: String? = null
        //"location": "\u4e0a\u6d77",
        var location: String? = null
        //"logo": "https://www.xiniudata.com/file/565cedc1e4861d4b8da1ba05",
        var logo: String? = null
        //"name": "Teambition",
        var name: String? = null
        //"round": "\u6218\u7565\u6295\u8d44",
        var round: String? = null
        //"tags": [
        //"\u77e5\u540d\u5a92\u4f53\u62a5\u9053",
        //"SaaS",
        //"\u534f\u4f5c\u5de5\u5177",
        //"GTD\u6548\u7387"
        //],
        var tags: ArrayList<String>? = null
        //"website": "https://www.teambition.com"
        var website: String? = null
    }

    class Member {
        //"description": "\u9f50\u4fca\u5143\uff0c Teambition\u521b\u59cb\u4eba\u53caCEO\u3002",
        var description: String? = null
        //"education": "\u4e0a\u6d77\u4ea4\u901a\u5927\u5b66",
        var education: String? = null
        //"name": "\u9f50\u4fca\u5143",
        var name: String? = null
        //"photo": "https://www.xiniudata.com/file/5656bd57e4861d2a64c5adf0",
        var photo: String? = null
        //"position": "\u521b\u59cb\u4eba",
        var position: String? = null
        //"work": ""
        var work: String? = null

    }
}