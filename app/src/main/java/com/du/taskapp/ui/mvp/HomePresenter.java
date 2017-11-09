package com.du.taskapp.ui.mvp;

import com.du.taskapp.data.Home.HomeResult;
import com.du.taskapp.data.Home.PayLoad;

import java.util.List;

/**
 * Created by Hithayath.
 */

public interface HomePresenter {

    void onAttachView(HomeView view);

    void loadTiles();

    List<PayLoad> applyFilterAndSort(HomeResult data);

    void onTileClicked(PayLoad data);

    void onDetachView();
}
