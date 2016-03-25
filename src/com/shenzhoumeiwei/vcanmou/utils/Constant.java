package com.shenzhoumeiwei.vcanmou.utils;

public class Constant {

    public static final boolean DEBUG = true;
    // WX返回正确数据对应的resultCode
    public static final boolean RET_CODE_RESULT_SUCCESS = true;
    // WX返回用户凭证过期对应的resultCode
    public static final int RET_CODE_CREDENTIALS_EXPIRED = 462;

    /**
     * 分享 微信APPID
     */
    public static final String WX_APP_ID = "123456789";
    
    /**
     * 端口号
     */
    public static final int WX_SERVER_PORT = 17425; // wx服务器的默认端口号
    public static final int WX_SERVER_PUSH_MESSAGE_PORT = 15501; // wx服务器消息推送的默认端口号

    /**
     * all 接口信息
     */
    public static final String HTTP_BASE_URL = "http://192.168.20.96:8056/";
    public static final String HTTP_UP_IMAGE_URL = HTTP_BASE_URL + "ImagesManager/UpLoad";//上传图片
    public static final String HTTP_CREATE_TEXT_IMG = HTTP_BASE_URL + "ImagesManager/CreateImage";//生成文本图片
    public static final String HTTP_GET_BOARD_INFO = HTTP_BASE_URL + "PosterPageElementsAPI/Get";//获取版块信息
    public static final String HTTP_ADD_BOARD_INFO = HTTP_BASE_URL + "PosterPageElementsAPI/Add";//添加版块
    public static final String HTTP_UPDATE_BOARD = HTTP_BASE_URL + "PosterPageElementsAPI/Update";//修改板块接口
    public static final String HTTP_DELETE_BOARD = HTTP_BASE_URL + "PosterPageElementsAPI/Delete";//删除板块接口
    public static final String HTTP_POSTER_PAGE = HTTP_BASE_URL + "PosterPagesAPI/Get";//获取海报页面接口
    public static final String HTTP_POSTER_PAGE_SORT = HTTP_BASE_URL + "PosterPagesAPI/PosterPagesOrder";//海报页面排序接口
    public static final String HTTP_ADD_POSTER_PAGE= HTTP_BASE_URL + "PosterPagesAPI/Add";//添加海报页面接口
    public static final String HTTP_UPDATE_POSTER_PAGE= HTTP_BASE_URL + "PosterPagesAPI/Update";//修改海报页面接口
    public static final String HTTP_DELETE_POSTER_PAGE= HTTP_BASE_URL + "PosterPagesAPI/Delete";//删除海报页面接口
    //海报管理接口
    public static final String HTTP_POSTER_GET = HTTP_BASE_URL + "PostersAPI/Get";//获取海报信息接口
    public static final String HTTP_ADD_POSTER = HTTP_BASE_URL + "PostersAPI/Add";//添加海报接口
    public static final String HTTP_UPDATE_POSTER = HTTP_BASE_URL + "PostersAPI/Update";//修改海报接口
    public static final String HTTP_PUBLISH_POSTER = HTTP_BASE_URL + "PostersAPI/Publish";//发布海报接口
    public static final String HTTP_GET_TEMPLATE_INFO = HTTP_BASE_URL + "PosterTemplatesAPI/Get";//获取模板信息接口
    public static final String HTTP_ADD_TEMPLATE_INFO = HTTP_BASE_URL + "PosterTemplatesAPI/Add";//添加海报模板接口
    public static final String HTTP_UPDATE_TEMPLATE_INFO = HTTP_BASE_URL + "PosterTemplatesAPI/Update";//修改海报模板接口
    
    
    /**
     * SharedPreference
     */
    public static final String PREFERENCE_FILE = "emenu_clerk_preference"; // sharedpreferenc文件名

    /**
     * 菜品图片的下载目录及宽高
     */
    public static final String DOWNLOAD_CARTE_IMAGE_DIR = "/emenu_clerk/images/"; // 菜品图片的下载目录
    public static final String DOWNLOAD_CARTE_IMAGE_WIDTH = "314"; // 菜品图片的宽度
    public static final String DOWNLOAD_CARTE_IMAGE_HEIGHT = "375"; // 菜品图片的高度

    /**
     * Bundle keys
     */
    public static final String BUNDLE_KEY_DISH_C_ID = "dish_c_id"; // bundle中的菜品id

    public static final int IMAGE_LOADER_CACHE_WIDTH = 150; // 菜品图片的高度
    public static final int IMAGE_LOADER_CACHE_HEIGHT = 150; // 菜品图片的高度

    // 缓存文件夹
    public static final String CACHE_DIR = "vcanmou/";

}
