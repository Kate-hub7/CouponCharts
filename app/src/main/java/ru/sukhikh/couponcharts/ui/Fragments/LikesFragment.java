package ru.sukhikh.couponcharts.ui.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.sukhikh.couponcharts.databinding.FragmentLikesBinding;
import ru.sukhikh.couponcharts.models.FavoriteShop;
import ru.sukhikh.couponcharts.plugs.HorizontalBarChartPlug;
import ru.sukhikh.couponcharts.utils.BarChartBuilder;
import ru.sukhikh.couponcharts.utils.ConnectHelper;

public class LikesFragment extends Fragment {

    private final List<FavoriteShop> favoriteShops = new ArrayList<>();
    private FragmentLikesBinding binding;
    private final BarChartBuilder barChartBuilder = new BarChartBuilder();
    private final ConnectHelper connectHelper = new ConnectHelper();
    private final HorizontalBarChartPlug plugs = new HorizontalBarChartPlug();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLikesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final HorizontalBarChart barChart = binding.textHome;
        createChart(barChart);
        return root;
    }

    private List<String> formLabels(){
        List<String> labels = new ArrayList<>();
        if(connectHelper.isOnline(getContext())) {
            for (int i = 0; i < favoriteShops.size(); i++) {
                labels.add(favoriteShops.get(i).getName());
            }
        }
        else labels = plugs.labelsPlug();
        return labels;
    }

    private void createChart(HorizontalBarChart barChart) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                favShop();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                List<BarEntry> dataSet = new ArrayList<>();
                if(connectHelper.isOnline(getContext())) {
                    for (int i = 0; i < favoriteShops.size(); i++) {
                        dataSet.add(new BarEntry(i, favoriteShops.get(favoriteShops.size() - i - 1).getLikes()));
                    }
                }
                else
                    dataSet.addAll(plugs.likesPlug());
                barChartBuilder.build(barChart, dataSet, formLabels());
            });
        });
    }

    private void favShop() throws JSONException, IOException {
        String finalJson = connectHelper.connect("/users-likes");
        JSONArray shopList = new JSONArray(finalJson);
        for(int i=0;i<shopList.length(); i++){
            favoriteShops.add(
                    new FavoriteShop(
                            shopList.getJSONObject(i).getString("shop"),
                            shopList.getJSONObject(i).getInt("likes")
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