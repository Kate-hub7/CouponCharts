package ru.sukhikh.couponcharts.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.List;

public class LineChartBuilder {

    private final ColorHelper colorHelper = new ColorHelper();

    public void build(LineChart lineChart, List<Entry> dataSet, List<String> labels) {
        LineDataSet lineDataSet = new LineDataSet(dataSet, "Label");
        colorHelper.setupGradient(lineChart);
        lineDataSet.setValueTextSize(17f);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labels.size());
        xAxis.setLabelCount(labels.size());
        xAxis.setLabelRotationAngle(300);

        LineData data = new LineData(lineDataSet);
        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setScaleEnabled(false);

        lineChart.setData(data);
        lineChart.setVisibleXRangeMaximum(10);
        lineChart.animate();
        lineChart.invalidate();
    }
}
