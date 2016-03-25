package com.shenzhoumeiwei.vcanmou.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.adapter.FontAdapter;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequestController;
import com.shenzhoumeiwei.vcanmou.net.HttpResponseListener;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg.ApiCreateTextImgResponse;
import com.shenzhoumeiwei.vcanmou.utils.Utils;

public class FontPopupWindow extends PopupWindow implements OnClickListener,OnItemClickListener {
	private final String TAG = "FontPopupWindow";
	private Context context;
	private View mRootView = null;
	
	private ImageView mQrcode;
	
	private GridView mGridView;
	private FontAdapter mFontAdapter;
	private List mList;
	private FontCallBack mFc;
	private String[] font = new String[]{"微软雅黑","时尚中黑简","方正粗倩简体","方正大标宋简体","叶根友毛笔行书简","方正大黑简体","方正卡通简体","华文细黑","华文新魏","方正黑体简体"};
	public FontPopupWindow(Context context, View popupView) {
		super(popupView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		this.context = context;
        mRootView = popupView;
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        
        findView();
        initData();
	}
	
	private void findView(){
		mGridView = (GridView) mRootView.findViewById(R.id.font_grid);
		mGridView.setOnItemClickListener(this);
		
		
	}
	
	private void initData(){
		mFontAdapter = new FontAdapter(context, new ArrayList());
		mGridView.setAdapter(mFontAdapter);
		mList = new ArrayList();
		for(int i = 0;i<font.length;i++){
			
			mList.add(font[i]);
		}
		mFontAdapter.setData(mList);	
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String fonts = font[arg2];
		if(mFc != null ){
			mFc.fontResult(fonts);
		}
		dismiss();
	}
	
	public void setFontChangeListener(FontCallBack fc){
		this.mFc = fc;
	}
	
	public interface FontCallBack{
		public void fontResult(String font);
	}
}
