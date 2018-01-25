package com.pwc.newfind


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pwc.newfind.bean.NewsBean
import com.pwc.newfind.bean.SubscriptionBean
import com.pwc.newfind.net.RetrofitHelper
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by lhuang126 on 1/10/2018.
 */
class SubscriptionFragment : Fragment() {
    private lateinit var viewContent: ViewGroup
    val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        viewContent.findViewById<SwipeRefreshLayout>(R.id.main_srl)
    }
    val recycler: RecyclerView by lazy {
        viewContent.findViewById<RecyclerView>(R.id.recyclerView)
    }
    val adapter: SubscriptionAdapter by lazy {

        SubscriptionAdapter(arrayListOf())
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewContent = inflater!!.inflate(R.layout.subscription_fragment, container, false) as ViewGroup
        recycler.layoutManager = LinearLayoutManager(container!!.context)
        adapter.context = activity
        recycler.adapter = adapter
        return viewContent
    }

    private fun load() {
        RetrofitHelper.getInstance(activity)
                .server
                .getNews(Application.getInstances().userToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<NewsBean>() {
                    override fun onCompleted() {
                    }

                    override fun onNext(t: NewsBean) {
                        val dataList = mutableListOf<SubscriptionBean>()
                        for (item in t.news!!) {
                            val data = SubscriptionBean()
                            data.fullName = item.fullName
                            data.title = item.name
                            data.content = item.title
                            data.time = item.publish_date
                            data.iconUrl = item.logo
                            data.webSite=item.url
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