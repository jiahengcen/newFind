package com.pwc.newfind.report

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pwc.newfind.R
import com.pwc.newfind.entity.ResearchEntity
import android.widget.Toast
import android.content.ActivityNotFoundException


/**
 * Created by lhuang126 on 1/10/2018.
 */
class ResearchAdapter constructor(subList: MutableList<ResearchEntity>) : RecyclerView.Adapter<ResearchAdapter.ResearchViewHolder>() {
    var context: Context? = null
    var subDataList: MutableList<ResearchEntity> = subList
    override fun onBindViewHolder(holder: ResearchViewHolder, position: Int) {
        holder.title.text = subDataList[position].title
        holder.time.text = getTime(subDataList[position].time)
        holder.title.setOnClickListener({ view ->
            val uri = Uri.parse(subDataList[position].link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            view.context.startActivity(intent)
        })
        if (subDataList[position].starred!!) {
            holder.favourite.setImageResource(R.drawable.ic_grade)
            holder.favourite.tag = "select"
        } else {
            holder.favourite.setImageResource(R.drawable.ic_grade_unselect)
            holder.favourite.tag = "unSelect"
        }
        holder.source.text = subDataList[position].source
        holder.favourite.setOnClickListener({ view ->
            if (holder.favourite.tag == "select") {
                // RetrofitHelper.getInstance(view.context).server.actionStarCompany()

                holder.favourite.setImageResource(R.drawable.ic_grade_unselect)
                holder.favourite.tag = "unSelect"
            } else {
                holder.favourite.setImageResource(R.drawable.ic_grade)
                holder.favourite.tag = "select"
            }
        })
        holder.pdf.setOnClickListener(View.OnClickListener { view ->
            try {
                view.context.startActivity(getPdfFileIntent(subDataList[position].attachment!!))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(view.context,
                        "No Application Available to View PDF",
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getTime(time: String?): String? {
        return time
    }

    override fun getItemCount(): Int {
        return subDataList.size
    }
    // 定义打开pdf的Intent，罗列出可以打开pdf文件的APP

    /**
     * android获取一个用于打开PDF文件的intent
     * @param path 要打开的文件的绝对路径
     * @return
     */
    private fun getPdfFileIntent(path: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.parse(path)
        intent.setDataAndType(uri, "application/pdf")
        return intent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchViewHolder {
        return ResearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.research_content, parent, false))
    }

    class ResearchViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView!!.findViewById(R.id.title)
        val favourite: ImageView = itemView!!.findViewById(R.id.favourite)
        val pdf: ImageView = itemView!!.findViewById(R.id.pdf)
        val time: TextView = itemView!!.findViewById(R.id.time)
        val source: TextView = itemView!!.findViewById(R.id.source)
    }
}