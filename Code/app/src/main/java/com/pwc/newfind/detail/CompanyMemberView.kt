package com.pwc.newfind.detail

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.pwc.newfind.R
import com.pwc.newfind.entity.CompanyDetailEntity
import kotlinx.android.synthetic.main.company_margin_card_view.view.*

/**
 * Created by lhuang126 on 1/17/2018.
 */
class CompanyMemberView(context: Context) : FrameLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.company_member_card_view, this, false)
    }
    fun setData(entity: CompanyDetailEntity) {
        if (entity.marginInformation.size > 0) {
            for (i in 0..entity.marginInformation.size - 1) {
                layout_inform.addView(LayoutInflater.from(context).inflate(R.layout.company_margin_card_view_content, this, false))
                layout_inform.addView(LineView(context))
            }
        }
    }

    class LineView(context: Context) : View(context) {
        private val paint = Paint()

        init {
            paint.color = Color.GRAY
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 10f
        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            canvas?.drawLine(0F, 0F, measuredWidth.toFloat(), measuredHeight.toFloat(), paint)
        }
    }
}