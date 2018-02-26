package com.pwc.newfind.view

/**
 * Created by lhuang126 on 2/26/2018.
 */
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class MyDecoration constructor(context: Context, orientation: Int) : RecyclerView.ItemDecoration() {
    private var mContext: Context? = null
    private var mDivider: Drawable? = null
    private var mOrientation = LinearLayoutManager.HORIZONTAL
    private val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
    private val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    val ATRRS = intArrayOf(android.R.attr.listDivider)

    init {
        this.mContext = context
        val ta = context.obtainStyledAttributes(ATRRS)
        this.mDivider = ta.getDrawable(0)
        ta.recycle()
        setOrientation(orientation)
    }

    //设置屏幕的方向
    private fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw  IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation;
    }

    override
    fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalLine(c, parent, state)
        } else {
            drawHorizontalLine(c, parent, state)
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    fun drawHorizontalLine(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.getPaddingLeft()
        val right = parent.getWidth() - parent.getPaddingRight()
        val childCount = parent.childCount
        var j = 0;
        while (j < childCount) {
            val child = parent.getChildAt(j)
            //获得child的布局信息
            val params = child!!.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom); mDivider!!.draw(c)
            j++
            //Log.d("wnw", left + " " + top + " "+right+" "+bottom+" "+i);
        }
    }

    //画竖线
    public fun drawVerticalLine(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount
        var j = 0;
        while (j < childCount) {
            val child = parent.getChildAt(j)
            //获得child的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.getRight() + params.rightMargin
            val right = left + mDivider!!.intrinsicWidth
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
            j++
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == HORIZONTAL_LIST) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, mDivider!!.intrinsicWidth, 0)
        }
    }
}


