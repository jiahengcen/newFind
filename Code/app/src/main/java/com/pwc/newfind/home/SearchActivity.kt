package com.pwc.newfind.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.pwc.newfind.R
import com.pwc.newfind.base.Application
import com.pwc.newfind.detail.CompanyDetailActivity
import com.pwc.newfind.net.RetrofitHelper
import com.pwc.searchview.CompanyListAdapter
import com.pwc.searchview.SearchView
import com.pwc.searchview.bean.CompanyTitleListBean
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by lhuang126 on 1/13/2018.
 */
class SearchActivity : AppCompatActivity() {
    private var searchView: SearchView? = null
    var sub: Subscription? = null
    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. 绑定视图
        setContentView(R.layout.search_activity)

        // 3. 绑定组件
        searchView = findViewById<SearchView>(R.id.search_view)

        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView!!.setOnClickSearch { string -> println("我收到了" + string) }

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView!!.setOnClickBack { finish() }
        searchView!!.setOnSearchListener { word ->
            Log.e("HLA", "word" + word)
            sub = RetrofitHelper.getInstance(Application.appContext)
                    .server
                    .companySearchResult(Application.getInstances().userToken, word!!)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<CompanyTitleListBean> {
                        override fun onError(e: Throwable?) {
                            Log.e("HLA", "onError" + e!!.message)
                        }

                        override fun onCompleted() {
                            Log.e("HLA", "onCompleted")
                        }

                        override fun onNext(t: CompanyTitleListBean?) {
                            val companyAdapter = CompanyListAdapter()
                            companyAdapter.setData(t!!.result)
                            searchView!!.setCompanyAdapter(companyAdapter)
                        }

                    })
        }

        searchView!!.setCompanyOnItemClickListener(SearchView.OnCompanyItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long, bean: CompanyTitleListBean.CompanyTitleSubBean ->
            Log.e("HLA", "onClick" + bean.fullName)
            val intent = Intent(this@SearchActivity, CompanyDetailActivity::class.java)
            intent.putExtra(CompanyDetailActivity.COMPANY_CODE, bean.fullName)
            startActivity(intent)

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (sub != null && sub!!.isUnsubscribed) {
            sub!!.unsubscribe()
        }
    }
}


