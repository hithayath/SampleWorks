package com.du.taskapp.ui.mvp;

import com.du.taskapp.data.Home.PayLoad;

import java.util.List;

/**
 * Created by Hithayath.
 */

public interface HomeView {

    void showLoading(boolean show);

    void setData(List<PayLoad> data);

    void showErrorMessageForFailure(int msgId);

    void showErrorMessageForFailure(String msg);

    void showWebView(String title, String url);

    void showFindUs(String title);

}
