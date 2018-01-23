package com.pwc.newfind

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.pwc.newfind.bean.FavouriteIndustryBean
import com.pwc.newfind.bean.IndustryListBean
import com.pwc.newfind.net.Helper
import com.pwc.newfind.net.RetrofitHelper
import kotlinx.android.synthetic.main.company_favourite_activity.*
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

/**
 * Created by lhuang126 on 1/23/2018.
 */
class FavouriteIndustryActivity : AppCompatActivity(), View.OnClickListener, IndustryAdapter.OnSelectListener {
    private val retrofit by lazy {
        RetrofitHelper.getInstance(this)
    }
    val service by lazy { retrofit.server }

    private val tvNext: TextView by lazy { findViewById<TextView>(R.id.save) }
    private var industryNet: Subscription? = null
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val industryAdapter = IndustryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = Color.rgb(0xC5, 0x2A, 0x1A)
        setContentView(R.layout.industry_favourite_activty)
        setSupportActionBar(toolbar)
        loadData()
        tvNext.setOnClickListener(this)
    }

    private fun loadData() {
        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        industryAdapter.setHasOneSelectListener(this)
        recyclerView.adapter = industryAdapter
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
        service.getStarIndustry(Application.getInstances().userToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<FavouriteIndustryBean> {
                    override fun onCompleted() {
                        Log.e("HLA", "onCompleted")
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("HLA", "onError")
                    }

                    override fun onNext(t: FavouriteIndustryBean) {
                        var selectDataSet: MutableSet<String> = mutableSetOf()
                        for (item in t.starIndustryList!!) {
                            selectDataSet.add(item)
                        }
                        industryAdapter.reSetSelectData(selectDataSet)
                    }

                })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.save -> {
                var date = mutableListOf<String>()
                industryAdapter.selectDataSet.forEach { date.add(it) }
                Helper.actionStarIndustry(this, date as ArrayList<String>, "override")
                finish()
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