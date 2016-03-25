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
import android.widget.ListAdapter;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.model.TestData;


public class MyPosterAdapter extends BaseAdapter {

    private static final String TAG = "FinePosterAdapter";
    private Context context;
    private List mList;
    private LayoutInflater mInflater;
    private Resources mResources;

    public MyPosterAdapter(Context context, List list) {
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
            convertView = mInflater.inflate(R.layout.my_poster_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mDate = (TextView) convertView.findViewById(R.id.date);
            viewHolder.mGridView = (GridView) convertView.findViewById(R.id.finePoster_grid);
            viewHolder.pga = new PosterGridAdapter(context, new ArrayList());
            viewHolder.mGridView.setAdapter(viewHolder.pga);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TestData td = (TestData) getItem(position);
        
        viewHolder.mDate.setText(td.getDate());
        viewHolder.pga.setData(td.getTestGridData());

        setGridViewHeightBasedOnChildren(viewHolder.mGridView);
        return convertView;
    }

    private static class ViewHolder {
        public TextView mDate;
        public GridView mGridView;
        PosterGridAdapter pga ;
    }

    /***
     * 设置gridview高度
     * @param gridView
     */
    public static void setGridViewHeightBasedOnChildren(GridView gridView) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        int count = listAdapter.getCount();
        int num = (int) Math.ceil(count / 2);
        View listItem = listAdapter.getView(0, null, null);
        listItem.measure(0, 0);
        int totalHeights = listItem.getMeasuredHeight();
        params.height = totalHeights * num;

        gridView.setLayoutParams(params);
    }
    
}
