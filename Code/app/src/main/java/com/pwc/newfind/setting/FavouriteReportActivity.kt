package com.pwc.newfind.setting

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.pwc.newfind.R
import com.pwc.newfind.base.Application
import com.pwc.newfind.bean.ResearchBean
import com.pwc.newfind.entity.ResearchEntity
import com.pwc.newfind.net.RetrofitHelper
import com.pwc.newfind.report.ResearchAdapter
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by lhuang126 on 2/26/2018.
 */
class FavouriteReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_fragment)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter.context = this
        recycler.adapter = adapter
        load()
    }

    val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.main_srl)
    }
    val recycler: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }
    val adapter: ResearchAdapter by lazy {
        ResearchAdapter(arrayListOf())
    }


    private fun load() {
        RetrofitHelper.getInstance(this)
                .server
                .getReport(Application.getInstances().userToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ResearchBean>() {
                    override fun onCompleted() {
                    }

                    override fun onNext(t: ResearchBean) {
                        val dataList = mutableListOf<ResearchEntity>()
                        for (item in t.research!!) {
                            val data = ResearchEntity()
                            data.id = item.id
                            data.attachment = item.attachment
                            data.link = item.link
                            data.source = item.source
                            data.time = item.time
                            data.title = item.title
                            data.attachment = item.attachment
                            data.starred = item.starred
                            dataList.add(data)
                        }
                        adapter.subDataList = dataList
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {
                        Log.e("HLA", "getNews error. e:" + e.message)
                    }

                })
    }
}