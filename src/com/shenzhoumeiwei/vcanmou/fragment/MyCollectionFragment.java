package com.shenzhoumeiwei.vcanmou.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenzhoumeiwei.vcanmou.R;

public class MyCollectionFragment extends Fragment {
	private final String TAG = "MyCollectionFragment";
	
	private Context context = getActivity();
    private View root;
    
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_collection, container, false);
		initView();
		initData();
		return root;
	}
	
	private void initView(){
		
	}
	
	private void initData(){
		
	}
}
