package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.DeletePosterPage;
import com.shenzhoumeiwei.vcanmou.model.PosterPage;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 删除海报页面
 */
public class ApiDeletePosterPage extends HttpApiBase {

    private static final String TAG = "ApiDeletePosterPage";

    /**
     * 删除海报页面
     * 
     * @param context
     *            上下文
     * @param params
     *            删除海报页面需要的参数
     */
    public ApiDeletePosterPage(Context context, ApiDeletePosterPageParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_DELETE_POSTER_PAGE,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 删除海报页面需要的参数
     */
    public static class ApiDeletePosterPageParams extends BaseRequestParams {

        private String PP_Id;
        
        /**
         * 删除海报页面需要的参数
         * 
         */
        public ApiDeletePosterPageParams(String PP_Id) {
            super();
            this.PP_Id = PP_Id;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PP_Id=" + PP_Id ;
        }
    }

    /**
     * 获取海报页面返回结果
     */
    public static class ApiDeletePosterPageResponse extends BaseResponse {
        public DeletePosterPage deletePosterPage; 
    }

    public ApiDeletePosterPageResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiDeletePosterPageResponse response = new ApiDeletePosterPageResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.deletePosterPage = gson.fromJson(baseResponse.getContent(), DeletePosterPage.class);
            Log.i(TAG, "response.deletePosterPage = " + response.deletePosterPage);
        }
        return response;
    }

}
