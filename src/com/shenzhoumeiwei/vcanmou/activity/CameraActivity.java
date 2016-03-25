package com.shenzhoumeiwei.vcanmou.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.activity.CameraInterface.CamOpenOverCallback;
import com.shenzhoumeiwei.vcanmou.utils.DisplayUtil;
import com.shenzhoumeiwei.vcanmou.utils.FileUtil;
import com.shenzhoumeiwei.vcanmou.utils.ImageUtil;
import com.shenzhoumeiwei.vcanmou.utils.Utils;
import com.shenzhoumeiwei.vcanmou.view.CameraSurfaceView;

public class CameraActivity extends BaseActivity implements OnClickListener,OnCheckedChangeListener,
		CamOpenOverCallback {
	private final String TAG = "CameraActivity";
	private static final int TAKE_PICTURE = 0;
	private CameraActivity context = CameraActivity.this;
	private CameraSurfaceView surfaceView = null;
	private Button mShutterBtn;
	float previewRate = -1f;

	private TextView mReturn;
	private boolean isOpen = true;
	
	//打开闪光灯
	private CheckBox mCheckBox;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		Thread openThread = new Thread() {
			@Override
			public void run() {
				CameraInterface.getInstance().doOpenCamera(context);
			}
		};
		openThread.start();
		setContentView(R.layout.activity_camera);
		initView();
		initData();
		
	}

	private void initView() {
		surfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);
		mShutterBtn = (Button) findViewById(R.id.btn_shutter);
		mReturn = (TextView) super.findViewById(R.id.take_photo_return);
		mCheckBox = (CheckBox) super.findViewById(R.id.checkBox1);
	}

	private void initData() {
		mCheckBox.setOnCheckedChangeListener(this);
		mReturn.setOnClickListener(this);
		mShutterBtn.setOnClickListener(this);
		
		LayoutParams params = surfaceView.getLayoutParams();
		Point p = DisplayUtil.getScreenMetrics(this);
		params.width = p.x;
		params.height = p.y;
		previewRate = DisplayUtil.getScreenRate(this); // 默认全屏的比例预览
		surfaceView.setLayoutParams(params);

		// 手动设置拍照ImageButton的大小为120dip×120dip,原图片大小是64×64
		/*LayoutParams p2 = mShutterBtn.getLayoutParams();
		p2.width = DisplayUtil.dip2px(this, 80);
		p2.height = DisplayUtil.dip2px(this, 80);
		mShutterBtn.setLayoutParams(p2);*/
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_shutter:
			CameraInterface.getInstance().doTakePicture(mJpegPictureCallback);
			break;
		case R.id.take_photo_return:
			finish();
			break;
		case R.id.camera_switch:
			
			break;
		default:
			break;
		}
	}

	@Override
	public void cameraHasOpened() {
		SurfaceHolder holder = surfaceView.getSurfaceHolder();
		CameraInterface.getInstance().doStartPreview(holder, previewRate);
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		isOpen = CameraInterface.getInstance().setCameraFlash(isOpen);
	}
	
	PictureCallback mJpegPictureCallback = new PictureCallback() {
		//对jpeg图像数据的回调,最重要的一个回调
		public void onPictureTaken(byte[] data, Camera camera) {
			//Camera camera = CameraInterface.getInstance().getCamera();
			Log.i(TAG, "myJpegCallback:onPictureTaken...");
			Bitmap b = null;
			if(null != data){
				b = BitmapFactory.decodeByteArray(data, 0, data.length);//data是字节数据，将其解析成位图
				camera.stopPreview();
				//isPreviewing = false;
			}
			//保存图片到sdcard
			if(null != b){
				//设置FOCUS_MODE_CONTINUOUS_VIDEO)之后，myParam.set("rotation", 90)失效。
				//图片竟然不能旋转了，故这里要旋转下
				Bitmap rotaBitmap = ImageUtil.getRotateBitmap(b, 90.0f);
				String url = FileUtil.saveBitmap(rotaBitmap);
				//执行回调  返回到上个页面
				CameraInterface.getInstance().doStopCamera();
				Intent datas=new Intent();  
	            datas.putExtra("url", url);  
				setResult(RESULT_OK,datas);
			}
			Utils.toast(context, "拍照成功");
			context.finish();
		}
	};
}
