package com.pwc.newfind.detail;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.pwc.newfind.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by lhuang126 on 1/22/2018.
 */

public class TimeAxisValueFormatter implements IAxisValueFormatter {
    private String startDay;

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    TimeAxisValueFormatter(String startDay) {
        this.startDay = startDay;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try {
            return TimeUtil.getDayString(new SimpleDateFormat("yyyyMM"), startDay, (int) value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf((int) value);
    }
}
