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
import com.pwc.newfind.R
import com.pwc.newfind.entity.CompanyDetailEntity
import kotlinx.android.synthetic.main.company_margin_card_view.view.*

/**
 * Created by lhuang126 on 1/16/2018.
 */
class MarginInformationView(context: Context) : FrameLayout(context) {
    init {
        addView(LayoutInflater.from(context).inflate(R.layout.company_margin_card_view, this, false))
    }

    fun setData(entity: CompanyDetailEntity) {
        if (entity.marginInformation.size > 0) {
            for (i in 0..entity.marginInformation.size - 1) {
                val itemView = LayoutInflater.from(context).inflate(R.layout.company_margin_card_view_content, this, false)
                itemView.findViewById<TextView>(R.id.yyyy_mm).text = getYYYYMM(entity.marginInformation[i].fundingDate)
                itemView.findViewById<TextView>(R.id.round).text = entity.marginInformation[i].round
                itemView.findViewById<TextView>(R.id.money).text = getMoneyText(entity.marginInformation[i].investment)
                itemView.findViewById<TextView>(R.id.owner).text = getInvestorsText(entity.marginInformation[i].investors)
                layout_inform.addView(itemView)
                if (i != entity.marginInformation.size - 1) {
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

    private fun getYYYYMM(fundingDate: String?): String? {
        if (fundingDate != null && fundingDate.length > 7) {
            return fundingDate.substring(0, 7)
        }
        return fundingDate
    }

    /**
     * 	1000000转换成1百万等文字功能
     */
    private fun getMoneyText(investment: String?): String? {
        if (investment != null) {
            return investment
        }
        return investment
    }

    /**
     *
     */
    private fun getInvestorsText(investors: String?): String? {
        if (investors != null) {
            return investors
        }
        return investors
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