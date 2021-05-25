package ru.sukhikh.couponcharts.utils;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.List;

public class BarChartBuilder {

    private final ColorHelper colorHelper = new ColorHelper();

    public void build(HorizontalBarChart barChart, List<BarEntry> dataSet, List<String> labels) {

        BarDataSet barDataSet = new BarDataSet(dataSet, "Label");
        barDataSet.setValueTextSize(17f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labels.size());

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setScaleEnabled(false);
        colorHelper.setupGradient(barChart);

        barChart.setData(barData);
        barChart.setVisibleXRangeMaximum(15);
        barChart.animate();
        barChart.invalidate();
    }


}
