package com.du.taskapp.network;

import com.du.taskapp.data.Home.HomeResult;
import com.du.taskapp.data.findus.ShopResult;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hithayath.
 */

public class ApiServiceImpl implements ApiService {

    private Retrofit mRetrofit = null;
    private Call<HomeResult> mHomeCall;
    private Call<ShopResult> mFindUsCall;

    private Retrofit getClient() {
        if (mRetrofit ==null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return mRetrofit;
    }

    public void cancelHomeData() {
        if (mHomeCall != null) {
            mHomeCall.cancel();
        }
    }

    public void cancelFindUsShops() {
        if(mFindUsCall != null) {
            mFindUsCall.cancel();
        }
    }

    @Override
    public void getHomeData() {
        ApiInterface apiService =
                getClient().create(ApiInterface.class);

        mHomeCall = apiService.getHomeData();
        mHomeCall.enqueue(new ApiCallback<>(HomeResult.class));
    }

    @Override
    public void getFindUsShops() {
        ApiInterface apiService =
                getClient().create(ApiInterface.class);

        mFindUsCall = apiService.getFindUsShops();
        mFindUsCall.enqueue(new ApiCallback<>(ShopResult.class));
    }
}
