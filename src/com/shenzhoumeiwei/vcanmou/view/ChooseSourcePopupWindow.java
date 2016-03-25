package com.shenzhoumeiwei.vcanmou.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.activity.BaseActivity;
import com.shenzhoumeiwei.vcanmou.activity.CameraActivity;

public class ChooseSourcePopupWindow extends PopupWindow implements OnClickListener {
	private Context context ;
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private View mRootView = null;
	private ImageView mQrcode;
	
	private RelativeLayout mRoot;
	private LinearLayout mPopupwindow;
	private Button mCameraGet,mMaterialLib,mAlbumImport;
	private TextView mCameraGetTv,mMaterialLibTv,mAlbumIportTv;
	private static final int SCALE = 5;//照片缩小比例
	
	public ChooseSourcePopupWindow(Context context, View popupView) {
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
		mPopupwindow = (LinearLayout) mRootView.findViewById(R.id.popupwindow);
		mCameraGet = (Button) mRootView.findViewById(R.id.camera_get);
		mMaterialLib = (Button) mRootView.findViewById(R.id.meterial_lib);
		mAlbumImport = (Button) mRootView.findViewById(R.id.album_import);
		mCameraGetTv = (TextView) mRootView.findViewById(R.id.camera_get_tv);
		mMaterialLibTv = (TextView) mRootView.findViewById(R.id.meterial_lib_tv);
		mAlbumIportTv = (TextView) mRootView.findViewById(R.id.album_import_tv);
	}
	
	private void initView(){
		
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.in_disappear);
    	mPopupwindow.startAnimation(animation);
    	mCameraGet.setOnClickListener(this);
    	mMaterialLib.setOnClickListener(this);
    	mAlbumImport.setOnClickListener(this);
    	mCameraGetTv.setOnClickListener(this);
    	mMaterialLibTv.setOnClickListener(this);
    	mAlbumIportTv.setOnClickListener(this);
    	
    	mRoot.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.camera_get://相机取材
		case R.id.camera_get_tv:
			Intent intent = new Intent(context,CameraActivity.class);
			((BaseActivity)context).startActivityForResult(intent, TAKE_PICTURE);
			dismiss();
			break;
		case R.id.album_import://相册导入 
		case R.id.album_import_tv:
			Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
			openAlbumIntent.setType("image/*");
			((BaseActivity)context).startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
			dismiss();
			break;
		case R.id.meterial_lib://素材库导入
		case R.id.meterial_lib_tv:
			
			break;
		case R.id.root:
			dismiss();
			break;
		}
	}
	
	
}
