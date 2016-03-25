package com.shenzhoumeiwei.vcanmou.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.google.zxing.WriterException;
import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.utils.Constant;
import com.shenzhoumeiwei.vcanmou.utils.CreateQR;

public class QrcodePopupWindow extends PopupWindow implements OnClickListener {
	private Context context;
	private View mRootView = null;
	
	private ImageView mQrcode;
	
	public QrcodePopupWindow(Context context, View popupView) {
		super(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		this.context = context;
        mRootView = popupView;
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        
        findView();
        initView();
	}
	
	private void findView(){
		mQrcode = (ImageView) mRootView.findViewById(R.id.qrcode);
		
	}
	
	private void initView(){
		try {
			mQrcode.setImageBitmap(CreateQR.Create2DCode("1"));
		} catch (WriterException e) {
		}
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			
		}
	}
	
}
