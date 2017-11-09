package com.du.taskapp.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.du.taskapp.R;
import com.du.taskapp.data.findus.PayLoad;
import com.du.taskapp.ui.mvp.FindUsPresenter;
import com.du.taskapp.util.DistanceCalculator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hithayath.
 */

public class FindUsShopsAdapter extends RecyclerView.Adapter<FindUsShopsAdapter.ShopsHolder> {

    private final Activity mActivity;
    private FindUsPresenter mListener;
    private LayoutInflater mInflater;
    private List<PayLoad> mDataArray;
    private int mTotalCount;

    public FindUsShopsAdapter(Activity context, FindUsPresenter listener) {
        mActivity = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListener = listener;
    }

    @Override
    public ShopsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = mInflater.inflate(R.layout.shop_list, parent, false);
        ShopsHolder holder = new ShopsHolder(vi);
        holder.setOnClickListener(mListener);
        vi.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopsHolder holder, int position) {
        try {

            PayLoad viewData = getViewData(position);
            holder.bindData(viewData);

        } catch (Exception e) {
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

    class ShopsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_title)
        TextView mTxtViewTitle;

        @BindView(R.id.tv_distance)
        TextView mTxtViewDistance;

        private FindUsPresenter mItemClickListener;

        public ShopsHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindData(PayLoad viewData) {

            if (viewData != null) {
                mTxtViewTitle.setText(viewData.getTitle());
                Double distance = viewData.getDistance();
                //Note: Address is shown if location is not available
                mTxtViewDistance.setText(distance != null ? mActivity.getString(R.string.distance, distance) : viewData.getAddress());
            }
        }

        public void setOnClickListener(FindUsPresenter listener) {
            mItemClickListener = listener;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            PayLoad payLoad = getItem(position);
            mItemClickListener.onShopListClicked(payLoad);
        }
    }
}
