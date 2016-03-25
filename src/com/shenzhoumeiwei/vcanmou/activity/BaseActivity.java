package com.shenzhoumeiwei.vcanmou.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shenzhoumeiwei.vcanmou.R;

@SuppressLint("SimpleDateFormat")
public class BaseActivity extends FragmentActivity {
	private static final String TAG = "BaseActivity";
	private Dialog mDialog; // 自定义的Dialog
	private Dialog mErrorDialog;// 错误提示
	private boolean mIsVisiable;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		mDialog = new Dialog(this, R.style.CustomLoadingDialog);
		mDialog.setContentView(R.layout.custom_loading_dialog);

		mErrorDialog = new Dialog(this, R.style.CustomLoadingDialog);
		mErrorDialog.setContentView(R.layout.error_dialog);
		Button close = (Button) mErrorDialog.findViewById(R.id.close);
		close.setOnClickListener(ocl);

	}

	/**
	 * 返回
	 */
	public OnClickListener onBackClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};

	/**
	 * 显示Dialog
	 * 
	 * @param content
	 *            Dialog中要显示的内容
	 */
	public void showCustomDialog(String content) {
		TextView tv = (TextView) mDialog.findViewById(R.id.message);
		tv.setText(content);
		mDialog.show();
	}

	/**
	 * 显示Dialog
	 * 
	 * @param resid
	 *            Dialog中要显示的内容
	 */
	public void showCustomDialog(int resid) {
		TextView tv = (TextView) mDialog.findViewById(R.id.message);
		tv.setText(resid);
		mDialog.show();
	}

	/**
	 * 取消Dialog
	 */
	public void dismissCustomDialog() {
		if (mDialog != null && mIsVisiable) {
			mDialog.dismiss();
		}
	}

	/**
	 * 显示错误提示Dialog
	 * 
	 * @param content
	 *            Dialog中要显示的内容
	 */
	public void showCustomNoticeDialog(String content) {
		TextView tv = (TextView) mErrorDialog.findViewById(R.id.error_message);
		tv.setText(content);
		mErrorDialog.show();
	}

	/**
	 * 显示Dialog
	 * 
	 * @param resid
	 *            Dialog中要显示的内容
	 */
	public void showCustomNoticeDialog(int resid) {
		TextView tv = (TextView) mErrorDialog.findViewById(R.id.error_message);
		tv.setText(resid);
		mErrorDialog.show();
	}

	/**
	 * 取消Dialog
	 */
	public void dismissCustomNoticeDialog() {
		if (mErrorDialog != null && mIsVisiable) {
			mErrorDialog.dismiss();
		}
	}

	private OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.close:
				dismissCustomNoticeDialog();
				break;
			}
		}
	};

	/**
	 * Toast
	 * 
	 * @param msg
	 *            提示内容
	 */
	private static Toast mToast = null;

	public void toast(String msg) {
		if (this != null) {
			if (mToast == null) {
				mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
			} else {
				mToast.setText(msg);
				mToast.setDuration(Toast.LENGTH_SHORT);
			}

			mToast.show();
		}
	}

	/**
	 * Toast
	 * 
	 * @param resId
	 *            字串资源id
	 */
	public void toast(int resId) {
		if (this != null) {
			if (mToast == null) {
				mToast = Toast.makeText(this, resId, Toast.LENGTH_SHORT);
			} else {
				mToast.setText(resId);
				mToast.setDuration(Toast.LENGTH_SHORT);
			}

			mToast.show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mIsVisiable = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		mIsVisiable = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
