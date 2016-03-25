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
import android.widget.ListView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.adapter.MyPosterAdapter;
import com.shenzhoumeiwei.vcanmou.model.TestData;
import com.shenzhoumeiwei.vcanmou.model.TestData.TestGridData;

public class MyPosterFragment extends Fragment {
	private final String TAG = "FinePosterFragment";
	
	private Context context = getActivity();
    private View root;
    
    private ListView mFinePosterList;
    private List list = new ArrayList();
    private MyPosterAdapter mFinePosterAdapter;
    
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_my_poster, container, false);
		initView();
		initData();
		
		return root;
	}
	
	private void initView(){
		mFinePosterList = (ListView) root.findViewById(R.id.fine_poster_list);
		
	}
	
	private void initData(){
		mFinePosterAdapter = new MyPosterAdapter(getActivity(), new ArrayList());
		mFinePosterList.setAdapter(mFinePosterAdapter);
		for(int i = 0;i<10;i++){
			TestData td = new TestData();
			td.setDate("2015110"+i);
			List lists = new ArrayList();
			for(int j = 0;j<5;j++){
				TestGridData tgd = new TestGridData();
				tgd.setName("什么皇家龙凤西施拍黄瓜之类de乱起吧早");
				lists.add(tgd);	
			}
			td.setTestGridData(lists);
			list.add(td);
		}
		mFinePosterAdapter.setData(list);
	}
	
	
}
