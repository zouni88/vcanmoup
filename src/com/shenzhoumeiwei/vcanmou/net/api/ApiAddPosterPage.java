package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.AddPosterPage;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 添加海报页面
 */
public class ApiAddPosterPage extends HttpApiBase {

    private static final String TAG = "ApiAddPosterPage";

    /**
     * 添加海报页面
     * 
     * @param context
     *            上下文
     * @param params
     *            添加页面需要的参数
     */
    public ApiAddPosterPage(Context context, ApiAddPosterPageParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_ADD_POSTER_PAGE,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 添加海报页面需要的参数
     */
    public static class ApiAddPosterPageParams extends BaseRequestParams {
        private String PP_BackImage ;
        private String PP_Type;
        private String PP_Order;
        private String P_ID;
        private String PTM_ID;
        
        
        /**
         * 添加海报页面需要的参数
         */
        public ApiAddPosterPageParams(String PP_BackImage , String PP_Type, String PP_Order,String P_ID,String PTM_ID) {
            super();
            this.PP_BackImage  = PP_BackImage ;
            this.PP_Type = PP_Type;
            this.PP_Order = PP_Order;
            this.P_ID = P_ID;
            this.PTM_ID = PTM_ID;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PP_BackImage=" + PP_BackImage + "&PP_Type="+PP_Type+"&PP_Order="+PP_Order+"&P_ID="+P_ID+"&PTM_ID="+PTM_ID;
        }
    }

    /**
     * 添加海报页面返回结果
     */
    public static class ApiAddPosterPageResponse extends BaseResponse {
        public AddPosterPage addPosterPage; 
    }

    public ApiAddPosterPageResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiAddPosterPageResponse response = new ApiAddPosterPageResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.addPosterPage = gson.fromJson(baseResponse.getContent(), AddPosterPage.class);
            Log.i(TAG, "response.posterPageSortSort = " + response.addPosterPage);
        }
        return response;
    }

}
