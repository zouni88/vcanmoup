package com.shenzhoumeiwei.vcanmou.fragment;

import java.io.IOException;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.activity.BaseActivity;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequestController;
import com.shenzhoumeiwei.vcanmou.net.HttpResponseListener;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg.ApiCreateTextImgResponse;
import com.shenzhoumeiwei.vcanmou.utils.Constant;
import com.shenzhoumeiwei.vcanmou.utils.Utils;
import com.shenzhoumeiwei.vcanmou.view.FontAnimatoinPopupWindow;
import com.shenzhoumeiwei.vcanmou.view.FontBgColorPopupWindow;
import com.shenzhoumeiwei.vcanmou.view.FontColorPopupWindow;
import com.shenzhoumeiwei.vcanmou.view.FontColorPopupWindow.FontColorCallBack;
import com.shenzhoumeiwei.vcanmou.view.FontPopupWindow;
import com.shenzhoumeiwei.vcanmou.view.FontPopupWindow.FontCallBack;

public class TextEditFragment extends Fragment implements OnClickListener {
	private final String TAG = "TextEditActivity";
	private Context context ;
	
	private View root;
	private View mNullView;
	private TextView mFinish;
    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions = null;

	
	//字体 颜色  动画  底色
	private TextView mFontTv,mColorTv,mAnimTv,mBgTv;
	
	//字体
	private FontPopupWindow mFontPopupWindow;
	//字体颜色
	private FontColorPopupWindow mFontColorPopupWindow;
	//字体动画
	private FontAnimatoinPopupWindow mFontAnimatoinPopupWindow;
	//字体背景
	private FontBgColorPopupWindow mFontBgColorPopupWindow;
	
	private String mFont;//字体
	private int mColor;//字体颜色
	private String mAnimatoin;//动画
	private String mBg;//背景色
	private String mFontSize;
	private String mText;
	private EditText mEdit;
	private ImageView mPreviewTv;
	//预览
	private TextView mPreviewBtn;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.popupwindow_text_edit, container, false);
		this.context = getActivity();
		
		mOptions = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.sophie)
        .showImageForEmptyUri(R.drawable.sophie).cacheOnDisc(true)
        .showImageOnFail(R.drawable.sophie).cacheInMemory(true).build();
		initView();
		initData();
		return root;
	}

	private void initView(){
		mNullView = root.findViewById(R.id.null_view);
		mFinish = (TextView) root.findViewById(R.id.finish);
		mFinish.setOnClickListener(this);
		mFontTv = (TextView) root.findViewById(R.id.font);
		mColorTv = (TextView) root.findViewById(R.id.color);
		mAnimTv = (TextView) root.findViewById(R.id.animation);
		mBgTv = (TextView) root.findViewById(R.id.bg_color);
		
		mFontTv.setOnClickListener(this);
		mColorTv.setOnClickListener(this);
		mAnimTv.setOnClickListener(this);
		mBgTv.setOnClickListener(this);

		mEdit = (EditText) root.findViewById(R.id.edit_text);
		mPreviewTv = (ImageView) root.findViewById(R.id.preview_tv);
		mPreviewBtn = (TextView) root.findViewById(R.id.preview);
		
		mPreviewBtn.setOnClickListener(this);
	}
	
	private void initData(){
		
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.finish:
			
			getActivity().onBackPressed();
			break;
		case R.id.font://字体
			View popupView2 = LayoutInflater.from(context).inflate(R.layout.popupwindow_font_layout, null);
			mFontPopupWindow = new FontPopupWindow(context, popupView2);
			popupView2.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			int popupWidth = popupView2.getMeasuredWidth();
			int popupHeight =  popupView2.getMeasuredHeight();
			int[] location = new int[2];
			v.getLocationOnScreen(location);
			mFontPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0]+v.getWidth()/2)-popupWidth/2,location[1]-popupHeight);
			mFontPopupWindow.setFontChangeListener(new FontCallBack() {
				@Override
				public void fontResult(String font) {
					mFont = font;
					createTextImg(mText, mFont, mFontSize);
				}
			});
			break; 
		case R.id.color://字体颜色
			View popupView3 = LayoutInflater.from(context).inflate(R.layout.popupwindow_font_color, null);
			mFontColorPopupWindow = new FontColorPopupWindow(context, popupView3);
			popupView3.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			int popupWidth1 = popupView3.getMeasuredWidth();
			int popupHeight1 =  popupView3.getMeasuredHeight();
			int[] location1 = new int[2];
			v.getLocationOnScreen(location1);
			mFontColorPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location1[0]+v.getWidth()/2)-popupWidth1/2,location1[1]-popupHeight1);
			mFontColorPopupWindow.setFontColorChangeListener(new FontColorCallBack() {
				@Override
				public void fontColorResult(int fontColor) {
					mColor = fontColor;
					createTextImg(mText, mFont, mFontSize);
				}
			});
			break;
		case R.id.animation://动画
			View popupView4 = LayoutInflater.from(context).inflate(R.layout.popupwindow_font_animation,null);
			mFontAnimatoinPopupWindow = new FontAnimatoinPopupWindow(context, popupView4);
			popupView4.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			int popupWidth2 = popupView4.getMeasuredWidth();
			int popupHeight2 =  popupView4.getMeasuredHeight();
			int[] location2 = new int[2];
			v.getLocationOnScreen(location2);
			mFontAnimatoinPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location2[0]+v.getWidth()/2)-popupWidth2/2,location2[1]-popupHeight2);
			break;
		case R.id.bg_color://背景颜色
			View popupView5 = LayoutInflater.from(context).inflate(R.layout.popupwindow_font_bg, null);
			mFontBgColorPopupWindow = new FontBgColorPopupWindow(context, popupView5);
			popupView5.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			int popupWidth3 = popupView5.getMeasuredWidth();
			int popupHeight3 =  popupView5.getMeasuredHeight();
			int[] location3 = new int[2];
			v.getLocationOnScreen(location3);
			mFontBgColorPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location3[0]+v.getWidth()/2)-popupWidth3/2,location3[1]-popupHeight3);
			break; 
		case R.id.preview:
			String content = mEdit.getText().toString();
			if(!TextUtils.isEmpty(content)){
//				mFont = content;
				mText = content;
				createTextImg(mText, mFont, mFontSize);
			}
			break;
			
		}
	}
	
	/***
	 * 上传文字生成图片
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public void createTextImg(String text,String font,String fontSize) {
		((BaseActivity)getActivity()).showCustomDialog(R.string.loading);
		HttpRequestController.createTextImg(context, "1", "zl", text,font,fontSize,
			new HttpResponseListener<ApiCreateTextImg.ApiCreateTextImgResponse>() {
				@Override
				public void onResult(ApiCreateTextImgResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.createTextImg = " + response.createTextImg);
						mImageLoader.displayImage(Constant.HTTP_BASE_URL+response.createTextImg.Data, mPreviewTv, mOptions);
					}
					Utils.toast(getActivity(), response.getRetInfo()+"");
					
					((BaseActivity)getActivity()).dismissCustomDialog();
				}
			});
	}
	
	public interface EditTextCallback {
		public void result();
	}
}
