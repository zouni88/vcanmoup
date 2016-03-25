package com.shenzhoumeiwei.vcanmou.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.shenzhoumeiwei.vcanmou.R;

public class NewPosterPopupWindow extends PopupWindow implements OnClickListener {
	private Context context;
	private View mRootView = null;
	
	private ImageView mQrcode;
	
	private RelativeLayout mRoot;
	private RelativeLayout mPopupwindow;
	private Button mFastMakePoster;
	private Button mUseDefaultPoster;
	
	private Button mReturn;
	private ChooseSourcePopupWindow mChooseSourcePopupWindow;
	public NewPosterPopupWindow(Context context, View popupView) {
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
		mRoot = (RelativeLayout) mRootView.findViewById(R.id.root);
		mPopupwindow = (RelativeLayout) mRootView.findViewById(R.id.popupwindow);
		mFastMakePoster = (Button) mRootView.findViewById(R.id.falst_make_poster);
		mUseDefaultPoster = (Button) mRootView.findViewById(R.id.user_default_board);
		mReturn = (Button) mRootView.findViewById(R.id.return_dismiss);
	}
	
	private void initView(){
		
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.in_disappear);
    	mPopupwindow.startAnimation(animation);
    	mFastMakePoster.setOnClickListener(this);
    	mUseDefaultPoster.setOnClickListener(this);
    	mReturn.setOnClickListener(this);
    	mRoot.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.falst_make_poster:
			dismiss();
			//快速制作海报
			/*Intent intent = new Intent(context,ChooseSourcePopupWindow.class);
			context.startActivity(intent);*/
			View popupView2 = LayoutInflater.from(context).inflate(R.layout.popupwindow_choose_source, null);
			mChooseSourcePopupWindow = new ChooseSourcePopupWindow(context, popupView2);
			mChooseSourcePopupWindow.showAtLocation(mRoot, Gravity.BOTTOM, 0, 0);
			dismiss();
			break;
		case R.id.user_default_board:
			//使用预设模板
			
			break;
		case R.id.root:
		case R.id.return_dismiss:
			dismiss();
			break;
		}
	}
	
}
