package com.shenzhoumeiwei.vcanmou.activity;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.fragment.SavePreviewFragment;
import com.shenzhoumeiwei.vcanmou.fragment.TextEditFragment;
import com.shenzhoumeiwei.vcanmou.utils.ImageTools;
import com.shenzhoumeiwei.vcanmou.view.ChooseBoardPopupWindow;
import com.shenzhoumeiwei.vcanmou.view.ChooseBoardPopupWindow.EditTextCallback;
import com.shenzhoumeiwei.vcanmou.view.ChooseSourcePopupWindow;
import com.shenzhoumeiwei.vcanmou.view.CustomRotateTextView;

public class EditPosterActivity extends BaseActivity implements OnClickListener {
	private final String TAG = "EditPosterActivity";
	private EditPosterActivity context = EditPosterActivity.this;
	
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int SCALE = 2;//照片缩小比例
	
	private RelativeLayout mRoot;
	private FragmentManager mFragmentManager;
	private FragmentTransaction ft;
	
	private RelativeLayout mLL;
	//返回 ，保存并预览效果，页面设置，再添加一页
	private TextView mReturn,mSavePreview,mSet,mAddPage;
	private ChooseSourcePopupWindow mChooseSourcePopupWindow;//选择资源
	private ChooseBoardPopupWindow mChooseBoardPopupWindow;//选择模板
	
	private TextEditFragment mTextEditPopupWindow;
	
