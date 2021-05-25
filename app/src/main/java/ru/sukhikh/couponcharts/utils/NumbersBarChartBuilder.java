package ru.sukhikh.couponcharts.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.List;

public class NumbersBarChartBuilder {

    private final ColorHelper colorHelper = new ColorHelper();

    public void build(BarChart barChart, List<BarEntry> dataSet, List<String> labels) {
        BarDataSet barDataSet = new BarDataSet(dataSet, "Label");
        barDataSet.setValueTextSize(17f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(300);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getLegend().setEnabled(false);
        barChart.setScaleEnabled(false);
        colorHelper.setupGradient(barChart);

        barChart.setData(barData);
        barChart.setVisibleXRangeMaximum(8);
        barChart.animate();
        barChart.invalidate();
    }
}
