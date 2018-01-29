package com.pwc.newfind.report

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwc.newfind.base.Application
import com.pwc.newfind.R
import com.pwc.newfind.bean.ResearchBean
import com.pwc.newfind.entity.ResearchEntity
import com.pwc.newfind.net.RetrofitHelper
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by lhuang126 on 1/19/2018.
 */
class ResearchFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewContent = inflater.inflate(R.layout.report_fragment, container, false) as ViewGroup
        recycler.layoutManager = LinearLayoutManager(container!!.context)
        adapter.context = activity
        recycler.adapter = adapter
        return viewContent

    }

    private lateinit var viewContent: ViewGroup
    val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        viewContent.findViewById<SwipeRefreshLayout>(R.id.main_srl)
    }
    val recycler: RecyclerView by lazy {
        viewContent.findViewById<RecyclerView>(R.id.recyclerView)
    }
    val adapter: ResearchAdapter by lazy {
        ResearchAdapter(arrayListOf())
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load()
    }


    private fun load() {
        RetrofitHelper.getInstance(activity)
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
                            data.starred=item.starred
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