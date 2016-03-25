package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.PosterPageSort;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 海报页面排序
 */
public class ApiPosterPageSort extends HttpApiBase {

    private static final String TAG = "ApiPosterPageSort";

    /**
     * 海报页面排序
     * 
     * @param context
     *            上下文
     * @param params
     *            排序需要的参数
     */
    public ApiPosterPageSort(Context context, ApiPosterPageSortParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_POSTER_PAGE_SORT,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 海报页面排序需要的参数
     */
    public static class ApiPosterPageSortParams extends BaseRequestParams {
        private String P_Id;
        private String startIndex;
        private String endIndex;
        
        
        /**
         * 海报页面排序需要的参数
         */
        public ApiPosterPageSortParams(String P_Id, String startIndex, String endIndex) {
            super();
            this.P_Id = P_Id;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?P_Id=" + P_Id + "&startIndex="+startIndex+"&endIndex="+endIndex;
        }
    }

    /**
     * 海报页面排序返回结果
     */
    public static class ApiPosterPageSortResponse extends BaseResponse {
        public PosterPageSort posterPageSort; 
    }

    public ApiPosterPageSortResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiPosterPageSortResponse response = new ApiPosterPageSortResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.posterPageSort = gson.fromJson(baseResponse.getContent(), PosterPageSort.class);
            Log.i(TAG, "response.posterPageSortSort = " + response.posterPageSort);
        }
        return response;
    }

}
