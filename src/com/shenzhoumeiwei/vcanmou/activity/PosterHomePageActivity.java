package com.shenzhoumeiwei.vcanmou.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.adapter.MyPagerAdapter;
import com.shenzhoumeiwei.vcanmou.fragment.FinePosterFragment;
import com.shenzhoumeiwei.vcanmou.fragment.MyCollectionFragment;
import com.shenzhoumeiwei.vcanmou.fragment.MyPosterFragment;
import com.shenzhoumeiwei.vcanmou.fragment.PosterLibFragment;
import com.shenzhoumeiwei.vcanmou.fragment.UnFinishFragment;
import com.shenzhoumeiwei.vcanmou.view.NewPosterPopupWindow;

public class PosterHomePageActivity extends BaseActivity implements OnClickListener {
	private final String TAG = "PosterHomePageActivity";

	private PosterHomePageActivity context = PosterHomePageActivity.this;
	private FragmentManager mFragmentManager;
	private FragmentTransaction ft;
	//海报制作首页，制作新海报，我的海报，分享传播
	private TextView mNavHomePage,mNavNewMake,mNavMyPoster,mNavShare;
	//我的收藏，未做完的，海报模板库，优秀海报
	private TextView mPoster,mCollection,mUnfinish,mLib,mFinePoster;
	private ViewPager mPager;// 页卡内容
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度

	private LinearLayout mRoot;
	