	private Button mHelpBtn,mAddBtn,mArrowBtn;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		super.setContentView(R.layout.activity_editposter);
		mFragmentManager = getSupportFragmentManager();
		initView();
		initData();
	}
	
	private void initView(){
		mLL = (RelativeLayout) super.findViewById(R.id.root_view);
		mRoot = (RelativeLayout) super.findViewById(R.id.poster_root);
		
		mReturn = (TextView) super.findViewById(R.id.edit_return);
		mSavePreview = (TextView) super.findViewById(R.id.edit_save_preview);
		mSet = (TextView) super.findViewById(R.id.edit_set);
		mAddPage = (TextView) super.findViewById(R.id.edit_add_page);
		mReturn.setOnClickListener(this);
		mSavePreview.setOnClickListener(this);
		mSet.setOnClickListener(this);
		mAddPage.setOnClickListener(this);
		
		mHelpBtn = (Button) super.findViewById(R.id.edit_help_btn);
		mAddBtn =(Button) super.findViewById(R.id.edit_add_btn);
		mArrowBtn = (Button) super.findViewById(R.id.edit_arrow_btn);
		mHelpBtn.setOnClickListener(this);
		mAddBtn.setOnClickListener(this);
		mArrowBtn.setOnClickListener(this);
		
	}
	
	private void initData(){
		String source = getIntent().getStringExtra("source");
		if(!TextUtils.isEmpty(source) && TextUtils.equals(source, "camera")){
			String url = getIntent().getStringExtra("url");
			Bitmap bitmap = BitmapFactory.decodeFile(url);
			Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
			//由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
			bitmap.recycle();
			
			//将处理过的图片显示在界面上，并保存到本地
			Drawable drawables = new BitmapDrawable(newBitmap);
			mRoot.setBackgroundDrawable(drawables);
			ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
		} else if(!TextUtils.isEmpty(source) && TextUtils.equals(source, "choose_picture")) {
			Uri originalUri = getIntent().getData();
			ContentResolver resolver = getContentResolver();
			try {
            	//使用ContentProvider通过URI获取原始图片
				Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
				if (photo != null) {
					//为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
					Bitmap smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
					//释放原始图片占用的内存，防止out of memory异常发生
					photo.recycle();
					Drawable drawable = new BitmapDrawable(smallBitmap);
					mRoot.setBackgroundDrawable(drawable);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		View v1 = addTextView("请输入标题",30,getWindowManager().getDefaultDisplay().getHeight()*0.6f);
		View v2 = addTextView("请在此输入与图片内容相匹配的详细信息......",30,getWindowManager().getDefaultDisplay().getHeight()*0.7f);
		
	}
	
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()) {
		case R.id.edit_return://返回
			finish();
			break;
		case R.id.edit_save_preview://保存并预览效果
			navStyle(0);
//			savePreview();
			Intent intent = new Intent(context,SaveAndPreviewActivity.class);
			startActivity(intent);
			break;
		case R.id.edit_set://页面设置
			navStyle(1);
			Intent intents = new Intent(context,PageSetActivity.class);
			startActivity(intents);
			break;
		case R.id.edit_add_page://再添加一页
			navStyle(2);
			View popupView2 = LayoutInflater.from(context).inflate(R.layout.popupwindow_choose_source, null);
			mChooseSourcePopupWindow = new ChooseSourcePopupWindow(context, popupView2);
			mChooseSourcePopupWindow.showAtLocation(mLL, Gravity.BOTTOM, 0, 0);
			/*Intent intent = new Intent(context,ChooseSourcePopupWindow.class);
			startActivity(intent);*/
			break;
		case R.id.edit_help_btn://帮助提示
			
			break;
		case R.id.edit_add_btn://添加图片或者文字模块
			View popupView3 = LayoutInflater.from(context).inflate(R.layout.popupwindow_choose_add_board, null);
			mChooseBoardPopupWindow = new ChooseBoardPopupWindow(context, popupView3);
			mChooseBoardPopupWindow.showAtLocation(mLL, Gravity.BOTTOM, 0, 0);
			mChooseBoardPopupWindow.setCallBack(new EditTextCallback() {
				@Override
				public void result(int board) {
					if(board == 1){
						addTextView("请在此输入与图片内容相匹配的详细信息......",30,getWindowManager().getDefaultDisplay().getHeight()*0.7f);
					}else if(board == 2){
						addImageView(30,getWindowManager().getDefaultDisplay().getHeight()*0.7f);
					}
				}
			});
			
			break;
		case R.id.edit_arrow_btn://调整位置
			
			break;
		}
	}
	
	/**
	 * 保存并预览效果
	 */
	private void savePreview() {
		ft = mFragmentManager.beginTransaction();
		removeFragment();
		SavePreviewFragment plf = new SavePreviewFragment();
		ft.add(R.id.poster_root, plf, "save_preview");
		ft.commit();
	}
	
	private View addTextView(String content,float left,float top){
		LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.activity_text_customview, null);
		final CustomRotateTextView title = (CustomRotateTextView) layout.findViewById(R.id.title);
		title.setText(content);
		int width = (int) (title.getText().length()*title.getTextSize())*2;
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.height = 80;
		lp.width = width+10;
		lp.leftMargin = (int) left;
		lp.topMargin = (int) top;
		lp.bottomMargin = 30;
		layout.setLayoutParams(lp);
		//CustomRotateTextView crtv = (CustomRotateTextView) layout.findViewById(R.id.title);
		title.setOnClickListener(new com.shenzhoumeiwei.vcanmou.view.CustomRotateTextView.OnClickListener() {
			@Override
			public void onClick() {
				//文字编辑页面
				/*View popupView2 = LayoutInflater.from(context).inflate(R.layout.popupwindow_text_edit, null);
				mTextEditPopupWindow = new TextEditPopupWindow(context, popupView2);
				mTextEditPopupWindow.showAtLocation(mRoot, Gravity.CENTER, 0, 0);
				*/
				FragmentTransaction ft = mFragmentManager.beginTransaction();
	            ft.add(R.id.root_view, new TextEditFragment(), "text_fragment");
	            ft.addToBackStack(null);
	            ft.commitAllowingStateLoss();
	            
//	            title.setTextColor(R.color.)
			}
		});
		mRoot.addView(layout);
		//保存当前layout
		return layout;
	}
	
	private View addImageView(float left,float top){
		LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.add_img_layout, null);
		mRoot.addView(layout);
		return layout;
	}
	
	private void removeFragment(){
		SavePreviewFragment spf = (SavePreviewFragment) mFragmentManager.findFragmentByTag("save_preview");
        if (spf != null) {
            ft.remove(spf);
        }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case TAKE_PICTURE://拍照回调
				String url = data.getStringExtra("url");
				System.out.println("我的拍照图片url="+url);
				Bitmap bitmap = BitmapFactory.decodeFile(url);
				Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
				//由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
				bitmap.recycle();
				
				//将处理过的图片显示在界面上，并保存到本地
				Drawable drawables = new BitmapDrawable(newBitmap);
				mRoot.setBackgroundDrawable(drawables);
				ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
				
				/*if (photos != null) {
					//为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
					Bitmap smallBitmaps = ImageTools.zoomBitmap(photos, photos.getWidth() / SCALE, photos.getHeight() / SCALE);
					//释放原始图片占用的内存，防止out of memory异常发生
					photos.recycle();
					Drawable drawable = new BitmapDrawable(smallBitmaps);
					mRoot.setBackground(drawable);
				}*/
				
				break;
			case CHOOSE_PICTURE:
				ContentResolver resolver = getContentResolver();
				//照片的原始资源地址
				Uri originalUri = data.getData(); 
	            try {
	            	//使用ContentProvider通过URI获取原始图片
					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
					if (photo != null) {
						//为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
						Bitmap smallBitmap = ImageTools.zoomBitmap(photo, photo.getWidth() / SCALE, photo.getHeight() / SCALE);
						//释放原始图片占用的内存，防止out of memory异常发生
						photo.recycle();
						Drawable drawable = new BitmapDrawable(smallBitmap);
						mRoot.setBackgroundDrawable(drawable);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			
			default:
				break;
			}
		}
	}
	
	private void navStyle(int arg0){
		if(arg0 == 0){
			Drawable newMake = getResources().getDrawable(R.drawable.edit_save_preview_press);
			newMake.setBounds(0, 0, newMake.getMinimumWidth(), newMake.getMinimumHeight());
			mSavePreview.setCompoundDrawables(null, newMake, null, null);
			Drawable myPoster = getResources().getDrawable(R.drawable.edit_set_normal);
			myPoster.setBounds(0, 0, myPoster.getMinimumWidth(), myPoster.getMinimumHeight());
			mSet.setCompoundDrawables(null, myPoster, null, null);
			Drawable share = getResources().getDrawable(R.drawable.edit_add_page_normal);
			share.setBounds(0, 0, share.getMinimumWidth(), share.getMinimumHeight());
			mAddPage.setCompoundDrawables(null, share, null, null);
			
			mSavePreview.setTextColor(getResources().getColor(R.color.bg_red));
			mSet.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
			mAddPage.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
		} else if (arg0 == 1){
			Drawable myPoster = getResources().getDrawable(R.drawable.edit_set_press);
			myPoster.setBounds(0, 0, myPoster.getMinimumWidth(), myPoster.getMinimumHeight());
			mSet.setCompoundDrawables(null, myPoster, null, null);
			Drawable newMake = getResources().getDrawable(R.drawable.edit_save_preview_normal);
			newMake.setBounds(0, 0, newMake.getMinimumWidth(), newMake.getMinimumHeight());
			mSavePreview.setCompoundDrawables(null, newMake, null, null);
			Drawable share = getResources().getDrawable(R.drawable.edit_add_page_normal);
			share.setBounds(0, 0, share.getMinimumWidth(), share.getMinimumHeight());
			mAddPage.setCompoundDrawables(null, share, null, null);
			
			mSet.setTextColor(getResources().getColor(R.color.bg_red));
			mSavePreview.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
			mAddPage.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
		} else if (arg0 == 2){
			Drawable share = getResources().getDrawable(R.drawable.edit_add_page_press);
			share.setBounds(0, 0, share.getMinimumWidth(), share.getMinimumHeight());
			mAddPage.setCompoundDrawables(null, share, null, null);
			Drawable myPoster = getResources().getDrawable(R.drawable.edit_save_preview_normal);
			myPoster.setBounds(0, 0, myPoster.getMinimumWidth(), myPoster.getMinimumHeight());
			mSavePreview.setCompoundDrawables(null, myPoster, null, null);
			Drawable newMake = getResources().getDrawable(R.drawable.edit_set_normal);
			newMake.setBounds(0, 0, newMake.getMinimumWidth(), newMake.getMinimumHeight());
			mSet.setCompoundDrawables(null, newMake, null, null);
			
			mAddPage.setTextColor(getResources().getColor(R.color.bg_red));
			mSet.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
			mSavePreview.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
		}
	}
	
}
