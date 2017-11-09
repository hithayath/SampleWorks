package com.du.taskapp.network;

import com.du.taskapp.data.Home.HomeResult;
import com.du.taskapp.data.findus.ShopResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hithayath.
 */

public interface ApiInterface {


    @GET("bins/19kxcv")
    Call<HomeResult> getHomeData();

    @GET("bins/114kkf")
    Call<ShopResult> getFindUsShops();
}
