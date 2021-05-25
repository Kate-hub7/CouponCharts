package ru.sukhikh.couponcharts.ui.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.sukhikh.couponcharts.databinding.FragmentCouponNumbersBinding;
import ru.sukhikh.couponcharts.models.NumbersCoupon;
import ru.sukhikh.couponcharts.plugs.BarChartPlug;
import ru.sukhikh.couponcharts.utils.ConnectHelper;
import ru.sukhikh.couponcharts.utils.NumbersBarChartBuilder;

public class NumbersCouponFragment extends Fragment {

    private FragmentCouponNumbersBinding binding;
    private final List<NumbersCoupon> numbersCoupons = new ArrayList<>();
    private final ConnectHelper connectHelper = new ConnectHelper();
    private final NumbersBarChartBuilder barChartBuilder = new NumbersBarChartBuilder();
    private final BarChartPlug plugs = new BarChartPlug();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCouponNumbersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        BarChart barChart = binding.numberCoupon;
        createChart(barChart);

        return root;
    }

    private List<String> formLabels(){
        List<String> labels = new ArrayList<>();
        if(connectHelper.isOnline(getContext())) {
            for (int i = 0; i < numbersCoupons.size(); i++) {
                labels.add(numbersCoupons.get(i).getShopName());
            }
        }
        else
            labels.addAll(plugs.labelsPlug());
        return labels;
    }

    private void createChart(BarChart barChart) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                NumbersCoupon();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                List<BarEntry> dataSet = new ArrayList<>();
                if(connectHelper.isOnline(getContext())) {
                    for (int i = 0; i < numbersCoupons.size(); i++) {
                        dataSet.add(new BarEntry(i, numbersCoupons.get(i).getCouponNumbers()));
                    }
                }
                else
                    dataSet.addAll(plugs.numbersPlug());
                barChartBuilder.build(barChart, dataSet, formLabels());
            });
        });
    }

    private void NumbersCoupon() throws JSONException, IOException {
        String finalJson = connectHelper.connect("/coupons-number-in-shop");
        JSONArray shopList = new JSONArray(finalJson);
        for(int i=0;i<shopList.length(); i++){
            numbersCoupons.add(
                    new NumbersCoupon(
                            shopList.getJSONObject(i).getInt("couponsNumber"),
                            shopList.getJSONObject(i).getString("shop")
                    )
            );
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}