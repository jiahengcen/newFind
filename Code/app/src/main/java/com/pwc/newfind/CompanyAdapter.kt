package com.pwc.newfind

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pwc.newfind.entity.CompanyAdapterEntity

/**
 * Created by lhuang126 on 1/15/2018.
 */
class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {
    var data: ArrayList<CompanyAdapterEntity> = arrayListOf()
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        Glide.with(holder!!.itemView.context).load(data[position].logoString).into(holder.logo);
        holder.textView.text = data[position].companyName
        holder.selectLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                data[position].select = !data[position].select
                if (data[position].select) {
                    holder.selectLayout.setBackgroundColor(holder.itemView.resources.getColor(R.color.colorPrimary))
                    holder.textView.setTextColor(holder.itemView.resources.getColor(R.color.white))
                    holder.select.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.ic_check))
                } else {
                    holder.selectLayout.setBackgroundColor(holder.itemView.resources.getColor(R.color.lightgrey))
                    holder.textView.setTextColor(holder.itemView.resources.getColor(R.color.black))
                    holder.select.setImageDrawable(holder.itemView.resources.getDrawable(R.drawable.ic_add))
                }
            }

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.company_pick_content, parent, false))
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val logo: ImageView = view.findViewById(R.id.logo)
        val textView: TextView = view.findViewById(R.id.text)
        val select: ImageView = view.findViewById(R.id.select)
        val selectLayout: LinearLayout = view.findViewById(R.id.select_layout)
    }

    fun reSetData(listCompanyAdapterBean: ArrayList<CompanyAdapterEntity>) {
        data = listCompanyAdapterBean
        notifyDataSetChanged()
    }
}