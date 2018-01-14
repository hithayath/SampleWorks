package com.example.user.bowlingapp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bowlingapp.R;
import com.example.user.bowlingapp.data.ScorerObject;

import java.util.ArrayList;

/**
 * Created by Lenovo on 14/01/2018.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ListHolder> {


    private final LayoutInflater mInflater;
    private final Context mContext;
    private ArrayList<ScorerObject> mData;
    private int mTotalCount;

    public RecycleAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = mInflater.inflate(R.layout.score_board, parent, false);
        ListHolder listHolder = new ListHolder(vi);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        ScorerObject viewData = mData.get(position);
        holder.bindData(viewData);
    }

    @Override
    public int getItemCount() {
        return mTotalCount;
    }

    public void updateData(ArrayList<ScorerObject> data) {
        mData = data;
        mTotalCount = mData != null ? mData.size() : 0;
        notifyDataSetChanged();
    }

    class ListHolder extends RecyclerView.ViewHolder {

        TextView mTextViewPlayer; // display player name.
        LinearLayout mLinearScroll; // Horizontal scroll view and add score rect in it

        public ListHolder(View itemView) {
            super(itemView);
            mTextViewPlayer = itemView.findViewById(R.id.tv_player_name);
            mLinearScroll = itemView.findViewById(R.id.layout_score_view);
        }

        public void bindData(ScorerObject viewData) {
            mTextViewPlayer.setText(viewData.getPlayerName());
            ArrayList<Integer> roll = viewData.roll();
            int size = roll.size();
            for (int i = 0; i < size; i++) {
                TextView roundRect = new TextView(mContext); // Add custom text view using xml and add it later.
                roundRect.setText(roll.get(i));
                mLinearScroll.addView(roundRect);
            }
        }
    }
}
