package ru.sukhikh.couponcharts.ui.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.sukhikh.couponcharts.databinding.FragmentDateBinding;
import ru.sukhikh.couponcharts.models.DateModel;
import ru.sukhikh.couponcharts.plugs.LineChartPlug;
import ru.sukhikh.couponcharts.utils.ConnectHelper;
import ru.sukhikh.couponcharts.utils.LineChartBuilder;

import static ru.sukhikh.couponcharts.models.DateModel.formatter;

public class DateFragment extends Fragment {

    private final List<DateModel> dateModels = new ArrayList<>();
    private final LineChartPlug plugs = new LineChartPlug();
    private FragmentDateBinding binding;
    private final ConnectHelper connectHelper = new ConnectHelper();
    private final LineChartBuilder lineChartBuilder = new LineChartBuilder();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final LineChart lineChart = binding.lineChart;
        createChart(lineChart);

        return root;
    }

    private List<String> formLabels(){
        List<String> labels = new ArrayList<>();
        if(connectHelper.isOnline(getContext())) {
            for (int i = 0; i < dateModels.size(); i++) {
                labels.add(dateModels.get(i).getShopName());
            }
        }
        else labels.addAll(plugs.labelsPlug());
        return labels;
    }

    private void createChart(LineChart lineChart) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                getDates();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                List<Entry> dataSet = new ArrayList<>();
                if(connectHelper.isOnline(getContext())) {
                    for (int i = 0; i < dateModels.size(); i++) {
                        dataSet.add(new Entry(i, formatter(dateModels.get(i).getDate())));
                    }
                }
                else
                    dataSet.addAll(plugs.plugDataSet());
                lineChartBuilder.build(lineChart, dataSet, formLabels());
            });
        });
    }

    private void getDates() throws JSONException, IOException {
        String finalJson = connectHelper.connect("/hot-shops");
        JSONArray dateList = new JSONArray(finalJson);
        for(int i=0;i<dateList.length(); i++){
            dateModels.add(
                    new DateModel(
                            dateList.getJSONObject(i).getString("minExpirationTime"),
                            dateList.getJSONObject(i).getString("shop")
                    )
            );
        }
        Collections.shuffle(dateModels);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
