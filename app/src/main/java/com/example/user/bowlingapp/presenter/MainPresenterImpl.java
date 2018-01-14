package com.example.user.bowlingapp.presenter;

import com.example.user.bowlingapp.data.ScorerObject;
import com.example.user.bowlingapp.views.MainView;

import java.util.ArrayList;

/**
 * Created by User on 1/14/2018.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mView;

    public MainPresenterImpl() {

    }

    @Override
    public void attachView(MainView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    private boolean isViewActive() {
        return mView != null;
    }

    @Override
    public void loadScoreBoard() {
        ArrayList<ScorerObject> list = new ArrayList();
        for (int i = 0; i < 4; i++) {
            ScorerObject scorerObject = new ScorerObject();
            scorerObject.setPlayerName("Player"+i);
            scorerObject.setFrameNumber(new ArrayList<Integer>());
            scorerObject.setScoreSoFar(i + 30);
            list.add(scorerObject);
        }

        if (isViewActive()) {
            mView.updateView(list);
        }
    }
}
