package com.ada.jw;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


/**
 * Created by ada on 2015/7/23.
 */
public class HistogramAdapter extends BaseAdapter {
    private Context mContext;
    private int itemLayoutId;
    private ArrayList<HashMap<String, Integer>> datas;
    private ArrayList<DoubleProgressBar> mDoubleProgressBarList;
    private HorizontalListView mHorizontalListView;
    private static final int mMax = 20;

    public HistogramAdapter(Context mContext, int itemLayoutId,
                            ArrayList<HashMap<String, Integer>> datas) {
        this.mContext = mContext;
        this.itemLayoutId = itemLayoutId;
        this.datas = datas;
        mDoubleProgressBarList = new ArrayList<DoubleProgressBar>();
    }

    public void setListView(HorizontalListView listView) {
        this.mHorizontalListView = listView;
    }

    public void refressDataAll() {
        for(int i = 0; i < getCount(); i++) {
            mDoubleProgressBarList.get(i).setProgress(
                    datas.get(i).get("speed"), datas.get(i).get("incline"));
        }
    }

    public void refressDataAll(ArrayList<HashMap<String, Integer>> newDatas) {
        datas = newDatas;
        mDoubleProgressBarList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMax;
    }

    @Override
    public Object getItem(int position) {
        return mDoubleProgressBarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        DoubleProgressBar bar = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(itemLayoutId, null);
            holder.fillisterImageView = (ImageView) convertView.findViewById(R.id.fillister);
            holder.leftImageView = (ImageView) convertView.findViewById(R.id.left);
            holder.rightImageView = (ImageView) convertView.findViewById(R.id.right);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        bar = new DoubleProgressBar(
                position, holder.leftImageView, holder.rightImageView, holder.fillisterImageView);
        bar.setProgressMax(250, 30);
        bar.setProgress(datas.get(position).get("speed"), datas.get(position).get("incline"));
        mDoubleProgressBarList.add(bar);

        return convertView;
    }

    static class ViewHolder {
        ImageView fillisterImageView;
        ImageView leftImageView;
        ImageView rightImageView;
    }
}
