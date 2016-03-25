package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.TemplateInfo;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 获取模板信息
 */
public class ApiGetTemplateInfo extends HttpApiBase {

    private static final String TAG = "ApiGetTemplateInfo";

    /**
     * 获取模板信息
     * 
     * @param context
     *            上下文
     * @param params
     *            获取模板信息需要的参数
     */
    public ApiGetTemplateInfo(Context context, ApiGetTemplateInfoParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_GET_TEMPLATE_INFO,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 获取模板信息需要的参数
     */
    public static class ApiGetTemplateInfoParams extends BaseRequestParams {

        private String PTM_Id;
        private String PT_Ids;
        private String PageIndex;
        private String PageSize;
        
        
        /**
         * 获取模板信息需要的参数
         * 
         */
        public ApiGetTemplateInfoParams(String PTM_Id, String PT_Ids, String PageIndex, String PageSize) {
            super();
            this.PTM_Id = PTM_Id;
            this.PT_Ids = PT_Ids;
            this.PageIndex = PageIndex;
            this.PageSize = PageSize;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PTM_Id=" + PTM_Id + "&PT_Ids="+PT_Ids + "&PageIndex="+PageIndex + "&PageSize="+PageSize ;
        }
    }

    /**
     * 获取模板信息返回结果
     */
    public static class ApiGetTemplateInfoResponse extends BaseResponse {
        public TemplateInfo templateInfo; 
    }

    public ApiGetTemplateInfoResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiGetTemplateInfoResponse response = new ApiGetTemplateInfoResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.templateInfo = gson.fromJson(baseResponse.getContent(), TemplateInfo.class);
            Log.i(TAG, "response.templateInfo = " + response.templateInfo);
        }
        return response;
    }

}
