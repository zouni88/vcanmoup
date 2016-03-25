package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.PublishPoster;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 发布海报
 */
public class ApiPublishPoster extends HttpApiBase {

    private static final String TAG = "ApiPublishPoster";

    /**
     * 发布海报
     * 
     * @param context
     *            上下文
     * @param params
     *            发布海报需要的参数
     */
    public ApiPublishPoster(Context context, ApiPublishPosterParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_PUBLISH_POSTER,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 发布海报需要的参数
     */
    public static class ApiPublishPosterParams extends BaseRequestParams {

        private String P_ID;
        private String MC_ID;
        
        
        /**
         * 发布海报需要的参数
         * 
         */
        public ApiPublishPosterParams(String P_ID, String MC_ID ) {
            super();
            this.P_ID = P_ID;
            this.MC_ID = MC_ID;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?P_ID=" + P_ID + "&MC_ID="+MC_ID ;
        }
    }

    /**
     * 发布海报返回结果
     */
    public static class ApiPublishPosterResponse extends BaseResponse {
        public PublishPoster publishPoster; 
    }

    public ApiPublishPosterResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiPublishPosterResponse response = new ApiPublishPosterResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.publishPoster = gson.fromJson(baseResponse.getContent(), PublishPoster.class);
            Log.i(TAG, "response.posterPage = " + response.publishPoster);
        }
        return response;
    }

}
