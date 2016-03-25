package com.shenzhoumeiwei.vcanmou.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.model.TestData;


public class FinePosterAdapter extends BaseAdapter {

    private static final String TAG = "FinePosterAdapter";
    private Context context;
    private List mList;
    private LayoutInflater mInflater;
    private Resources mResources;

    public FinePosterAdapter(Context context, List list) {
        mInflater = LayoutInflater.from(context);
        mResources = context.getResources();
        this.context = context;
        setData(list);
    }

    public void setData(List list){
    	if(list != null){
    		if(mList == null){
    			mList = new ArrayList();
    		}
    		this.mList.clear();
    		mList.addAll(list);
    	}else{
    		if(mList == null){
    			mList = new ArrayList();
    		}
    	}
    	notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fine_poster_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TestData td = (TestData) getItem(position);
        
        return convertView;
    }

    private static class ViewHolder {
        public ImageView iv;
    }

}
