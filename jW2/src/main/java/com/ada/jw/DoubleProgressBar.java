package com.ada.jw;

import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by lyj on 2015/7/22.
 */
public class DoubleProgressBar {
    private ImageView fillisterImageView;
    public ColumnProgressBar leftProgressBar;
    private ColumnProgressBar rightProgressBar;
    private int fillisterHeightMax = 244;
    public int id;

    public DoubleProgressBar(ImageView left, ImageView right, ImageView fillister) {
        leftProgressBar = new ColumnProgressBar(left);
        rightProgressBar = new ColumnProgressBar(right);
        fillisterImageView = fillister;

//        ViewTreeObserver vto2 = fillisterImageView.getViewTreeObserver();
//        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                fillisterImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                fillisterHeightMax = (int) ((float) fillisterImageView.getHeight() * 0.93f);
//                callBack(id);
//                Log.i("LYJ", "fillisterHeightMax:" + fillisterHeightMax);
//            }
//        });
    }

    public DoubleProgressBar(int position, ImageView left, ImageView right, ImageView fillister) {
        this.id = position;
        leftProgressBar = new ColumnProgressBar(left);
        rightProgressBar = new ColumnProgressBar(right);
        fillisterImageView = fillister;

//        ViewTreeObserver vto2 = fillisterImageView.getViewTreeObserver();
//        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                fillisterImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                fillisterHeightMax = (int) ((float) fillisterImageView.getHeight() * 0.93f);
//                callBack(id);
//            }
//        });
    }

    public void setProgress(int left, int right) {
        leftProgressBar.setProgress(left);
        rightProgressBar.setProgress(right);
    }

    public void setProgressMax(int left, int right) {
        leftProgressBar.setProgressMax(left);
        rightProgressBar.setProgressMax(right);
    }

    public void setLayoutMax(int max) {
        fillisterHeightMax = max;
    }

    public void callBack(final int id) {

    }

    private class ColumnProgressBar {
        private ImageView mImageView;
        private ViewGroup.LayoutParams params;
        private int max;

        public ColumnProgressBar(ImageView iv) {
            if(iv != null) {
                mImageView = iv;
                params = mImageView.getLayoutParams();
            }
        }

        public void setProgress(int progress) {
            if(mImageView != null) {
                float scale = fillisterHeightMax > 0 ?
                        (float) progress / (float) max : 0;
                params.height = (int) ((float) fillisterHeightMax * scale * 0.93f);
                mImageView.setLayoutParams(params);
            }
        }

        public void setProgressMax(int max) {
            this.max = max;
        }
    }
}
