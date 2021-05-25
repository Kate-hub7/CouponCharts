package ru.sukhikh.couponcharts.utils;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.github.mikephil.charting.charts.Chart;

public class ColorHelper {

    public void setupGradient(Chart mChart) {
        Paint paint = mChart.getRenderer().getPaintRender();
        int width = mChart.getWidth();

        LinearGradient linGrad = new LinearGradient(0f, 0f, width, 0f,
                Color.CYAN,
                Color.BLUE,
                Shader.TileMode.REPEAT);
        paint.setShader(linGrad);
    }
}
