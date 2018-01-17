package com.pwc.newfind

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pwc.newfind.bean.SubscriptionBean

/**
 * Created by lhuang126 on 1/10/2018.
 */
class SubscriptionAdapter constructor(subList: ArrayList<SubscriptionBean>) : RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder>() {
    private var subDataList: ArrayList<SubscriptionBean> = subList
    override fun onBindViewHolder(holder: SubscriptionViewHolder?, position: Int) {
        holder!!.title.text = subDataList[position].title
        holder.subTitle.text = subDataList[position].subTitle
        holder.time.text = subDataList[position].time
        holder.content.text = subDataList[position].content
        holder.viewGroup!!.setOnClickListener(View.OnClickListener { view ->
//            val intent = Intent()
//            intent.setClass(view.context, CompanyDetailActivity::class.java)
//            view.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return subDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SubscriptionViewHolder {
        return SubscriptionViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.subscription_content, parent, false))
    }

    class SubscriptionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val viewGroup: ViewGroup? = itemView as ViewGroup
        val titleIcon: ImageView = itemView!!.findViewById(R.id.titleIcon)
        val title: TextView = itemView!!.findViewById(R.id.title)
        val subTitle: TextView = itemView!!.findViewById(R.id.sub_title)
        val time: TextView = itemView!!.findViewById(R.id.time)
        val content: TextView = itemView!!.findViewById(R.id.content)
    }
}