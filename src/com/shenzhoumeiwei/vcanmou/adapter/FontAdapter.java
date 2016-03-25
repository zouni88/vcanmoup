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


public class FontAdapter extends BaseAdapter {

    private static final String TAG = "FontAdapter";
    private Context context;
    private List mList;
    private LayoutInflater mInflater;
    private Resources mResources;
    private String[] font = new String[]{"微软雅黑","时尚中黑简","方正粗倩简体","方正大标宋简体","叶根友毛笔行书简","方正大黑简体","方正卡通简体","华文细黑","华文新魏","方正黑体简体"};
    public FontAdapter(Context context, List list) {
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
            convertView = mInflater.inflate(R.layout.font_grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.iv = (TextView) convertView.findViewById(R.id.font_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(TextUtils.equals(font[0],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_03);
        }else if(TextUtils.equals(font[1],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_04);
        }else if(TextUtils.equals(font[2],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_05);
        }else if(TextUtils.equals(font[3],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_06);
        }else if(TextUtils.equals(font[4],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_07);
        }else if(TextUtils.equals(font[5],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_08);
        }else if(TextUtils.equals(font[6],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_09);
        }else if(TextUtils.equals(font[7],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_10);
        }else if(TextUtils.equals(font[8],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_11);
        }else if(TextUtils.equals(font[9],getItem(position)+"")){
        	viewHolder.iv.setBackgroundResource(R.drawable.font_12);
        }
        
        return convertView;
    }

    private static class ViewHolder {
        public TextView iv;
    }

}
