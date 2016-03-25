package com.shenzhoumeiwei.vcanmou.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;

public class ChooseBoardPopupWindow extends PopupWindow implements OnClickListener {
	private final String TAG = "TextEditActivity";
	
	private Context context;
	private View mRootView = null;
	
	private View mNullView;
	private TextView mReturn;
	private Button mAddTextBtn,mAddImgBtn;
	
	private EditTextCallback mEtc;
	public ChooseBoardPopupWindow(Context context, View popupView) {
		super(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		this.context = context;
        mRootView = popupView;
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        
        initView();
        initData();
	}
	
	private void initView(){
		mReturn = (TextView) mRootView.findViewById(R.id.return_dismiss);
		mReturn.setOnClickListener(this);
		
		mAddTextBtn = (Button) mRootView.findViewById(R.id.add_text_board);
		mAddImgBtn = (Button) mRootView.findViewById(R.id.add_img_board);
		mAddTextBtn.setOnClickListener(this);
		mAddImgBtn.setOnClickListener(this);
	}
	
	private void initData(){
		
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.add_img_board:
			this.mEtc.result(2);
			dismiss();
			break;
		case R.id.add_text_board:
			this.mEtc.result(1);
			dismiss();
			break;
		case R.id.return_dismiss:
			dismiss();
			break;
		}
	}
	
	public void setCallBack(EditTextCallback etc){
		this.mEtc = etc;
	}
	
	
	public interface EditTextCallback {
		public void result(int board);
	}
}
