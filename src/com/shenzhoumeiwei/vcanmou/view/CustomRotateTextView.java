package com.shenzhoumeiwei.vcanmou.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomRotateTextView extends TextView implements OnLongClickListener {
	private final String TAG = "CustomView";
	private static final int FIX_NO = 3;

	/**
	 * 到中心点的距离
	 * @param context
	 * @param attrs
	 */
	float toCenterDistance = 1;
	int vX = 0;
	int vY = 0;
	
	int lastX ,lastY;
	int mFix = FIX_NO;
	
	private int left = 0;
	private int top = 0;
	private int right = 0;
	private int bottom = 0;
	
	private OnClickListener mL;
	public CustomRotateTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
	
	long a ;
	long b;
	
	@SuppressLint("WrongCall")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = 0;
		int y = 0;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			a = System.currentTimeMillis();
			vX = getWidth();
			vY = getHeight();
			
			//   按下的时候距离屏幕左上角的距离
			lastX = (int) event.getRawX();
			lastY = (int) event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			//某个点到中心的距离
			float pX = event.getX();
			float pY = event.getY();
			float aPointToCenterDistance = FloatMath.sqrt(pX * pX + pY * pY);
			Log.e(TAG,"点击X=="+aPointToCenterDistance);
			// 右上角点到中心的距离
			x = getWidth()/2;
			y = getHeight()/2;
			toCenterDistance = FloatMath.sqrt(x * x + y * y);
			Log.e(TAG,"x="+event.getX()+",y="+event.getY()+"|width="+getWidth()+",height="+getHeight());
			
			if(aPointToCenterDistance >= toCenterDistance && event.getX() >= getWidth()-50 && event.getY() >= getHeight() -50){
				/*Log.i(TAG, "初始化中心距离="+toCenterDistance);	
				MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
				left = mlp.leftMargin;
				top = mlp.topMargin;
				right = mlp.rightMargin;
				bottom = mlp.bottomMargin;
				RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT); 
				lp.width = (int) (event.getX());
				lp.height = (int) (event.getY());
				lp.setMargins(getLeft(), getTop(), right, bottom);
				setLayoutParams(lp);
				*/
			}else{
				//移动的时候距离屏幕左上角的距离
				int nowX = (int)event.getRawX();
				int nowY = (int)event.getRawY();
				// X轴和Y轴移动的距离
				int moveX = nowX - lastX;
				int moveY = nowY -lastY;
				// 分别计算距离
				top = getTop() + moveY;
				bottom = getBottom() + moveY;
				left =  getLeft() + moveX;
				right = getRight() + moveX;
				
				// 这个地方是控制 那个轴固定的的 
				//layout(left, top, right, bottom);
				setTop(top);
				setBottom(bottom);
				setLeft(left);
				setRight(right);
				invalidate(left, top, right, bottom);
				lastX = (int) event.getRawX();
				lastY = (int) event.getRawY();	
				
			}
			
			Log.e(TAG,"left="+left+",top="+top+",right="+right+",bottom="+bottom);
			break;
		case MotionEvent.ACTION_UP:
			b = System.currentTimeMillis();
			Log.e(TAG,"点击时间差=="+(b-a));
			if(b - a <=100){
				if(mL != null){
					mL.onClick();
				}
			}
			break;
		}
		return true;
	}
	
	@Override
	public boolean onLongClick(View arg0) {
		
		return false;
	}
	
	public void setOnClickListener(OnClickListener l){
		this.mL = l;
	}
	
	public interface OnClickListener{
		void onClick();
	}
}
