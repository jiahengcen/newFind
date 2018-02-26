package com.pwc.newfind.detail

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pwc.newfind.R
import com.pwc.newfind.entity.CompanyDetailEntity
import com.pwc.newfind.net.Helper
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.company_margin_card_view.view.*

/**
 * Created by lhuang126 on 1/18/2018.
 */
class CompanyCompareView(context: Context) : FrameLayout(context) {
    init {
        addView(LayoutInflater.from(context).inflate(R.layout.company_compare_card_view, this, false))
    }

    fun setData(entity: CompanyDetailEntity) {
        if (entity.compare.size > 0) {
            for (i in 0..entity.compare.size - 1) {
                val itemMemberView = LayoutInflater.from(context).inflate(R.layout.company_compare_card_view_content, null, false)
                Glide.with(context).load(entity.compare[i].logo).into(itemMemberView.findViewById<CircleImageView>(R.id.titleIcon))
                itemMemberView.findViewById<TextView>(R.id.title).text = entity.compare[i].name
                itemMemberView.findViewById<TextView>(R.id.fullCompanyName).text = entity.compare[i].fullName
                val button = itemMemberView.findViewById<TextView>(R.id.favourite)
                button.tag = "true"
                if (entity.compare[i].starred ?: false) {
                    button.text = "已关注"
                } else {
                    button.text = "关注"
                }
                button.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        if (button.tag == "true") {
                            button.tag = "false"
                            button.text = "关注"
                            Helper.actionStarCompany(context, entity.fullName, "delete")
                        } else {
                            button.tag = "true"
                            button.text = "已关注"
                            Helper.actionStarCompany(context, entity.fullName, "insert")
                        }
                    }
                })
                itemMemberView.findViewById<TextView>(R.id.round).text = entity.compare[i].round
                itemMemberView.findViewById<TextView>(R.id.establishDate).text = entity.compare[i].establishDate
                itemMemberView.findViewById<TextView>(R.id.location).text = entity.compare[i].location
                layout_inform.addView(itemMemberView)
                if (i != entity.members.size - 1) {
                    val line = LineView(context)
                    val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    params.leftMargin = dp2px(8)
                    params.rightMargin = dp2px(8)
                    params.topMargin = dp2px(8)
                    params.bottomMargin = dp2px(8)
                    layout_inform.addView(line, params)
                }
            }
        }
    }

    class LineView(context: Context) : View(context) {
        private val paint = Paint()

        init {
            paint.color = Color.rgb(0XF2, 0XF2, 0XF2)
            paint.style = Paint.Style.STROKE

        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            paint.strokeWidth = dp2px(4).toFloat()
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), dp2px(2))
        }

        private fun dp2px(dp: Int): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
                    resources.displayMetrics).toInt()
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            canvas?.drawLine(0F, 0F, measuredWidth.toFloat(), measuredHeight.toFloat(), paint)
        }
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
                resources.displayMetrics).toInt()
    }
}