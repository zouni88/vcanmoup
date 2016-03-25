package com.shenzhoumeiwei.vcanmou.utils;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        MobclickAgent.openActivityDurationTrack(false);
        initImageLoader(this);
        //startPushMessageReceiverService(this);
    }

    private static void startPushMessageReceiverService(Context context) {
        /*Intent intent = new Intent(context, PushMessageReceiverService.class);
        context.startService(intent);*/
    }

    private static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(Constant.IMAGE_LOADER_CACHE_WIDTH,
                        Constant.IMAGE_LOADER_CACHE_HEIGHT)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

}
