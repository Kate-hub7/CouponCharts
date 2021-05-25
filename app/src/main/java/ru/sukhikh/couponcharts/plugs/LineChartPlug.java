package ru.sukhikh.couponcharts.plugs;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import static ru.sukhikh.couponcharts.models.DateModel.formatter;

public class LineChartPlug {

    public List<String> labelsPlug(){

        List<String> list = new ArrayList<>();
        list.add("gulliver.ru");
        list.add("La Roche-Posay");
        list.add("iCover");
        list.add("SUNLIGHT");
        list.add("s-centres.ru");
        list.add("\u0422\u0432\u043e\u0439 \u0434\u043e\u043c");
        list.add("\u041c.\u0412\u0438\u0434\u0435\u043e");
        list.add("\u041c\u0422\u0421");
        list.add("VK Combo");
        list.add("\u0417\u043e\u043e\u041f\u0430\u0441\u0441\u0430\u0436");
        list.add("Tefal");
        list.add("AliExpress");
        list.add("\u0411\u0438\u043b\u0430\u0439\u043d");
        list.add("\u041b\u0438\u0442\u0420\u0435\u0441");
        list.add("rigla.ru");
        list.add("\u041c\u0430\u043a\u0434\u043e\u043d\u0430\u043b\u0434\u0441");
        list.add("FoodBand");
        list.add("IVI");
        list.add("Roxy");
        list.add("\u042f\u043d\u0434\u0435\u043a\u0441.\u041c\u0430\u0440\u043a\u0435\u0442");
        list.add("Boxberry");
        list.add("SOKOLOV");
        list.add("Zippo");
        list.add("Gracy");
        list.add("Polaris");
        list.add("KFC");


        return list;
    }

    public List<Entry> plugDataSet(){

        List<Entry> list = new ArrayList<>();
        list.add(new Entry(0, formatter("Wed, 28 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(1, formatter("Wed, 28 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(2, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(3, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(4, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(5, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(6, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(7, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(8, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(9, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(10, formatter("Fri, 30 Apr 2021 00:00:00 GMT")));
        list.add(new Entry(11, formatter("Sun, 02 May 2021 00:00:00 GMT")));
        list.add(new Entry(12, formatter("Thu, 06 May 2021 00:00:00 GMT")));
        list.add(new Entry(13, formatter("Thu, 06 May 2021 00:00:00 GMT")));
        list.add(new Entry(14, formatter("Tue, 11 May 2021 00:00:00 GMT")));
        list.add(new Entry(15, formatter("Fri, 14 May 2021 00:00:00 GMT")));
        list.add(new Entry(16, formatter("Thu, 27 May 2021 00:00:00 GMT")));
        list.add(new Entry(17, formatter("Mon, 31 May 2021 00:00:00 GMT")));
        list.add(new Entry(18, formatter("Mon, 31 May 2021 00:00:00 GMT")));
        list.add(new Entry(19, formatter("Mon, 31 May 2021 00:00:00 GMT")));
        list.add(new Entry(20, formatter("Wed, 23 Jun 2021 00:00:00 GMT")));
        list.add(new Entry(21, formatter("Wed, 30 Jun 2021 00:00:00 GMT")));
        list.add(new Entry(22, formatter("Wed, 30 Jun 2021 00:00:00 GMT")));
        list.add(new Entry(23, formatter("Fri, 31 Dec 2021 00:00:00 GMT")));
        list.add(new Entry(24, formatter("Fri, 31 Dec 2021 00:00:00 GMT")));
        list.add(new Entry(25, formatter("Wed, 30 Nov 2022 00:00:00 GMT")));
        list.add(new Entry(26, formatter("Tue, 31 Dec 2030 00:00:00 GMT")));

        return list;
    }

}
