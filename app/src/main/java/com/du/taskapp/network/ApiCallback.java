package com.du.taskapp.network;

import android.support.annotation.NonNull;

import com.du.taskapp.main.BusProvider;
import com.squareup.otto.Bus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hithayath.
 */

public class ApiCallback<T> implements Callback<T> {

    private Class<? extends T> responseClass;

    private final Bus bus = BusProvider.getInstance();

    public ApiCallback(Class<? extends T> responseClass) {
        this.responseClass = responseClass;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if(response.isSuccessful()) {
            bus.post(response.body());
        } else {
            final String message = String.format("Not successful",
                    response.code(),
                    response.message()
            );

            bus.post(new ApiError(message, new Exception(response.message())));
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        bus.post(new ApiError(t.getMessage(), t));
    }
}
