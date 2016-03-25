package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.PosterInfo;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 获取海报信息
 */
public class ApiGetPosterInfo extends HttpApiBase {

    private static final String TAG = "ApiGetPosterInfo";

    /**
     * 获取海报信息
     * 
     * @param context
     *            上下文
     * @param params
     *           获取海报信息需要的参数
     */
    public ApiGetPosterInfo(Context context, ApiGetPosterInfoParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_POSTER_GET,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 获取海报信息需要的参数
     */
    public static class ApiGetPosterInfoParams extends BaseRequestParams {

        private String MC_ID;
        private String P_Id;
        private String PTM_ID;
        private String PageIndex;
        private String PageSize;
        
        
        /**
         * 获取海报信息需要的参数
         * 
         */
        public ApiGetPosterInfoParams(String MC_ID, String P_Id, String PTM_ID, String PageIndex, String PageSize) {
            super();
            this.MC_ID = MC_ID;
            this.P_Id = P_Id;
            this.PTM_ID = PTM_ID;
            this.PageIndex = PageIndex;
            this.PageSize = PageSize;
            
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?MC_ID=" + MC_ID + "&P_Id="+P_Id + "&PTM_ID="+PTM_ID + "&PageIndex="+PageIndex + "&PageSize="+PageSize;
        }
    }

    /**
     * 获取海报信息返回结果
     */
    public static class ApiGetPosterInfoResponse extends BaseResponse {
        public PosterInfo posterBoard; 
    }

    public ApiGetPosterInfoResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiGetPosterInfoResponse response = new ApiGetPosterInfoResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.posterBoard = gson.fromJson(baseResponse.getContent(), PosterInfo.class);
            Log.i(TAG, "response.posterPage = " + response.posterBoard);
        }
        return response;
    }

}
