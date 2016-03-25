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

public class EditImgFragment extends Fragment {
	private final String TAG = "EditImgFragment";
	
	private Context context = getActivity();
    private View root;
    
    
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_edit_img, container, false);
		initView();
		initData();
		return root;
	}
	
	private void initView(){
	}
	
	private void initData(){
		
	}
}
