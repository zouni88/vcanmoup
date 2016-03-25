package com.shenzhoumeiwei.vcanmou.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.view.Switch;

public class PageSetActivity extends BaseActivity implements OnCheckedChangeListener {
	private final String TAG = "EditImgFragment";
	
	private PageSetActivity context = PageSetActivity.this;
    private View root;
    private Switch mSetNoticeSwitch;
    
    @Override
    protected void onCreate(Bundle bundle) {
    	super.onCreate(bundle);
    	super.setContentView(R.layout.fragment_page_set);
    	initView();
		initData();
    }
    
	private void initView() {
		mSetNoticeSwitch = (Switch) super.findViewById(R.id.set_notice_switch);
		mSetNoticeSwitch.setOnCheckedChangeListener(this);
	}
	
	private void initData(){
		
	}
	
	@Override
    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
        switch (arg0.getId()) {
        case R.id.set_notice_switch:
            if (arg1) {
                
            } else {
            	
            }
            break;
        }
    }
}
