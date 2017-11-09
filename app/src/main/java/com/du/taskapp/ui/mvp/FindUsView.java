package com.du.taskapp.ui.mvp;

import com.du.taskapp.data.findus.PayLoad;

import java.util.List;

/**
 * Created by Hithayath.
 */

public interface FindUsView {

    void showLoading(boolean show);

    void setData(List<PayLoad> data);

    void showErrorMessageForFailure(int msgId);

    void showErrorMessageForFailure(String msg);

    boolean checkLocationEnabled(boolean showDialog);

    boolean needPermission();

    void requestPermission();

    void onShopListClicked(String marker, double[] latLong);
}
