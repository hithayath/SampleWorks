package com.du.taskapp.ui.mvp;

import android.support.annotation.NonNull;

import com.du.taskapp.data.findus.PayLoad;
import com.du.taskapp.data.findus.ShopResult;
import com.du.taskapp.main.BusProvider;
import com.du.taskapp.network.ApiError;
import com.du.taskapp.network.ApiService;
import com.du.taskapp.util.DistanceCalculator;
import com.squareup.otto.Subscribe;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Hithayath.
 */

public class FindUsPresenterImpl implements FindUsPresenter {

    private ApiService mApiService;
    private FindUsView mView;
    private List<PayLoad> mShops;
    private double[] mLatitudeAndLongitude;

    public FindUsPresenterImpl(@NonNull ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void onAttachView(FindUsView view) {
        mView = view;
        BusProvider.getInstance().register(this);
    }

    @Override
    public void loadShops(double[] latLong) {
        if(mView.needPermission()) {
            mView.requestPermission();

        } else if(latLong != null || mView.checkLocationEnabled(true)) {
            mView.showLoading(true);
            mLatitudeAndLongitude = latLong;
            mApiService.getFindUsShops();
        }
    }

    @Override
    public void onShopListClicked(PayLoad payLoad) {
        double[] latLong = {payLoad.getLatitude(), payLoad.getLongitude()};
        mView.onShopListClicked(payLoad.getTitle(), latLong);
    }

    @Override
    public void onDetachView() {
        BusProvider.getInstance().unregister(this);
        mView = null;
        mApiService.cancelFindUsShops();
        mApiService = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }

    @Subscribe
    public void onShopsResponse(ShopResult data) {
        if (isViewAttached()) {
            mShops = data.getPayLoad();
            applyNearBySort(mLatitudeAndLongitude);
            mView.setData(mShops);
            mView.showLoading(false);
        }
    }

    @Subscribe
    public void onFailureResponse(ApiError error) {
        if (isViewAttached()) {
            mView.showLoading(false);
            mView.showErrorMessageForFailure(error.getMessage());
        }
    }

    private void applyNearBySort(double[] latLong) { // This is called from bus thread,
                                                    // for large data set we can move to async loaders.
        if (latLong != null) {  // Distance can be calculated with current location. Fallback Address is shown in list.
            final double lat = latLong[0];
            final double lon = latLong[1];
            Collections.sort(mShops, new Comparator<PayLoad>() {
                @Override
                public int compare(PayLoad p1, PayLoad p2) {
                    double distance1 = DistanceCalculator.distance(p1.getLatitude(), p1.getLongitude(), lat, lon, "K");
                    p1.setDistance(distance1);
                    double distance2 = DistanceCalculator.distance(p2.getLatitude(), p2.getLongitude(), lat, lon, "K");
                    p2.setDistance(distance2);
                    return p1.getDistanceInt() - p2.getDistanceInt();
                }
            });
        }
    }
}
