package com.pwc.newfind

import com.pwc.newfind.bean.SubscriptionBean
import com.pwc.newfind.entity.CompanyAdapterEntity

/**
 * Created by lhuang126 on 1/10/2018.
 */
class TestSubscriptionBean {
    fun getListCompanyAdapterBean(): ArrayList<CompanyAdapterEntity> {
        return arrayListOf(
                getCompanyAdapterBean(),
                getCompanyAdapterBean(),
                getCompanyAdapterBean(),
                getCompanyAdapterBean(),
                getCompanyAdapterBean()
        )
    }


    fun getCompanyAdapterBean(): CompanyAdapterEntity {
        val ben = CompanyAdapterEntity()
        ben.logoString = "https://www.xiniudata.com/file/565ce6ffe4861d4b8da1b000"
        ben.companyName = "K歌达人"
        return ben
    }

    fun getListSubscriptionBean(): ArrayList<SubscriptionBean> {

        return arrayListOf<SubscriptionBean>(
                getSubscriptionBean(), getSubscriptionBean(), getSubscriptionBean(),
                getSubscriptionBean(), getSubscriptionBean(), getSubscriptionBean(),
                getSubscriptionBean(), getSubscriptionBean(), getSubscriptionBean(),
                getSubscriptionBean(), getSubscriptionBean(), getSubscriptionBean(),
                getSubscriptionBean(), getSubscriptionBean(), getSubscriptionBean(),
                getSubscriptionBean(), getSubscriptionBean())
    }

    fun getSubscriptionBean(): SubscriptionBean {
        val sub: SubscriptionBean = SubscriptionBean()
        sub.title = "title" + Math.random()
        sub.subTitle = "subTitle" + Math.random()
        sub.time = "" + Math.random()
        sub.content = "根据用户在系统内浏览产生的历史记录，自动推送相同或者相关领域同类型信息。信息范围同主动订阅推送"
        return sub
    }
}