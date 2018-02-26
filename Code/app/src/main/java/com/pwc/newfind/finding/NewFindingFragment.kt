package com.pwc.newfind.finding

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.pwc.newfind.R
import com.pwc.newfind.Test
import com.pwc.newfind.entity.FindingTitleEntity
import com.pwc.newfind.util.Preference
import android.animation.ObjectAnimator
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.SimpleAdapter
import com.pwc.newfind.base.Application
import com.pwc.newfind.bean.FindingCompanyBean
import com.pwc.newfind.bean.FindingTitleBean
import com.pwc.newfind.net.RetrofitHelper
import com.pwc.searchview.bean.CompanyTitleListBean
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList


/**
 * Created by lhuang126 on 1/19/2018.
 */
class NewFindingFragment : Fragment() {
    private var findingTitle by Preference("FindingTitle", "")
    private var findingFilter by Preference("FindingFilter", "")
    private lateinit var viewGroup: ViewGroup
    private val titleView by lazy {
        viewGroup.findViewById<TextView>(R.id.title)
    }
    private val titleLayout by lazy {
        viewGroup.findViewById<LinearLayout>(R.id.title_layout)
    }
    private val listTitleLayout by lazy {
        viewGroup.findViewById<CardView>(R.id.list_title_card)
    }
    private val listTitle by lazy {
        viewGroup.findViewById<ListView>(R.id.list_title)
    }
    private val listContent by lazy {
        viewGroup.findViewById<ListView>(R.id.list_content)
    }
    private val progressBar by lazy {
        viewGroup.findViewById<ProgressBar>(R.id.loading_progress)
    }
    private val companyAdapter by lazy {
        CompanyListAdapter()
    }
    private var titleListEntity = FindingTitleEntity()
    private lateinit var sampleAdapter: SimpleAdapter
    private val listMap: MutableList<Map<String, Any>> = mutableListOf() //定义一个适配器对象
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewGroup = inflater.inflate(R.layout.new_finding_fragment_layout, container, false) as ViewGroup
        if (findingFilter.length > 2) {
            loadCompanyList(findingFilter, findingTitle, false)
        } else {
            loadCompanyListDefault()
        }
        titleLayout.setOnClickListener({

            val animator = ObjectAnimator.ofFloat(listTitleLayout, "translationY", (listTitleLayout.measuredHeight).toFloat(), 0F)
            animator.duration = 1000
            animator.start()
            sampleAdapter = SimpleAdapter(activity, listMap, R.layout.simple_list_item, arrayOf("title"), intArrayOf(android.R.id.text1))
            listTitle.setOnItemClickListener { _, _, _, id ->
                loadCompanyList(titleListEntity.tileList[id.toInt()].filter!!, titleListEntity.tileList[id.toInt()].title!!, true)
            }
            loadTitleList()
            listTitle.adapter = sampleAdapter
            listContent.visibility = View.INVISIBLE
            listTitleLayout.visibility = View.VISIBLE
        })
        Handler().postDelayed({ progressBar.visibility = View.GONE }, 5000)
        return viewGroup
    }

    private fun loadCompanyList(filter: String, title: String, needAnimator: Boolean) {
        findingTitle = title
        findingFilter = filter
        if (needAnimator) {
            val animator = ObjectAnimator.ofFloat(listTitleLayout, "translationY", 0F, (listTitleLayout.measuredHeight).toFloat())
            animator.duration = 1000
            animator.start()
        } else {
            listTitleLayout.visibility = View.INVISIBLE
        }
        listContent.adapter = companyAdapter

        titleView.text = title
        RetrofitHelper.getInstance(activity)
                .server
                .getFindingTitles(Application.getInstances().userToken, filter)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<FindingCompanyBean> {
                    override fun onError(e: Throwable) {
                        Log.e("HLA", "getFindingTitle error:" + e.message)
                    }

                    override fun onCompleted() {
                    }

                    override fun onNext(t: FindingCompanyBean) {
                        val data: MutableList<CompanyTitleListBean.CompanyTitleSubBean> = mutableListOf()
                        for (item in t.result!!) {
                            val companyTitleSubBean = CompanyTitleListBean.CompanyTitleSubBean()
                            companyTitleSubBean.fullName = item.fullName
                            companyTitleSubBean.location = item.location
                            companyTitleSubBean.logo = item.logo
                            companyTitleSubBean.name = item.name
                            companyTitleSubBean.round = item.round
                            companyTitleSubBean.establishDate = item.establishDate
                            companyTitleSubBean.starred = item.starred
                            data.add(companyTitleSubBean)
                        }
                        companyAdapter.setData(data as ArrayList<CompanyTitleListBean.CompanyTitleSubBean>)
                        companyAdapter.notifyDataSetChanged()
                    }

                })
    }

    private fun loadCompanyListDefault() {
        val animator = ObjectAnimator.ofFloat(listTitleLayout, "translationY", 0F, (listTitleLayout.measuredHeight).toFloat())
        animator.duration = 1000
        animator.start()
        listContent.adapter = companyAdapter
        //listTitle.visibility = View.GONE
        listContent.visibility = View.VISIBLE
        //listTitleLayout.visibility = View.GONE
        RetrofitHelper.getInstance(activity)
                .server
                .getFindingTitlesDefault(Application.getInstances().userToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<FindingCompanyBean> {
                    override fun onError(e: Throwable) {
                        Log.e("HLA", "getFindingTitle error:" + e.message)
                    }

                    override fun onCompleted() {
                    }

                    override fun onNext(t: FindingCompanyBean) {
                        val data: MutableList<CompanyTitleListBean.CompanyTitleSubBean> = mutableListOf()
                        for (item in t.result!!) {
                            val companyTitleSubBean = CompanyTitleListBean.CompanyTitleSubBean()
                            companyTitleSubBean.fullName = item.fullName
                            companyTitleSubBean.location = item.location
                            companyTitleSubBean.logo = item.logo
                            companyTitleSubBean.name = item.name
                            companyTitleSubBean.round = item.round
                            companyTitleSubBean.establishDate = item.establishDate
                            companyTitleSubBean.starred = item.starred
                            data.add(companyTitleSubBean)
                        }
                        companyAdapter.setData(data as ArrayList<CompanyTitleListBean.CompanyTitleSubBean>)
                        companyAdapter.notifyDataSetChanged()
                    }

                })
    }

