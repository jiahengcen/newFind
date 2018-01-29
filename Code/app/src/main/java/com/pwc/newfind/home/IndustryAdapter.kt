package com.pwc.newfind.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pwc.newfind.R

/**
 * Created by lhuang126 on 1/9/2018.
 */
class IndustryAdapter constructor(array: MutableList<String>) : RecyclerView.Adapter<IndustryAdapter.IndustryViewHolder>() {
    constructor() : this(arrayListOf()) {
    }

    private var dataList: MutableList<String> = array
    private lateinit var listener: OnSelectListener

    var selectDataSet: MutableSet<String> = mutableSetOf()
    override fun onBindViewHolder(holder: IndustryViewHolder?, position: Int) {
        holder!!.textView.text = dataList[position]
        if (selectDataSet.any { it == (holder.textView.text.toString()) }) {
            holder.textView.setBackgroundResource(R.drawable.text_red_bg)
            holder.textView.setTextColor(holder.textView.context.resources.getColor(R.color.white))
        } else {
            holder.textView.setBackgroundResource(R.drawable.text_grey_bg)
            holder.textView.setTextColor(holder.textView.context.resources.getColor(R.color.black))
        }
        holder.textView.setOnClickListener {
            if (selectDataSet.any { it == (holder.textView.text.toString()) }) {
                selectDataSet.remove(holder.textView.text.toString())
                holder.textView.setBackgroundResource(R.drawable.text_grey_bg)
                holder.textView.setTextColor(holder.textView.context.resources.getColor(R.color.black))
            } else {
                selectDataSet.add(holder.textView.text.toString())
                holder.textView.setBackgroundResource(R.drawable.text_red_bg)
                holder.textView.setTextColor(holder.textView.context.resources.getColor(R.color.white))
            }
            if (selectDataSet.isEmpty()) {
                listener.onSelected(false)
            } else {
                listener.onSelected(true)
            }
        }
    }

    public fun reSetData(data: MutableList<String>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): IndustryViewHolder {
        return IndustryViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.industry_pick_content, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    public fun setHasOneSelectListener(l: OnSelectListener) {
        listener = l
    }

    interface OnSelectListener {
        fun onSelected(select: Boolean)
    }

    class IndustryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView!!.findViewById(R.id.text)
    }

    fun reSetSelectData(selectSet: MutableSet<String>) {
        selectDataSet = selectSet
        notifyDataSetChanged()

    }
}