	//制作新海报
	private NewPosterPopupWindow mNewPosterPopupWindow;
	//选取素材
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int SCALE = 5;//照片缩小比例
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		super.setContentView(R.layout.activity_posterpage);
		mFragmentManager = getSupportFragmentManager();
		initView();
		InitImageView();
		initData();
		
	}

	private void initView() {
		mRoot = (LinearLayout) super.findViewById(R.id.ll_roots);
		mNavHomePage = (TextView) super.findViewById(R.id.nav_poster_page);
		mNavNewMake = (TextView) super.findViewById(R.id.nav_new_make);
		mNavMyPoster = (TextView) super.findViewById(R.id.nav_my_poster);
		mNavShare = (TextView) super.findViewById(R.id.nav_share);
		
		mPoster = (TextView) super.findViewById(R.id.my_poster);
		mCollection = (TextView) super.findViewById(R.id.collection);
		mUnfinish = (TextView) super.findViewById(R.id.unfinish);
		mLib = (TextView) super.findViewById(R.id.poster_lib);
		mFinePoster = (TextView) super.findViewById(R.id.fine_poster);
		
	}

	/**
	 * 初始化动画
	 */
	private void InitImageView() {
		cursor = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();// 获取图片宽度		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		
		offset = ((screenW-80) / 5 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
		
		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.lay1, null));
		listViews.add(mInflater.inflate(R.layout.lay2, null));
		listViews.add(mInflater.inflate(R.layout.lay3, null));
		listViews.add(mInflater.inflate(R.layout.lay4, null));
		listViews.add(mInflater.inflate(R.layout.lay5, null));
		
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	private void initData() {
		mNavHomePage.setOnClickListener(this);
		mNavNewMake.setOnClickListener(this);
		mNavMyPoster.setOnClickListener(this);
		mNavShare.setOnClickListener(this);
		
		mPoster.setOnClickListener(this);
		mPoster.setOnClickListener(new MyOnClickListener(0));
		mCollection.setOnClickListener(new MyOnClickListener(1));
		mUnfinish.setOnClickListener(new MyOnClickListener(2));
		mLib.setOnClickListener(new MyOnClickListener(3));
		mFinePoster.setOnClickListener(new MyOnClickListener(4));
		handler.sendEmptyMessage(1);
	}
	
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			myPoster();
			textColor();
			super.handleMessage(msg);
		}
	};
	/**
	 * 我的海报
	 */
	private void myPoster() {
		ft = mFragmentManager.beginTransaction();
		removeFragment();
		MyPosterFragment tmf = new MyPosterFragment();
		ft.add(listViews.get(currIndex).findViewById(R.id.ll1).getId(), tmf, "my_poster");
		ft.commit();
	}
	
	/**
	 * 优秀海报
	 */
	private void finePoster() {
		ft = mFragmentManager.beginTransaction();
		removeFragment();
		FinePosterFragment tmf = new FinePosterFragment();
		ft.add(listViews.get(currIndex).findViewById(R.id.ll5).getId(), tmf, "fine_poster");
		ft.commit();
		
	}
	
	/***
	 * 我的收藏
	 */
	private void myCollection() {
		ft = mFragmentManager.beginTransaction();
		removeFragment();
		MyCollectionFragment mcf = new MyCollectionFragment();
		ft.add(listViews.get(currIndex).findViewById(R.id.ll2).getId(), mcf, "my_collection");
		ft.commit();
		
	}
	
	/***
	 * 未做完的
	 */
	private void unfinished() {
		ft = mFragmentManager.beginTransaction();
		removeFragment();
		UnFinishFragment uff = new UnFinishFragment();
		ft.add(listViews.get(currIndex).findViewById(R.id.ll3).getId(), uff, "unfinished");
		ft.commit();
	}
	
	/**
	 * 模板库
	 */
	private void posterLib() {
		ft = mFragmentManager.beginTransaction();
		removeFragment();
		PosterLibFragment plf = new PosterLibFragment();
		ft.add(listViews.get(currIndex).findViewById(R.id.ll4).getId(), plf, "poster_lib");
		ft.commit();
	}
	
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.nav_poster_page:
			finish();
			break;
		case R.id.nav_new_make:
			navStyle(0);
			View popupView2 = LayoutInflater.from(context).inflate(R.layout.popupwindow_new_poster, null);
			mNewPosterPopupWindow = new NewPosterPopupWindow(context, popupView2);
			mNewPosterPopupWindow.showAtLocation(mRoot, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.nav_my_poster:
			navStyle(1);
			break;
		case R.id.nav_share:
			navStyle(2);
			Intent intent = new Intent(context,ShareActivity.class);
			startActivity(intent);
			break;
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
				/*Bitmap bitmap = BitmapFactory.decodeFile(url);
				Bitmap newBitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
				//由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
				bitmap.recycle();*/
				
				//将处理过的图片显示在界面上，并保存到本地
				/*Drawable drawables = new BitmapDrawable(newBitmap);
				mRoot.setBackgroundDrawable(drawables);
				ImageTools.savePhotoToSDCard(newBitmap, Environment.getExternalStorageDirectory().getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
				*/
				Intent intent = new Intent(context, EditPosterActivity.class);
				intent.putExtra("source", "camera");
				intent.putExtra("url", url);
				startActivity(intent);
				
				break;
			case CHOOSE_PICTURE:
				//照片的原始资源地址
				Uri originalUri = data.getData(); 
				Intent intents = new Intent(context, EditPosterActivity.class);
				intents.setData(originalUri);
				intents.putExtra("source", "choose_picture");
				startActivity(intents);
				break;
			
			default:
				break;
			}
		}
	}
	
	private void removeFragment(){
		MyPosterFragment mpf = (MyPosterFragment) mFragmentManager.findFragmentByTag("my_poster");
        if (mpf != null) {
            ft.remove(mpf);
        }
		MyCollectionFragment mcf = (MyCollectionFragment) mFragmentManager.findFragmentByTag("my_collection");
        if (mcf != null) {
            ft.remove(mcf);
        } 
        FinePosterFragment fpf = (FinePosterFragment) mFragmentManager.findFragmentByTag("fine_poster");
        if (fpf != null) {
            ft.remove(fpf);
        }
        UnFinishFragment uff = (UnFinishFragment) mFragmentManager.findFragmentByTag("unfinished");
        if (uff != null) {
            ft.remove(uff);
        }
        PosterLibFragment plf = (PosterLibFragment) mFragmentManager.findFragmentByTag("poster_lib");
        if (plf != null) {
            ft.remove(plf);
        }
	}
	
	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};
	
	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量
		int three = one * 3;
		int four = one * 4;
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				} else if(currIndex == 3){
					animation = new TranslateAnimation(three, 0, 0, 0);
				} else if(currIndex == 4){
					animation = new TranslateAnimation(four, 0, 0, 0);
				}
				currIndex = arg0;
				myPoster();
				textColor();
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				} else if (currIndex == 3){
					animation = new TranslateAnimation(three, one, 0, 0);
				} else if (currIndex == 4){
					animation = new TranslateAnimation(four, one, 0, 0);
				}
				currIndex = arg0;
				myCollection();
				textColor();
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
				} else if (currIndex == 4) {
					animation = new TranslateAnimation(four, two, 0, 0);
				}
				currIndex = arg0;
				unfinished();
				textColor();
				break;
			case 3:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, three, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
				} else if (currIndex == 4) {
					animation = new TranslateAnimation(four, three, 0, 0);
				}
				currIndex = arg0;
				posterLib();
				textColor();
				break;
			case 4:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, four, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, four, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, four, 0, 0);
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, four, 0, 0);
				}
				currIndex = arg0;
				finePoster();
				textColor();
				break;
			}
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	private void textColor(){
		if(currIndex == 0){
			mPoster.setTextColor(getResources().getColor(R.color.red_1));
			mCollection.setTextColor(getResources().getColor(R.color.black));
			mUnfinish.setTextColor(getResources().getColor(R.color.black));
			mLib.setTextColor(getResources().getColor(R.color.black));
			mFinePoster.setTextColor(getResources().getColor(R.color.black));
		} else if (currIndex == 1) {
			mCollection.setTextColor(getResources().getColor(R.color.red_1));
			mPoster.setTextColor(getResources().getColor(R.color.black));
			mUnfinish.setTextColor(getResources().getColor(R.color.black));
			mLib.setTextColor(getResources().getColor(R.color.black));
			mFinePoster.setTextColor(getResources().getColor(R.color.black));
		} else if (currIndex == 2) {
			mUnfinish.setTextColor(getResources().getColor(R.color.red_1));
			mPoster.setTextColor(getResources().getColor(R.color.black));
			mCollection.setTextColor(getResources().getColor(R.color.black));
			mLib.setTextColor(getResources().getColor(R.color.black));
			mFinePoster.setTextColor(getResources().getColor(R.color.black));
		} else if (currIndex == 3) {
			mLib.setTextColor(getResources().getColor(R.color.red_1));
			mPoster.setTextColor(getResources().getColor(R.color.black));
			mCollection.setTextColor(getResources().getColor(R.color.black));
			mUnfinish.setTextColor(getResources().getColor(R.color.black));
			mFinePoster.setTextColor(getResources().getColor(R.color.black));
		} else if (currIndex == 4) {
			mFinePoster.setTextColor(getResources().getColor(R.color.red_1));
			mPoster.setTextColor(getResources().getColor(R.color.black));
			mCollection.setTextColor(getResources().getColor(R.color.black));
			mUnfinish.setTextColor(getResources().getColor(R.color.black));
			mLib.setTextColor(getResources().getColor(R.color.black));
		}
	}
	
	private void navStyle(int arg0){
		if(arg0 == 0){
			Drawable newMake = getResources().getDrawable(R.drawable.make_newposter_press);
			newMake.setBounds(0, 0, newMake.getMinimumWidth(), newMake.getMinimumHeight());
			mNavNewMake.setCompoundDrawables(null, newMake, null, null);
			Drawable myPoster = getResources().getDrawable(R.drawable.myposter_normal);
			myPoster.setBounds(0, 0, myPoster.getMinimumWidth(), myPoster.getMinimumHeight());
			mNavMyPoster.setCompoundDrawables(null, myPoster, null, null);
			Drawable share = getResources().getDrawable(R.drawable.share_normal);
			share.setBounds(0, 0, share.getMinimumWidth(), share.getMinimumHeight());
			mNavShare.setCompoundDrawables(null, share, null, null);
			
			mNavNewMake.setTextColor(getResources().getColor(R.color.bg_red));
			mNavMyPoster.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
			mNavShare.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
		} else if (arg0 == 1){
			Drawable myPoster = getResources().getDrawable(R.drawable.myposter_press);
			myPoster.setBounds(0, 0, myPoster.getMinimumWidth(), myPoster.getMinimumHeight());
			mNavMyPoster.setCompoundDrawables(null, myPoster, null, null);
			Drawable newMake = getResources().getDrawable(R.drawable.make_newposter_normal);
			newMake.setBounds(0, 0, newMake.getMinimumWidth(), newMake.getMinimumHeight());
			mNavNewMake.setCompoundDrawables(null, newMake, null, null);
			Drawable share = getResources().getDrawable(R.drawable.share_normal);
			share.setBounds(0, 0, share.getMinimumWidth(), share.getMinimumHeight());
			mNavShare.setCompoundDrawables(null, share, null, null);
			
			mNavMyPoster.setTextColor(getResources().getColor(R.color.bg_red));
			mNavNewMake.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
			mNavShare.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
		} else if (arg0 == 2){
			Drawable share = getResources().getDrawable(R.drawable.share_press);
			share.setBounds(0, 0, share.getMinimumWidth(), share.getMinimumHeight());
			mNavShare.setCompoundDrawables(null, share, null, null);
			Drawable myPoster = getResources().getDrawable(R.drawable.myposter_normal);
			myPoster.setBounds(0, 0, myPoster.getMinimumWidth(), myPoster.getMinimumHeight());
			mNavMyPoster.setCompoundDrawables(null, myPoster, null, null);
			Drawable newMake = getResources().getDrawable(R.drawable.make_newposter_normal);
			newMake.setBounds(0, 0, newMake.getMinimumWidth(), newMake.getMinimumHeight());
			mNavNewMake.setCompoundDrawables(null, newMake, null, null);
			
			mNavShare.setTextColor(getResources().getColor(R.color.bg_red));
			mNavNewMake.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
			mNavMyPoster.setTextColor(getResources().getColor(R.color.poster_home_nav_text));
		}
	}
	
}
