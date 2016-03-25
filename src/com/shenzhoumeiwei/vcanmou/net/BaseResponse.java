package com.shenzhoumeiwei.vcanmou.net;

/**
 * API response的基类
 */
public class BaseResponse {

    // 访问网络成功 {http : 200}
    public static final int RET_HTTP_STATUS_OK = 0;
    // 访问网络异常(result = 0)
    public static final int RET_HTTP_STATUS_ERROR = -1;
    // 返回数据是空(result != 0)
    public static final int RET_RESULT_STATUS_ERROR = -2;
    // 用户凭证过期(result:462)
    public static final int RET_RESULT_CREDENTIALS_EXPIRED = -3;
    // 获取缓存数据成功
    public static final int RET_CACHE_STATUS_OK = 1;
    // 获取缓存数据失败
    public static final int RET_CACHE_STATUS_ERROR = -4;

    private int retCode;
    private String retInfo;
    private String content;

    public int getRetCode() {
        return retCode;
    }

    public String getRetInfo() {
        return retInfo;
    }

    public String getContent() {
        return content;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "\n retCode =" + retCode + "\n retInfo =" + retInfo + "\n content = " + content;
    }

}
