package com.shenzhoumeiwei.vcanmou.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.fragment.PosterLibFragment.MCallBack;

public class ImageAdapter extends BaseAdapter{  
    private Context context;  
    private ImageAdapter self;
    Uri uri;
    Intent intent;
    ImageView imageView;
    private MCallBack mcb;
    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions = null;
   	public String[] mPic;
    public ImageAdapter(Context context,String[] pic,MCallBack mcb) {  
        this.context = context;  
        mOptions = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.card_default_img_big)
        .showImageForEmptyUri(R.drawable.card_default_img_big).cacheOnDisc(true)
        .showImageOnFail(R.drawable.card_default_img_big).cacheInMemory(true).build();
        this.self = this;
        this.mPic = pic;
        this.mcb = mcb;
    }  
  
    public int getCount() {  
        return Integer.MAX_VALUE;  
    }  
  
    public Object getItem(int position) {  
        return mPic[position % mPic.length];  
    }
   
    public long getItemId(int position) {  
        return position;  
    }  
  
    @SuppressWarnings("unused")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				switch (msg.what) {
					case 0: {
						self.notifyDataSetChanged();
					}
					break;
				}

				super.handleMessage(msg);
			} catch (Exception e) {
			}
		}
	};
    
    public View getView(int position, View convertView, ViewGroup parent) {  
        if(convertView==null){  
            convertView = LayoutInflater.from(context).inflate(R.layout.pic_item,null); //实例化convertView  
            Gallery.LayoutParams params = new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT,Gallery.LayoutParams.WRAP_CONTENT);
            convertView.setLayoutParams(params);
            convertView.setTag(mPic);  
        }  
        imageView = (ImageView) convertView.findViewById(R.id.gallery_image);  
        mImageLoader.displayImage(mPic[position % mPic.length], imageView, mOptions);
        //设置缩放比例：保持原样  	
        mcb.result(position % mPic.length); 
        return convertView;  
    }   
  
}  
