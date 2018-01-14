package com.example.user.bowlingapp.presenter;

import com.example.user.bowlingapp.views.MainView;

/**
 * Created by User on 1/14/2018.
 */

public interface MainPresenter {

    void attachView(MainView view);

    void detachView();

    void loadScoreBoard();
}
