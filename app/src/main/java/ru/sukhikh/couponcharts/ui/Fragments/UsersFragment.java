package ru.sukhikh.couponcharts.ui.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.sukhikh.couponcharts.databinding.FragmentUsersBinding;
import ru.sukhikh.couponcharts.models.Users;
import ru.sukhikh.couponcharts.plugs.PieChartPlug;
import ru.sukhikh.couponcharts.utils.ConnectHelper;
import ru.sukhikh.couponcharts.utils.PieChartBuilder;

public class UsersFragment extends Fragment {

    private FragmentUsersBinding binding;
    private final Users usersStatistic = new Users();
    private final ConnectHelper connectHelper = new ConnectHelper();
    private final PieChartBuilder pieChartBuilder = new PieChartBuilder();
    private final PieChartPlug plugs = new PieChartPlug();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final PieChart pieChart = binding.pieChart;
        if(connectHelper.isOnline(getContext()))
            createChart(pieChart);
        else
            NotConnectChart(pieChart);
        return root;
    }

    private void NotConnectChart(PieChart pieChart) {
        pieChartBuilder.build(pieChart, plugs.userPlug());
    }

    private void createChart(PieChart pieChart) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                countUsers();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                List<PieEntry> dataSet = new ArrayList<>();
                dataSet.add(new PieEntry(usersStatistic.getAndroidUsers(), "android"));
                dataSet.add(new PieEntry(usersStatistic.getIosUsers(), "ios"));
                pieChartBuilder.build(pieChart, dataSet);
            });
        });
    }

    private void countUsers() throws JSONException, IOException {
        String finalJson = connectHelper.connect("/users-os");
        JSONArray users = new JSONArray(finalJson);
        usersStatistic.setIosUsers(users
                .getJSONObject(0)
                .getInt("numberOfUsers"));
        usersStatistic.setAndroidUsers(users
                .getJSONObject(1)
                .getInt("numberOfUsers"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}