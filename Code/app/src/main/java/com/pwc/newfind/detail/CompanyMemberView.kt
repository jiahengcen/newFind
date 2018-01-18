package com.pwc.newfind.detail

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
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
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.company_margin_card_view.view.*

/**
 * Created by lhuang126 on 1/17/2018.
 */
class CompanyMemberView(context: Context) : FrameLayout(context) {
    init {
        addView(LayoutInflater.from(context).inflate(R.layout.company_member_card_view, this, false))
    }

    fun setData(entity: CompanyDetailEntity) {
        if (entity.members.size > 0) {
            for (i in 0..entity.members.size - 1) {
                try {
                    val itemMemberView = LayoutInflater.from(context).inflate(R.layout.company_member_card_view_content, null, false)
                    Glide.with(context).load(entity.members[i].photo).into(itemMemberView.findViewById<CircleImageView>(R.id.photo))
                    itemMemberView.findViewById<TextView>(R.id.legalPerson).text = getLegalPerson(entity.members[i])
                    val detailTextView = itemMemberView.findViewById<TextView>(R.id.content)
                    val button = itemMemberView.findViewById<TextView>(R.id.button)
                    detailTextView.text = entity.members[i].description
                    button.tag = "close"
                    if (detailTextView.lineCount > 1) {
                        button.visibility = View.VISIBLE
                    } else {
                        button.visibility = View.GONE
                    }
                    button.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View?) {
                            if (button.tag == "close") {
                                detailTextView.maxLines = 100
                                button.tag = "open"
                                button.text = "收起"
                            } else {
                                detailTextView.maxLines = 1
                                button.tag = "close"
                                button.text = "展开"
                            }
                        }
                    })

                    layout_inform.addView(itemMemberView)
                    if (i != entity.members.size - 1) {
                        val line = MarginInformationView.LineView(context)
                        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        params.leftMargin = dp2px(8)
                        params.rightMargin = dp2px(8)
                        params.topMargin = dp2px(8)
                        params.bottomMargin = dp2px(8)
                        layout_inform.addView(line, params)
                    }
                } catch (e: Exception) {
                    Log.e("HLA", e.message)
                }

            }
        }
    }

    private fun getLegalPerson(member: CompanyDetailEntity.Member): String? {
        return member.name + "-" + member.position
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