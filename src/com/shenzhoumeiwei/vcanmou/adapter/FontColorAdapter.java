package com.shenzhoumeiwei.vcanmou.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;


public class FontColorAdapter extends BaseAdapter {

    private static final String TAG = "FontAdapter";
    private Context context;
    private List mList;
    private LayoutInflater mInflater;
    private Resources mResources;
    private int[] font = new int[]{R.color.font_1,R.color.font_2,R.color.font_3,R.color.font_4,R.color.font_5,R.color.font_6,R.color.font_7,R.color.font_8,R.color.font_9,R.color.font_10,R.color.font_11,R.color.font_12,R.color.font_13};
    public FontColorAdapter(Context context, List list) {
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
    public Integer getItem(int position) {
        return (Integer) mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.font_grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv = (TextView) convertView.findViewById(R.id.font_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(font[0] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_1));
        }else if(font[1] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_2));
        }else if(font[2] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_3));
        }else if(font[3] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_4));
        }else if(font[4] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_5));
        }else if(font[5] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_6));
        }else if(font[6] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_7));
        }else if(font[7] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_8));
        }else if(font[8] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_9));
        }else if(font[9] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_10));
        }else if(font[10] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_11));
        }else if(font[11] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_12));
        }else if(font[12] == getItem(position)){
        	viewHolder.iv.setBackgroundColor(context.getResources().getColor(R.color.font_13));
        }
        return convertView;
    }

    private static class ViewHolder {
        public TextView iv;
    }

}
