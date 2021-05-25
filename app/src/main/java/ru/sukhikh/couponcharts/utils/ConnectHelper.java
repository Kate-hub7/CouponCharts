package ru.sukhikh.couponcharts.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectHelper {
    private static final String baseUrl = "http://5.63.154.154:8091/stat";

    public String connect(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(baseUrl+url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "GetCoupon Android 0.0" );
        connection.setRequestProperty("Authorization", "Basic YWxpdmUtZ2V0Y291cG9uLXVzZXI6OW1sNzBEbzdqWHRtMDVIS0s=");
        connection.connect();

        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder buffer = new StringBuilder();
        String line ="";
        while ((line = reader.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();
    }

    public boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
