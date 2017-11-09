package com.du.taskapp.network;


/**
 * Created by Hithayath.
 */

public interface ApiService {

    String BASE_URL = "https://api.myjson.com/";

    void getHomeData();

    void getFindUsShops();

    void cancelHomeData();

    void cancelFindUsShops();
}
