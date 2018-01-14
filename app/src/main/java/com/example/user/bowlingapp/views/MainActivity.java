package com.example.user.bowlingapp.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.bowlingapp.R;
import com.example.user.bowlingapp.data.ScorerObject;
import com.example.user.bowlingapp.presenter.MainPresenter;
import com.example.user.bowlingapp.presenter.MainPresenterImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        createPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    private void createPresenter() {
       mPresenter = new MainPresenterImpl();
       mAdapter = new RecycleAdapter(this);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    @Override
    public void updateView(ArrayList<ScorerObject> data) {
        mAdapter.updateData(data);
        mRecyclerView.setAdapter(mAdapter);

    }
}
