package com.shenzhoumeiwei.vcanmou.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.model.TestData;
import com.shenzhoumeiwei.vcanmou.model.TestData.TestGridData;


public class PosterGridAdapter extends BaseAdapter {

    private static final String TAG = "PosterGridAdapter";

    private List mList; // 桌台类型
    private LayoutInflater mInflater;
    private Resources mResources;

    public PosterGridAdapter(Context context, List list) {
        mInflater = LayoutInflater.from(context);
        mResources = context.getResources();
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
            convertView = mInflater.inflate(R.layout.fineposter_gridview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mName = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TestGridData td = (TestGridData) getItem(position);
        viewHolder.mName.setText(td.getName());
        
        return convertView;
    }

    private static class ViewHolder {
        public TextView mName;
    }

}
