package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.GetPosterType;
import com.shenzhoumeiwei.vcanmou.model.UpdateTemplate;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 获取海报类型
 */
public class ApiGetPosterType extends HttpApiBase {

    private static final String TAG = "ApiGetPosterType";

    /**
     * 获取海报类型
     * 
     * @param context
     *            上下文
     * @param params
     *            获取海报类型需要的参数
     */
    public ApiGetPosterType(Context context, ApiGetPosterTypeParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_UPDATE_TEMPLATE_INFO,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 获取海报类型需要的参数
     */
    public static class ApiGetPosterTypeParams extends BaseRequestParams {

        private int PT_ID;
        private int PT_ParentID;
        
        
        /**
         * 获取海报类型需要的参数
         * 
         */
        public ApiGetPosterTypeParams(int PT_ID, int PT_ParentID) {
            super();
            this.PT_ID = PT_ID;
            this.PT_ParentID = PT_ParentID;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PT_ID=" + PT_ID + "&PT_ParentID="+PT_ParentID;
        }
    }

    /**
     * 获取海报类型返回结果
     */
    public static class ApiGetPosterTypeResponse extends BaseResponse {
        public GetPosterType getPosterType; 
    }

    public ApiGetPosterTypeResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiGetPosterTypeResponse response = new ApiGetPosterTypeResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.getPosterType = gson.fromJson(baseResponse.getContent(), GetPosterType.class);
            Log.i(TAG, "response.getPosterType = " + response.getPosterType);
        }
        return response;
    }

}
