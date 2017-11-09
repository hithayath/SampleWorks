package com.du.taskapp.ui.mvp;

import com.du.taskapp.data.findus.PayLoad;

/**
 * Created by Hithayath.
 */

public interface FindUsPresenter {

    void onAttachView(FindUsView view);

    void loadShops(double[] latLong);

    void onShopListClicked(PayLoad payLoad);

    void onDetachView();
}
