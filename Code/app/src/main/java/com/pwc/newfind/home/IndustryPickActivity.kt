package com.pwc.newfind.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.pwc.newfind.NetService
import com.pwc.newfind.R
import com.pwc.newfind.bean.IndustryListBean
import com.pwc.newfind.net.Constant
import com.pwc.newfind.net.Helper
import com.pwc.newfind.net.RetrofitHelper
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers

import rx.schedulers.Schedulers
import java.util.ArrayList

/**
 * Created by lhuang126 on 1/9/2018.
 */
class IndustryPickActivity : AppCompatActivity(), View.OnClickListener, IndustryAdapter.OnSelectListener {
    private val retrofit by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.host)
                .build()
    }
    val service by lazy { retrofit.create(NetService::class.java) }
    private val btnNext: LinearLayout by lazy { findViewById<LinearLayout>(R.id.next_layout) }
    private val tvNext: TextView by lazy { findViewById<TextView>(R.id.next_text) }
    private var industryNet: Subscription? = null
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val industryAdapter = IndustryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.industry_pick_activty)
        loadData()
        btnNext.setOnClickListener(this)
    }

    private fun loadData() {
        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        industryAdapter.setHasOneSelectListener(this)
        recyclerView.adapter = industryAdapter
        recyclerView.adapter.notifyDataSetChanged()
        RetrofitHelper.getInstance(this.applicationContext)
        industryNet = service.industryList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<IndustryListBean> {
                    override fun onCompleted() {
                        Log.e("HLA", "onCompleted")
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("HLA", "onError")
                    }

                    override fun onNext(t: IndustryListBean?) {
                        Log.e("HLA", "ttt" + t!!.industryList)
                        industryAdapter.reSetData(t!!.industryList as MutableList<String>)
                    }

                })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.next_layout -> {
                var date = mutableListOf<String>()
                industryAdapter.selectDataSet.forEach { date.add(it) }
                Helper.actionStarIndustry(this, date as ArrayList<String>, "override")
                val intent: Intent = Intent()
                intent.setClass(this, CompanyPickActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onSelected(select: Boolean) {
//        btnNext.isClickable = select
//        btnNext.isFocusable = select
//        if (select) {
//            tvNext.setTextColor(this.resources.getColor(R.color.colorPrimary))
//        } else {
//            tvNext.setTextColor(this.resources.getColor(R.color.darkgrey))
//
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (industryNet!!.isUnsubscribed) {
            industryNet!!.unsubscribe()
        }

    }
}

