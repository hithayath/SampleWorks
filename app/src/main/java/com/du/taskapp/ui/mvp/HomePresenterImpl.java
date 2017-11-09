package com.du.taskapp.ui.mvp;

import android.support.annotation.NonNull;

import com.du.taskapp.R;
import com.du.taskapp.data.Home.HomeResult;
import com.du.taskapp.data.Home.PayLoad;
import com.du.taskapp.main.BusProvider;
import com.du.taskapp.network.ApiError;
import com.du.taskapp.network.ApiService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Hithayath.
 */

public class HomePresenterImpl implements HomePresenter {

    private ApiService mApiService;
    private HomeView mView;

    public HomePresenterImpl(@NonNull ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void onAttachView(HomeView view) {
        mView = view;
        BusProvider.getInstance().register(this);
    }

    @Override
    public void loadTiles() {
        mView.showLoading(true);
        mApiService.getHomeData();
    }

    public List<PayLoad> applyFilterAndSort(HomeResult data) { // This is called from bus thread, for large data set we can move to async loaders.
        List<PayLoad> filtered = new ArrayList<>();
        for( PayLoad payLoad : data.getPayLoad()) {
            if (payLoad.getIsActive() > 0 && payLoad.getIsVisible() > 0) {
                filtered.add(payLoad);
            }
        }
        Collections.sort(filtered, new Comparator<PayLoad>() {
            @Override
            public int compare(PayLoad p1, PayLoad p2) {
                return p1.getPosition() - p2.getPosition();
            }
        });
        return filtered;
    }

    @Override
    public void onTileClicked(PayLoad data) {
        if (data != null) {
            Integer sectionId = data.getSectionId();
            if (sectionId != null && sectionId == 10) {
                mView.showFindUs(data.getTitle());

            } else if(data.getExternalLink() != null) {
                mView.showWebView(data.getTitle(), data.getExternalLink());
            } else {
                mView.showErrorMessageForFailure(R.string.error_no_items);
            }
        }
    }

    @Override
    public void onDetachView() {
        BusProvider.getInstance().unregister(this);
        mView = null;
        mApiService.cancelHomeData();
        mApiService = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }

    @Subscribe
    public void onSuccessResponse(HomeResult data) {
        if (isViewAttached()) {
            mView.setData(applyFilterAndSort(data));
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
}
