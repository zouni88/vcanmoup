package com.shenzhoumeiwei.vcanmou.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.fancycoverflow.FancyCoverFlow;
import com.shenzhoumeiwei.vcanmou.fancycoverflow.FancyCoverFlowAdapter;
import com.shenzhoumeiwei.vcanmou.view.CustomViewGroup;

public class ViewGroupExampleAdapter  extends FancyCoverFlowAdapter {
	private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6,};

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Integer getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        CustomViewGroup customViewGroup = null;
        if (reuseableView != null) {
            customViewGroup = (CustomViewGroup) reuseableView;
        } else {
            customViewGroup = new CustomViewGroup(viewGroup.getContext());
            customViewGroup.setLayoutParams(new FancyCoverFlow.LayoutParams(400, 600));
        }
        customViewGroup.getImageView().setImageResource(this.getItem(i));
        return customViewGroup;
    }
    
}
