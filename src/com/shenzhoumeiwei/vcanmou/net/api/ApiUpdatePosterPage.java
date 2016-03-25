package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.UpdatePosterPage;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 海报页面
 */
public class ApiUpdatePosterPage extends HttpApiBase {

    private static final String TAG = "ApiUpdatePosterPage";

    /**
     * 海报页面
     * 
     * @param context
     *            上下文
     * @param params
     *            生成文字图片需要的参数
     */
    public ApiUpdatePosterPage(Context context, ApiUpdatePosterPageParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_UPDATE_POSTER_PAGE,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 获取海报页面需要的参数
     */
    public static class ApiUpdatePosterPageParams extends BaseRequestParams {

        private String PP_Id;
        private String PP_BackImage;
        private String PP_Type;
        private String PP_Order;
        private String P_Id;
        private String PTM_ID;
        
        
        /**
         * 获取海报页面需要的参数
         * 
         */
        public ApiUpdatePosterPageParams(String PP_Id, String PP_BackImage, String PP_Type, String PP_Order, String P_Id, String PTM_ID) {
            super();
            this.PP_Id = PP_Id;
            this.PP_BackImage = PP_BackImage;
            this.PP_Type = PP_Type;
            this.PP_Order = PP_Order;
            this.P_Id = P_Id;
            this.PTM_ID = PTM_ID;
            
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PP_Id=" + PP_Id + "&PP_BackImage="+PP_BackImage + "&PP_Type="+PP_Type + "&PP_Order="+PP_Order + "&P_Id="+P_Id + "&PTM_ID="+PTM_ID;
        }
    }

    /**
     * 获取海报页面返回结果
     */
    public static class ApiUpdatePosterPageResponse extends BaseResponse {
        public UpdatePosterPage updatePosterPage; 
    }

    public ApiUpdatePosterPageResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiUpdatePosterPageResponse response = new ApiUpdatePosterPageResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.updatePosterPage = gson.fromJson(baseResponse.getContent(), UpdatePosterPage.class);
            Log.i(TAG, "response.posterPage = " + response.updatePosterPage);
        }
        return response;
    }

}
