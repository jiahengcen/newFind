package com.pwc.newfind.detail

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.pwc.newfind.R
import com.pwc.newfind.entity.CompanyDetailEntity
import com.pwc.newfind.util.TimeUtil
import kotlinx.android.synthetic.main.company_alexa_card_view.view.*
import java.text.ParseException
import java.util.ArrayList

/**
 * Created by lhuang126 on 1/22/2018.
 */
class CompanyAlexaView(context: Context) : FrameLayout(context) {
    private var lineName: String? = null
    private var lineName1: String? = null

    init {
        addView(LayoutInflater.from(context).inflate(R.layout.company_alexa_card_view, this, false))
        initDataStyle(context, chart)
    }

    fun setData(entity: CompanyDetailEntity) {
        try {
            val x = entity.alexa!!.x
            val YList = entity.alexa!!.y
            var cnY: MutableList<Int>? = null
            //  var allY: MutableList<Int>? = null
            if (YList.isNotEmpty()) {
                for (i in YList.indices) {
                    if ("中国" == YList[i].scope) {
                        cnY = YList[i].rank
                    } else if ("全球" == YList[i].scope) {
                        //            allY = YList[i].rank
                    }
                }
            }
            val cnValue = mutableListOf<Entry>()
            //   val allValue = mutableListOf<Entry>()
            for (i in x!!.indices) {

                cnValue.add(Entry(TimeUtil.differentDays(x[0].toString(), x[i].toString()).toFloat(), cnY!![i].toFloat()))
                //      allValue.add(Entry(TimeUtil.differentDays(x[0].toString(), x[i].toString()).toFloat(), allY!![i].toFloat()))
            }
            Log.e("HLA", "" + x.size)
            //设置描述信息
            chart.description.text = ""
            //设置X轴显示内容
            val xAxis = chart.xAxis
            xAxis.valueFormatter = TimeAxisValueFormatter(x[0].toString())
            initSingleLineChart(context, chart, cnValue as ArrayList<Entry>)
        } catch (e: ParseException) {
            Log.e("HLA", e.message)
        }

    }

    /**
     * @Description:初始化图表的样式
     * @param context
     * @param mLineChart
     */
    private fun initDataStyle(context: Context, mLineChart: LineChart) {
        //设置图表是否支持触控操作
        mLineChart.setTouchEnabled(false)
        mLineChart.setScaleEnabled(false)
        //设置点击折线点时，显示其数值
        //        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
        //        mLineChart.setMarkerView(mv);
        //设置折线的描述的样式（默认在图表的左下角）
        val title = mLineChart.legend
        title.form = Legend.LegendForm.LINE
        //设置x轴的样式
        val xAxis = mLineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisLineColor = Color.parseColor("#66CDAA")
        xAxis.setAxisLineWidth(5F)
        xAxis.setDrawGridLines(false)

        //设置是否显示x轴
        xAxis.setEnabled(true)

        //设置左边y轴的样式
        val yAxisLeft = mLineChart.getAxisLeft()
        yAxisLeft.setAxisLineColor(Color.parseColor("#66CDAA"))
        yAxisLeft.setAxisLineWidth(5F)
        yAxisLeft.setDrawGridLines(false)

        //设置右边y轴的样式
        val yAxisRight = mLineChart.getAxisRight()
        yAxisRight.setEnabled(false)


    }

    /**
     * @Description:创建两条折线
     * @param context 上下文
     * @param mLineChart 折线图控件
     * @param xValues 折线在x轴的值
     * @param yValue 折线在y轴的值
     * @param yValue1 另一条折线在y轴的值
     */
    fun initDoubleLineChart(context: Context, mLineChart: LineChart,
                            yValue: ArrayList<Entry>, yValue1: ArrayList<Entry>) {

        initDataStyle(context, mLineChart)

        val dataSet = LineDataSet(yValue, lineName)
        dataSet.color = Color.RED
        dataSet.setCircleColor(Color.RED)
        dataSet.setDrawValues(false)

        val dataSet1 = LineDataSet(yValue1, lineName1)
        dataSet1.enableDashedLine(10f, 10f, 0f)//将折线设置为曲线
        dataSet1.color = Color.parseColor("#66CDAA")
        dataSet1.setCircleColor(Color.parseColor("#66CDAA"))
        dataSet1.setDrawValues(false)

        //构建一个类型为LineDataSet的ArrayList 用来存放所有 y的LineDataSet   他是构建最终加入LineChart数据集所需要的参数
        val dataSets = ArrayList<ILineDataSet>()

        //将数据加入dataSets
        dataSets.add(dataSet)
        dataSets.add(dataSet1)

        //构建一个LineData  将dataSets放入
        //val lineData = LineData(xValues, dataSets)
        val lineData = LineData(dataSets)

        //将数据插入
        mLineChart.data = lineData
        //设置动画效果
        //mLineChart.animateY(2000, Easing.EasingOption.Linear)
        //mLineChart.animateX(2000, Easing.EasingOption.Linear)
        mLineChart.invalidate()
    }

    fun initSingleLineChart(context: Context, mLineChart: LineChart,
                            yValue: ArrayList<Entry>) {

        initDataStyle(context, mLineChart)

        val dataSet = LineDataSet(yValue, lineName)
        dataSet.color = Color.RED
        dataSet.setCircleColor(Color.RED)
        dataSet.setDrawValues(false)


        //构建一个类型为LineDataSet的ArrayList 用来存放所有 y的LineDataSet   他是构建最终加入LineChart数据集所需要的参数
        val dataSets = ArrayList<ILineDataSet>()

        //将数据加入dataSets
        dataSets.add(dataSet)


        //构建一个LineData  将dataSets放入
        //val lineData = LineData(xValues, dataSets)
        val lineData = LineData(dataSets)

        //将数据插入
        mLineChart.data = lineData
        //设置动画效果
        //mLineChart.animateY(2000, Easing.EasingOption.Linear)
        //mLineChart.animateX(2000, Easing.EasingOption.Linear)
        mLineChart.invalidate()
    }
}