package com.shenzhoumeiwei.vcanmou.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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
import com.shenzhoumeiwei.vcanmou.adapter.FontColorAdapter;
import com.shenzhoumeiwei.vcanmou.view.FontPopupWindow.FontCallBack;

public class FontColorPopupWindow extends PopupWindow implements OnClickListener,OnItemClickListener {
	private Context context;
	private View mRootView = null;
	
	private ImageView mQrcode;
	
	private GridView mGridView;
	private FontColorAdapter mFontColorAdapter;
	private List mList;
	private int[] font = new int[]{R.color.font_1,R.color.font_2,R.color.font_3,R.color.font_4,R.color.font_5,R.color.font_6,R.color.font_7,R.color.font_8,R.color.font_9,R.color.font_10,R.color.font_11,R.color.font_12,R.color.font_13};
	
	private FontColorCallBack mFcc;
	public FontColorPopupWindow(Context context, View popupView) {
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
		mGridView = (GridView) mRootView.findViewById(R.id.font_color_grid);
		mGridView.setOnItemClickListener(this);
		
	}
	
	private void initData(){
		mFontColorAdapter = new FontColorAdapter(context, new ArrayList());
		mGridView.setAdapter(mFontColorAdapter);
		mList = new ArrayList();
		for(int i = 0;i<font.length;i++){
			mList.add(font[i]);
		}
		mFontColorAdapter.setData(mList);	
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		int fonts = font[arg2];
		if(mFcc != null ){
			mFcc.fontColorResult(fonts);
		}
		dismiss();
	}
	
	public void setFontColorChangeListener(FontColorCallBack fcc){
		this.mFcc = fcc;
	}
	
	public interface FontColorCallBack{
		public void fontColorResult(int fontColor);
	}
}
