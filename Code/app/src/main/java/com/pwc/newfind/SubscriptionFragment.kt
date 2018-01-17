package com.pwc.newfind

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewContent = inflater!!.inflate(R.layout.subscription_fragment, container, false) as ViewGroup
        recycler.layoutManager = LinearLayoutManager(container!!.context)
        recycler.adapter = SubscriptionAdapter(TestSubscriptionBean().getListSubscriptionBean())
        recycler.adapter.notifyDataSetChanged()
        return viewContent
    }
}