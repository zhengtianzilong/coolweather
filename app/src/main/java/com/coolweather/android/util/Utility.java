package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/14.
 */

public class Utility {

    // 解析和处理服务器返回的省级数据
//
    public static boolean handleProvinceResponse(String response){

        if (!TextUtils.isEmpty(response)){

            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0;i < allProvince.length(); i++) {
                    JSONObject provinceObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }

                return true;

            }catch (JSONException e){
                e.printStackTrace();
            }


        }
        return false;
    }


    public static boolean handleCityResponse(String response, int provinceId){

        if (!TextUtils.isEmpty(response)){

            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i <allCities.length(); i++){

                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();

                }

                return true;

            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;

    }

    public static boolean handleCountyResponse(String response, int cityId){

        if (!TextUtils.isEmpty(response)){

            try {

                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.save();

                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }



        }
        return false;

    }


    public static Weather handleWeatherPesponse(String response){
//
//        {
//            "HeWeather": [
//            {
//                "status": "ok",
//                    "basic": {},
//                "aqi": {},
//                "now": {},
//                "suggestion": {},
//                "daily_forecast": []
//            }
//    ]
//        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();

            return new Gson().fromJson(weatherContent, Weather.class);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;


    }






}
