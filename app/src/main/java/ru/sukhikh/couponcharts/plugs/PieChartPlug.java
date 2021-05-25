package ru.sukhikh.couponcharts.plugs;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChartPlug {

    public List<PieEntry> userPlug(){
        List<PieEntry> list = new ArrayList<>();
        list.add(new PieEntry(70, "android"));
        list.add(new PieEntry(116, "ios"));

        return list;
    }
}
