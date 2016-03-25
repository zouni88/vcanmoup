package com.shenzhoumeiwei.vcanmou.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.utils.Constant;
import com.shenzhoumeiwei.vcanmou.view.QrcodePopupWindow;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class ShareActivity extends BaseActivity implements OnClickListener {
	private ShareActivity context = ShareActivity.this;

	// 微信分享API
	private IWXAPI wxApi;
	private QrcodePopupWindow mQrcodePopupWindow;
	private ImageButton mWx, mQrcode;

	private LinearLayout mRoot;
	
	private Button mReturn;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		//getWindow().setFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR, WindowManager.LayoutParams.TYPE_STATUS_BAR); 
		super.setContentView(R.layout.share_popupwindow);
		wxApi = WXAPIFactory.createWXAPI(context, Constant.WX_APP_ID);
		wxApi.registerApp(Constant.WX_APP_ID);

		findView();
		initView();
	
	}
	
	private void findView() {
		mRoot = (LinearLayout) super.findViewById(R.id.root);
		mWx = (ImageButton) super.findViewById(R.id.share_wx);
		mQrcode = (ImageButton) super.findViewById(R.id.share_qrcode);
		mReturn = (Button) super.findViewById(R.id.return_btn);

	}

	private void initView() {
		mWx.setOnClickListener(this);
		mQrcode.setOnClickListener(this);
		mReturn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.share_wx:
			wechatShare(0);
			break;
		case R.id.share_qrcode:
			View popupView2 = LayoutInflater.from(context).inflate(R.layout.share_qrcode_popupwindow, null);
			mQrcodePopupWindow = new QrcodePopupWindow(context, popupView2);
			mQrcodePopupWindow.showAtLocation(mRoot, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.return_btn:
			finish();
			break;
		}
	}

	/**
	 * 微信分享 （这里仅提供一个分享网页的示例，其它请参看官网示例代码）
	 * 
	 * @param flag
	 *            (0:分享到微信好友，1：分享到微信朋友圈)
	 */
	private void wechatShare(int flag) {
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = "这里填写链接url";
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = "这里填写标题";
		msg.description = "这里填写内容";
		// 这里替换一张自己工程里的图片资源
		Bitmap thumb = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.sophie);
		msg.setThumbImage(thumb);

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession
				: SendMessageToWX.Req.WXSceneTimeline;
		wxApi.sendReq(req);
	}

}
