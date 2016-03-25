package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.PosterPage;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 海报页面
 */
public class ApiPosterPage extends HttpApiBase {

    private static final String TAG = "ApiPosterPage";

    /**
     * 海报页面
     * 
     * @param context
     *            上下文
     * @param params
     *            生成文字图片需要的参数
     */
    public ApiPosterPage(Context context, ApiPosterPageParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_POSTER_PAGE,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 获取海报页面需要的参数
     */
    public static class ApiPosterPageParams extends BaseRequestParams {

        private String PP_Id;
        private String P_Id;
        
        
        /**
         * 获取海报页面需要的参数
         * 
         */
        public ApiPosterPageParams(String PP_Id, String P_Id) {
            super();
            this.PP_Id = PP_Id;
            this.P_Id = P_Id;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PP_Id=" + PP_Id + "&P_Id="+P_Id;
        }
    }

    /**
     * 获取海报页面返回结果
     */
    public static class ApiPosterPageResponse extends BaseResponse {
        public PosterPage posterPage; 
    }

    public ApiPosterPageResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiPosterPageResponse response = new ApiPosterPageResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.posterPage = gson.fromJson(baseResponse.getContent(), PosterPage.class);
            Log.i(TAG, "response.posterPage = " + response.posterPage);
        }
        return response;
    }

}
