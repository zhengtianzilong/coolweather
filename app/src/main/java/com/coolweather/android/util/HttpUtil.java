package com.coolweather.android.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/14.
 */

public class HttpUtil {

    public static void sendHttpRequest(String address, Callback callback){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
        .url(address)
        .build();

         client.newCall(request).enqueue(callback);




    }




}
