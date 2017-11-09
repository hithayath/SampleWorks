package com.du.taskapp.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.du.taskapp.R;
import com.du.taskapp.data.Home.PayLoad;
import com.du.taskapp.network.ApiServiceImpl;
import com.du.taskapp.ui.mvp.HomePresenter;
import com.du.taskapp.ui.mvp.HomePresenterImpl;
import com.du.taskapp.ui.mvp.HomeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.loadingView)
    ProgressBar mLoadingView;

    private HomePresenter presenter;
    private HomeAdapter mAdapter;
    private Animation mAnimUpSlide;

    public static final String TITLE = "title";
    public static final String LINK  = "link";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        createPresenter();
        initializeViews();
        presenter.onAttachView(this);
        presenter.loadTiles();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();

        mAdapter.clearData();
        mAdapter = null;
        mRecyclerView = null;

    }

    public void createPresenter() {
        presenter = new HomePresenterImpl(new ApiServiceImpl()); //Dagger can be used for DI.
    }

    private void initializeViews() {

        mAdapter = new HomeAdapter(this, presenter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        mRecyclerView.setAdapter(mAdapter);

        mAnimUpSlide = AnimationUtils.loadAnimation(this,
                R.anim.up_slide);
        mAnimUpSlide.setDuration(700);

    }

    @Override
    public void showLoading(boolean show) {
        mLoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setData(List<PayLoad> data) {
        if (data != null && data.size() > 0) {
            mAdapter.updateData(data);
            mRecyclerView.startAnimation(mAnimUpSlide);
        } else {
            showErrorMessageForFailure(R.string.error_no_items);
        }
    }

    @Override
    public void showErrorMessageForFailure(int msgId) {
        Snackbar.make(mRecyclerView, msgId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessageForFailure(String msg) {
        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showWebView(String title, String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(LINK, url);
        startActivity(intent);
    }

    @Override
    public void showFindUs(String title) {
        Intent intent = new Intent(this, FindUsActivity.class);
        intent.putExtra(TITLE, title);
        startActivity(intent);
    }
}
