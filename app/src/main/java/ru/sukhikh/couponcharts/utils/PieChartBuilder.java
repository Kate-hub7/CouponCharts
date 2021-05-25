package ru.sukhikh.couponcharts.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChartBuilder {

    public void build(PieChart pieChart, List<PieEntry> dataSet) {

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);

        PieDataSet pieDataSet = new PieDataSet(dataSet, "Label");
        pieDataSet.setColors(Color.CYAN, Color.BLUE);
        pieDataSet.setValueTextColors(colors);
        pieDataSet.setValueTextSize(17f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawRoundedSlices(true);
        pieChart.animate();

        pieChart.invalidate();
    }
}
