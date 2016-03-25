package com.shenzhoumeiwei.vcanmou.utils;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class Utils {
    private static final String TAG = "Utils";

    /**
     * 获取整型的ip地址(0表示设备未联网)
     * 
     * @param context
     *            上下文
     * @return 整型的ip地址
     */
    public static int getIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getIpAddress();
    }

    /**
     * 将整型的ip地址转换为点分十进制格式("192.168.21.30")
     * 
     * @param ip
     *            int型的ip地址
     * @return 点分十进制格式的ip
     */
    public static String getDecimalIpAddress(int ip) {
        // 分别取出每个字节的值
        return ((ip) & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "."
                + (ip >> 24 & 0xFF);
    }

    /**
     * 获取ip的网络号。如:"192.168.21.30",若子网掩码为"255.255.255.0",则前三位"192.168.21"为ip的网络号,
     * 最后一位"30"为ip的主机号
     * 
     * @param ip
     *            int型的ip地址
     * @return ip的网络号
     */
    public static String getIpNetAddress(int ip) {
        return ((ip) & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF);
    }

    /**
     * 获取ip的主机号。如:"192.168.21.30",若子网掩码为"255.255.255.0",则前三位"192.168.21"为ip的网络号,
     * 最后一位"30"为ip的主机号
     * 
     * @param ip
     *            int型的ip地址
     * @return ip的主机号
     */
    public static int getIpHostAddress(int ip) {
        // 右移24位后获取到的一个字节
        return (ip >> 24) & 0xFF;
    }

    /**
     * 根据菜品id获取对应的图片路径
     * 
     * @param c_id
     *            菜品id
     * @return 菜品的图片路径
     */
    public static String getDishImagePath(int c_id) {
        return Environment.getExternalStorageDirectory() + Constant.DOWNLOAD_CARTE_IMAGE_DIR + c_id
                + ".jpg";
    }

    /**
     * 根据菜品id获取对应的图片路径"file:///mnt/sdcard/image.png"
     * 
     * @param c_id
     *            菜品id
     * @return 菜品的图片路径
     */
    public static String getDishImageUri(int c_id) {
        return "file://" + Environment.getExternalStorageDirectory()
                + Constant.DOWNLOAD_CARTE_IMAGE_DIR + c_id + ".jpg";
    }

    /**
     * 收起输入法键盘
     * 
     * @param context
     *            Context
     * @param tokenView
     *            该输入法绑定的View
     */
    public static void colseInputMethod(Context context, View tokenView) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tokenView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打开输入法键盘
     * 
     * @param context
     *            Context
     * @param tokenView
     *            该输入法绑定的View
     */
    public static void openInputMethod(Context context, View tokenView) {
        tokenView.setFocusable(true);
        tokenView.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInputFromWindow(tokenView.getWindowToken(), 0, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 验证IP是否合法
     * 
     * @param ipAddress
     *            ip地址
     * @return ip合法返回true，否则返回false
     */
    public static boolean verifyIP(String ipAddress) {
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        if (ipAddress.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除文件或目录
     * 
     * @param file
     *            文件或目录
     * @return 是否删除成功
     */
    public static boolean deleteFile(File file) {
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            for (File childFile : childFiles) {
                deleteFile(childFile);
            }
        }
        return file.delete();
    }

    /**
     * 获取应用版本号
     * 
     * @param context
     *            上下文
     * @return 应用版本号
     */
    public static int getVersionCode(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (NameNotFoundException e) {
        }
        return 0;
    }

    /**
     * 获取设备的MAC地址
     * 
     * @param context
     *            上下文
     * @return 设备的MAC地址
     */
    public static String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null) {
            return wifiInfo.getMacAddress();
        }
        return null;
    }

    /*
     * 设置对话框大小
     */
    public static void setDialogSize(Activity activity, Dialog dialog) {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); // 获取对话框当前的参数值
        p.height = (int) d.getHeight(); // 高度设置为屏幕的高度
        p.width = (int) d.getWidth(); // 宽度设置为屏幕的宽度
        dialog.getWindow().setAttributes(p);
    }

    /**
     * Toast
     * 
     * @param msg
     *            提示内容
     */
    private static Toast mToast = null;

    public static void toast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    /**
     * Toast
     * 
     * @param resId
     *            字串资源id
     */
    public static void toast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    /**
     * 获取缓存目录
     * 
     * @return 缓存目录
     */
    public static String getCacheDir(Context context) {
        Log.i(TAG, "CacheDir = " + context.getExternalCacheDir());
        return context.getExternalCacheDir() + Constant.CACHE_DIR;
    }

    
}
