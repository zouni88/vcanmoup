package com.shenzhoumeiwei.vcanmou.net;

import com.shenzhoumeiwei.vcanmou.utils.MD5;


/**
 * 用来保存Http请求的Url，访问方式，请求参数
 */
public class HttpRequest {

    /**
     * 定义HTTP请求方式
     */
    public static final int REQUEST_METHOD_HTTP_GET = 1; // HttpGet
    public static final int REQUEST_METHOD_HTTP_POST = 2; // HttpPost
    public static final int REQUEST_METHOD_HTTP_PUT = 3; // HttpPut
    public static final int REQUEST_METHOD_HTTP_DELETE = 4; // HttpDelete

    public static final int RESPONSE_TYPE_JSON = 0; // 返回的是json数据
    public static final int RESPONSE_TYPE_XML = 1; // 返回的是xml数据

    private String url; // url
    private int requestMethod; // 请求方式
    private int responseType; // 返回数据类型
    private BaseRequestParams mParams; // 参数
    private boolean cacheable; // 是否需要缓存
    private boolean forceRefresh; // 强制从网络获取数据
    private String md5Key; // 根据url和参数生成的md5字串

    /**
     * 请求数据的构造方法
     * 
     * @param url
     *            要请求的url
     * @param responseType
     *            返回数据类型(json,xml)
     * @param requestMethod
     *            请求方式(GET,POST,PUT,DELETE)
     */
    public HttpRequest(String url, int requestMethod, int responseType) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.responseType = responseType;
    }

    /**
     * 请求数据的构造方法
     * 
     * @param url
     *            要请求的url
     * @param requestMethod
     *            请求方式(GET,POST,PUT,DELETE)
     * @param responseType
     *            返回数据类型(json,xml)
     * @param params
     *            请求参数
     */
    public HttpRequest(String url, int requestMethod, int responseType, BaseRequestParams params) {
        this(url, requestMethod, responseType);
        this.mParams = params;
    }

    /**
     * 请求数据的构造方法
     * 
     * @param url
     *            要请求的url
     * @param requestMethod
     *            请求方式(GET,POST,PUT,DELETE)
     * @param responseType
     *            返回数据类型(json,xml)
     * @param cacheable
     *            是否需要缓存
     * @param forceRefresh
     *            是否强制刷新
     */
    public HttpRequest(String url, int requestMethod, int responseType, boolean cacheable,
            boolean forceRefresh) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.responseType = responseType;
        this.cacheable = cacheable;
        this.forceRefresh = forceRefresh;
        md5Key = MD5.encodeMD5String(url);
    }

    /**
     * 请求数据的构造方法
     * 
     * @param url
     *            要请求的url
     * @param requestMethod
     *            请求方式(GET,POST,PUT,DELETE)
     * @param responseType
     *            返回数据类型(json,xml)
     * @param cacheable
     *            是否需要缓存
     * @param forceRefresh
     *            是否强制刷新
     * @param params
     *            请求参数
     */
    public HttpRequest(String url, int requestMethod, int responseType, boolean cacheable,
            boolean forceRefresh, BaseRequestParams params) {
        this(url, requestMethod, responseType, cacheable, forceRefresh);
        this.mParams = params;
        md5Key = MD5.encodeMD5String(url + params.generateRequestParams());
    }

    public String getUrl() {
        return url;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    public int getResponseType() {
        return responseType;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public boolean isForceRefresh() {
        return forceRefresh;
    }

    public BaseRequestParams getmParams() {
        return mParams;
    }

    public String getMd5Key() {
        return md5Key;
    }

    @Override
    public String toString() {
        return "HttpRequest [url=" + url + ", requestMethod=" + requestMethod + ", responseType="
                + responseType + ", mParams=" + mParams + ", cacheable=" + cacheable
                + ", forceRefresh=" + forceRefresh + ", md5Key=" + md5Key + "]";
    }

}
