package com.shenzhoumeiwei.vcanmou.activity; 
  
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.view.DefinedScrollView;
/////import android.graphics.drawable.Drawable.Callback;
/*import com.tab.R; 
import com.tab.bitmap.ImageLoader; 
import com.tab.bitmap.ImageLoader.Callback; 
import com.tab.entity.ArticleInfo; 
import com.tab.entity.MagaInfo; 
import com.tab.utils.Constant; 
import com.tab.utils.DefinedScrollView; 
import com.tab.utils.JsonParse; */
  
/** 
* 利用自定义的ScrollView加载视图来实现翻页效果，下面用页码和总页数标识当前的视图是第几屏 
*  
* @author WANGXIAOHONG 
*  
*/
public class SaveAndPreviewActivity extends BaseActivity { 
	private LinearLayout mLinearLayout; 
	private LinearLayout.LayoutParams param; 
	private DefinedScrollView scrollView; 
	private LayoutInflater inflater; 
	private TextView mTextView; 
	private ImageView mImageViewMagaPic; 
	private int pageCount = 0; 
  
/*	private JsonParse parse; 
	private MagaInfo magaInfo; 
	private List<ArticleInfo> articleInfos; 
	private ArrayList<List<ArticleInfo>> list = new ArrayList<List<ArticleInfo>>(); 
  
	private String baseUrl = "http://192.168.100.8:4101/Express.ashx?page=1&filename="; 
	// 对应不同的期刊杂志或文献 
	private String filename = "DYCZ200206"; 
	private ImageLoader loader; */
  
	private void setupView() { 
		scrollView = (DefinedScrollView) findViewById(R.id.definedview); 
		mTextView = (TextView) findViewById(R.id.text_page); 
	  
/*		Constant.LOADPICTYPE = 2; 
	  
		// 将JSon数据解析为对象 
		parse = new JsonParse(); 
		magaInfo = parse.getJsonObjectOfMaga(parse.getJsonFromNet(baseUrl 
		+ filename)); 
		Log.i("info", "JSONString=========="+ parse.getJsonFromNet(baseUrl + filename)); 
		pageCount = magaInfo.getMageTotalPage(); */
		pageCount = 2;
	  
		mTextView.setText(1 + "/" + pageCount); 
	  
		for (int i = 0; i < pageCount; i++) { 
			param = new LinearLayout.LayoutParams( 
			android.view.ViewGroup.LayoutParams.FILL_PARENT, 
			android.view.ViewGroup.LayoutParams.FILL_PARENT); 
			inflater = this.getLayoutInflater(); 
		  
			if (i == 0) { 
				final View addview = inflater.inflate( R.layout.activity_text_customview, null); 
			/*	TextView magaName = (TextView) addview 
				.findViewById(R.id.text_magaName); 
				TextView magacode = (TextView) addview 
				.findViewById(R.id.text_magacode); 
				TextView art1Title = (TextView) addview 
				.findViewById(R.id.text1); 
				TextView art2Title = (TextView) addview 
				.findViewById(R.id.text2); 
				TextView art3Title = (TextView) addview 
				.findViewById(R.id.text3); 
				TextView art4Title = (TextView) addview 
				.findViewById(R.id.text4); 
				TextView art5Title = (TextView) addview 
				.findViewById(R.id.text5); 
				mImageViewMagaPic = (ImageView) addview 
				.findViewById(R.id.iv_picmagazine); */
				////mImageViewMagaPic.setTag(addview); 
		/*		magaName.setText(magaInfo.getMagaName().toString()); 
				magacode.setText(magaInfo.getErrorCode().toString()); 
				articleInfos = magaInfo.getArtInfos(); 
				art1Title.setText(articleInfos.get(0).getTitle().toString()); 
				art2Title.setText(articleInfos.get(1).getTitle().toString()); 
				art3Title.setText(articleInfos.get(2).getTitle().toString()); 
				art4Title.setText(articleInfos.get(3).getTitle().toString()); 
				art5Title.setText(articleInfos.get(4).getTitle().toString()); */
			  
/*				final String picPath = magaInfo.getMagePicPath(); 
				Log.i("info", "picpath============" + picPath); 
			
				loader.imageLoad(picPath, new Callback() { 
					@Override
					public void imageloaded(String path, Bitmap bitmap) { 
						mImageViewMagaPic.setImageBitmap(bitmap); 
					} 
				}); */
			  
				mLinearLayout = new LinearLayout(this); 
				mLinearLayout.addView(addview, param); 
				scrollView.addView(mLinearLayout); 
			} else { 
				View addview = inflater.inflate(R.layout.activity_text_customview, null); 
				/*TextView art1Title = (TextView) addview 
				.findViewById(R.id.text6); 
				TextView art2Title = (TextView) addview 
				.findViewById(R.id.text7); 
				TextView art3Title = (TextView) addview 
				.findViewById(R.id.text8); 
				TextView art4Title = (TextView) addview 
				.findViewById(R.id.text9); 
				TextView art5Title = (TextView) addview 
				.findViewById(R.id.text10); 
				TextView art6Title = (TextView) addview 
				.findViewById(R.id.text11); 
				TextView art7Title = (TextView) addview 
				.findViewById(R.id.text12); */
				
				/*articleInfos = magaInfo.getArtInfos(); 
				art1Title.setText(articleInfos.get(0).getTitle().toString()); 
				art2Title.setText(articleInfos.get(1).getTitle().toString()); 
				art3Title.setText(articleInfos.get(2).getTitle().toString()); 
				art4Title.setText(articleInfos.get(3).getTitle().toString()); 
				art5Title.setText(articleInfos.get(4).getTitle().toString()); 
				art6Title.setText("测试1"); 
				art7Title.setText("测试2");*/ 
				
				mLinearLayout = new LinearLayout(this); 
				mLinearLayout.addView(addview, param); 
				scrollView.addView(mLinearLayout); 
			}

	} 

	scrollView.setPageListener(new DefinedScrollView.PageListener() {
		@Override
		public void page(int page) { 
			setCurPage(page); 
		}
	}); 
} 
  
	private void setCurPage(int page) { 
		mTextView.setText((page + 1) + "/" + pageCount); 
	}
  
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_preview); 
		setupView(); 
	}
}