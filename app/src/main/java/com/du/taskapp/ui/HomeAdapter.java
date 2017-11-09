package com.du.taskapp.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.du.taskapp.R;
import com.du.taskapp.data.Home.PayLoad;
import com.du.taskapp.ui.mvp.HomePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hithayath.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeDataHolder> {

    private final Activity mActivity;
    private HomePresenter mListener;
    private LayoutInflater mInflater;
    private List<PayLoad> mDataArray;
    private int mTotalCount;

    public HomeAdapter(Activity context, HomePresenter listener) {
        mActivity = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListener = listener;
    }

    @Override
    public HomeDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = mInflater.inflate(R.layout.home_grid_item, parent, false);
        HomeDataHolder holder = new HomeDataHolder(vi);
        holder.setOnClickListener(mListener);
        vi.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeDataHolder holder, int position) {
        try {

            PayLoad viewData = getViewData(position);
            holder.bindData(viewData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PayLoad getItem(int position) {
        return getViewData(position);
    }

    @Override
    public int getItemCount() {
        return mTotalCount;
    }

    public PayLoad getViewData(int pos) {
        PayLoad viewData = null;
        try {
            viewData = mDataArray.get(pos);
        } catch (Exception e) {
        }
        return viewData;
    }

    public void updateData(List<PayLoad> viewDataArray) {

        try {
            if(viewDataArray != null) {
                List<PayLoad> oldArray = mDataArray;
                mDataArray = viewDataArray;
                mTotalCount = mDataArray.size();

                notifyDataSetChanged();

                if(oldArray != mDataArray) {
                    oldArray.clear();
                }
            }
        } catch (Throwable t) {
        }
    }

    public void clearData() {
        if(mDataArray != null) {
            mDataArray.clear();
            mTotalCount = 0;
        }
    }

    class HomeDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final View mItemView;

        @BindView(R.id.tv_title)
        TextView mTxtViewName;

        @BindView(R.id.tv_short_desc)
        TextView mTxtViewDetails;

        private HomePresenter mItemClickListener;

        public HomeDataHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            mItemView = itemView;
            itemView.setOnClickListener(this);
        }

        public void bindData(PayLoad viewData) {

            if (viewData != null) {
                Glide.with(mActivity).load(viewData.getImagePath()).asBitmap().into(new SimpleTarget<Bitmap>(120, 200) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(mActivity.getResources(), resource);
                        mItemView.setBackground(drawable);
                    }
                });
                mTxtViewName.setTextColor(Color.parseColor(viewData.getTitleColor()));
                mTxtViewName.setText(viewData.getTitle());

                mTxtViewDetails.setTextColor(Color.parseColor(viewData.getShortDescColor()));
                mTxtViewDetails.setText(viewData.getShortDesc());
            }
        }

        public void setOnClickListener(HomePresenter listener) {
            mItemClickListener = listener;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            PayLoad tile = getItem(position);
            mItemClickListener.onTileClicked(tile);
        }
    }
}
