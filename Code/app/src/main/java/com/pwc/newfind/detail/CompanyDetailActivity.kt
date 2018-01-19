package com.pwc.newfind.detail

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import com.pwc.newfind.Application
import com.pwc.newfind.R
import com.pwc.newfind.RetrofitHelper
import com.pwc.newfind.bean.CompanyDetailBean
import com.pwc.newfind.entity.CompanyDetailEntity
import kotlinx.android.synthetic.main.company_favourite_activity.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by lhuang126 on 1/10/2018.
 */
class CompanyDetailActivity : AppCompatActivity() {
    private val companyCode by lazy {
        //intent.getStringExtra(COMPANY_CODE)
        "suiyuejiezi"
    }
    private val companyDetailEntity = CompanyDetailEntity()

    companion object {
        public val COMPANY_CODE = "companyCode"
    }

    private val parentView: ViewGroup by lazy {
        findViewById<LinearLayout>(R.id.main_layout)
    }
    private val titleView: CompanyTitleCardView by lazy {
        CompanyTitleCardView(this)
    }
    private val profileView: CompanyProfileView by lazy {
        CompanyProfileView(this)
    }
    private val marginInformationView: MarginInformationView by lazy {
        MarginInformationView(this)
    }
    private val companyMemberView: CompanyMemberView by lazy {
        CompanyMemberView(this)
    }
    private val companyCompareView: CompanyCompareView by lazy {
        CompanyCompareView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = Color.rgb(0xC5, 0x2A, 0x1A)
        setContentView(R.layout.company_detail_activity)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { this@CompanyDetailActivity.finish() }
        load()
    }

    private fun load() {
        RetrofitHelper.getInstance(this)
                .server
                .companyDetailInformation(Application.getInstances().userToken, companyCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CompanyDetailBean> {
                    override fun onError(e: Throwable?) {
                        Log.e("HLA", "companyDetailInformation net error")
                    }

                    override fun onCompleted() {
                    }

                    override fun onNext(action: CompanyDetailBean?) {
                        /**
                         * part 1 titleCardView
                         */
                        companyDetailEntity.description = action?.info?.description
                        companyDetailEntity.establishDate = action?.info?.establishDate
                        companyDetailEntity.fullName = action?.info?.fullName
                        companyDetailEntity.industry = action?.info?.industry
                        companyDetailEntity.legalPerson = action?.info?.legalPerson
                        companyDetailEntity.location = action?.info?.location
                        companyDetailEntity.logo = action?.info?.logo
                        companyDetailEntity.name = action?.info?.name
                        companyDetailEntity.round = action?.info?.round
                        companyDetailEntity.tags = action?.info?.tags
                        companyDetailEntity.website = action?.info?.website
                        titleView.setData(companyDetailEntity)
                        toolbar.title = companyDetailEntity.name
                        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        params.leftMargin = dp2px(8)
                        params.rightMargin = dp2px(8)
                        params.topMargin = dp2px(8)
                        parentView.addView(initTitleCardView(), params)

                        /**
                         * part 2  Company Profile
                         */
                        parentView.addView(profileView, params)
                        profileView.setData(companyDetailEntity)

                        /**
                         * part 3 融资信息
                         */
                        if (action?.funding != null) {
                            for (i in 0..action.funding.size - 1) {
                                val item = CompanyDetailEntity.Funding(action.funding[i].fundingDate, action.funding[i].investment, action.funding[i].investors, action.funding[i].round)
                                companyDetailEntity.marginInformation.add(item)
                            }
                        }
                        parentView.addView(marginInformationView, params)
                        marginInformationView.setData(companyDetailEntity)
                        /**
                         * part 4 团队信息
                         */
                        if (action?.member != null) {
                            try {
                                for (i in 0..action.member!!.size - 1) {
                                    val item = CompanyDetailEntity.Member(
                                            action.member!![i].description,
                                            action.member!![i].education,
                                            action.member!![i].name,
                                            action.member!![i].photo,
                                            action.member!![i].position,
                                            action.member!![i].work)
                                    companyDetailEntity.members.add(item)
                                }
                            } catch (e: Exception) {
                                Log.e("HLA", "member..." + e.message)
                            }
                        }
                        parentView.addView(companyMemberView, params)
                        companyMemberView.setData(companyDetailEntity)
                        /**
                         * part 5 主要产品
                         */


                        /**
                         * part 6 公司经营状况
                         */
                        //因为现在新公司这个情况很少纰漏，现在不做这个功能
                        /**
                         * part 7 PWC点评
                         */

                        /**
                         * part 8 竞品推荐
                         */
                        if (action?.comp != null) {
                            try {
                                for (i in 0..action.comp.size - 1) {
                                    val item = CompanyDetailEntity.CompareCompany(
                                            action.comp[i].establishDate,
                                            action.comp[i].fullName,
                                            action.comp[i].industry,
                                            action.comp[i].location,
                                            action.comp[i].logo,
                                            action.comp[i].name,
                                            action.comp[i].round,
                                            action.comp[i].starreda
                                    )
                                    companyDetailEntity.compare.add(item)
                                }
                            } catch (e: Exception) {
                                Log.e("HLA", "compare..." + e.message)
                            }
                        }
                        parentView.addView(companyCompareView, params)
                        companyCompareView.setData(companyDetailEntity)
                    }
                })
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
                resources.displayMetrics).toInt()
    }

    private fun initTitleCardView(): View {
        return titleView
    }
}