//    private fun loadData(title: String) {
//
//
//        val animator = ObjectAnimator.ofFloat(listTitleLayout, "translationY", 0F, (listTitleLayout.measuredHeight).toFloat())
//        animator.duration = 1000
//        animator.start()
//        val arrayAdapter = ArrayAdapter<String>(activity, R.layout.simple_list_item, Test.getCompanyList())
//        listContent.setOnItemClickListener { _, _, _, id ->
//            //loadData(titleListEntity.tileList[id.toInt()])
//        }
//        listContent.adapter = arrayAdapter
//        //listTitle.visibility = View.GONE
//        listContent.visibility = View.VISIBLE
//        //listTitleLayout.visibility = View.GONE
//    }

    private fun loadTitleList() {
        RetrofitHelper.getInstance(activity)
                .server
                .getFindingTitleList(Application.getInstances().userToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<FindingTitleBean> {
                    override fun onError(e: Throwable) {
                        Log.e("HLA", "getFindingTitle error:" + e.message)
                    }

                    override fun onCompleted() {
                    }

                    override fun onNext(t: FindingTitleBean) {
                        listMap.clear()
                        for (item in t.filterList!!) {
                            val filterBean = FindingTitleEntity.FilterBean()
                            filterBean.filter = item.filter
                            filterBean.title = item.title
                            titleListEntity.tileList.add(filterBean)
                            val hashMap = HashMap<String, Any>()
                            hashMap.put("title", item.title!!)
                            listMap.add(hashMap)

                        }
                        sampleAdapter.notifyDataSetChanged()
                    }

                })
    }


}