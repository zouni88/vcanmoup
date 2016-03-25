package com.shenzhoumeiwei.vcanmou.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.adapter.ImageAdapter;
import com.shenzhoumeiwei.vcanmou.adapter.LibGridAdapter;
import com.shenzhoumeiwei.vcanmou.model.TestData.TestGridData;
import com.shenzhoumeiwei.vcanmou.view.GuideGallery;

public class PosterLibFragment extends Fragment {
	private final String TAG = "PosterLibFragment";
	
	private Context context = getActivity();
	private int positon = 0;
    private View mRootView;
    public GuideGallery images_ga;

    private GridView mLibGrid;
    private LibGridAdapter mLibGridAdapter;
    private List list;
	private String[] pic ;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_lib, container, false);
		initView();
		initData();
		return mRootView;
	}
	
	private void initView(){
		images_ga = (GuideGallery) mRootView.findViewById(R.id.meal_pic); 
		mLibGrid = (GridView) mRootView.findViewById(R.id.lib_grid);
	}
	
	private void initData(){
		pic = new String[]{"http://images.17173.com/game/2007/10/16/20071016144940476.jpg","http://tupian.baike.com/a3_27_46_01300000010387120029462771177_jpg.html"};
		ImageAdapter imageAdapter = new ImageAdapter(getActivity(),pic,mcb);  
        images_ga.setAdapter(imageAdapter);  
        
        mLibGridAdapter = new LibGridAdapter(getActivity(), new ArrayList());
        mLibGrid.setAdapter(mLibGridAdapter);
        list = new ArrayList();
		for(int j = 0;j<5;j++){
			TestGridData tgd = new TestGridData();
			tgd.setName("什么皇家龙凤西施拍黄瓜之类de乱起吧早");
			list.add(tgd);	
		}
        mLibGridAdapter.setData(list);
	}
	
	public void changePointView(int cur){
    	LinearLayout pointLinear = (LinearLayout) mRootView.findViewById(R.id.gallery_point_linear);
    	View view = pointLinear.getChildAt(positon);
    	View curView = pointLinear.getChildAt(cur);
    	if(view!=null&& curView!=null){
    		ImageView pointView = (ImageView)view;
    		ImageView curPointView = (ImageView)curView;
    		pointView.setBackgroundResource(R.drawable.feature_point);
    		curPointView.setBackgroundResource(R.drawable.feature_point_cur);
    		positon = cur;
    	}
    }
	
	private MCallBack mcb = new MCallBack() {
		
		@Override
		public void result(int cur) {
			changePointView(cur);
		}
	};
	
	public interface MCallBack{
		public void result(int cur);
	}
}
