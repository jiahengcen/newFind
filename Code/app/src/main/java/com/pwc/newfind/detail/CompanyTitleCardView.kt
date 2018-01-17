package com.pwc.newfind.detail

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pwc.newfind.FluidLayout
import com.pwc.newfind.R
import com.pwc.newfind.entity.CompanyDetailEntity

/**
 * Created by lhuang126 on 1/10/2018.
 */
class CompanyTitleCardView constructor(context: Context) : FrameLayout(context) {
    private val titleIcon: ImageView by lazy { findViewById<ImageView>(R.id.titleIcon) }
    private val title: TextView by lazy { findViewById<TextView>(R.id.title) }
    private val favourite: TextView by lazy { findViewById<TextView>(R.id.favourite) }
    private val round: TextView by lazy { findViewById<TextView>(R.id.round) }
    private val establishDate: TextView by lazy { findViewById<TextView>(R.id.establishDate) }
    private val location: TextView by lazy { findViewById<TextView>(R.id.location) }
    private val industry: TextView by lazy { findViewById<TextView>(R.id.industry) }
    private val tags: FluidLayout by lazy { findViewById<FluidLayout>(R.id.tags) }

    init {
        this.addView(LayoutInflater.from(context).inflate(R.layout.company_title_card_view, this, false))
    }

    /**
     * data 0: imageUrl
     * data 1:title
     * data 2:favourite
     * data 3:round
     * data 4:establishDate
     * data 5:location
     * data 6:industry
     *
     */
    fun setData(entity: CompanyDetailEntity) {
        setTitleImage(entity.logo)
        title.text = entity.name
        favourite.text = ""
        round.text = entity.round
        establishDate.text = entity.establishDate
        location.text = entity.location
        industry.text = entity.industry
        setTags(entity)

    }

    private fun setTags(entity: CompanyDetailEntity) {
        tags.removeAllViews()
        tags.setGravity(Gravity.TOP)
        for (i in 0..entity.tags!!.size - 1) {
            val tv = TextView(context)
            tv.text = entity.tags!![i]
            tv.textSize = 13f
            tv.setPadding(dp2px(12), 2, dp2px(12), 2)
            tv.setBackgroundResource(R.drawable.text_grey_bg)
            val params = FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 12, 24, 12)
            tags.addView(tv, params)
        }

    }

    private fun setTitleImage(imageUrl: String?) {
        Glide.with(this.context).load(imageUrl).into(titleIcon)
    }

    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
                resources.displayMetrics).toInt()
    }
}