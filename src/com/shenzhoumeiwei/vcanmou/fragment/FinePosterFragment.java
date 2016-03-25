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

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.adapter.FinePosterAdapter;
import com.shenzhoumeiwei.vcanmou.model.TestData;

public class FinePosterFragment extends Fragment {
	private final String TAG = "FinePosterFragment";
	
	private Context context = getActivity();
    private View root;
    
    private List mList;
    private GridView mGridView;
    private FinePosterAdapter mFinePosterAdapter;
    
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_fine_poster, container, false);
		initView();
		initData();
		return root;
	}
	
	private void initView(){
		mGridView = (GridView) root.findViewById(R.id.fine_poster_grid);
	}
	
	private void initData(){
		mFinePosterAdapter = new FinePosterAdapter(getActivity(), new ArrayList());
		mGridView.setAdapter(mFinePosterAdapter);
		mList = new ArrayList();
		for(int i = 0;i<20;i++){
			mList.add(new TestData());
		}
		mFinePosterAdapter.setData(mList);
	}
}
