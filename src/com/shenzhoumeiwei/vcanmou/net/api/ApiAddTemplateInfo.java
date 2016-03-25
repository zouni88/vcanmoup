package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.AddTemplate;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 添加模板信息
 */
public class ApiAddTemplateInfo extends HttpApiBase {

    private static final String TAG = "ApiAddTemplateInfo";

    /**
     * 添加模板信息
     * 
     * @param context
     *            上下文
     * @param params
     *            添加模板信息需要的参数
     */
    public ApiAddTemplateInfo(Context context,ApiAddTemplateInfoParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_ADD_TEMPLATE_INFO,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 添加模板信息需要的参数
     */
    public static class ApiAddTemplateInfoParams extends BaseRequestParams {

        private String PTM_Title;
        private int PTM_Order;
        private int PT_ID;
        private String PTM_Description;
        private String PTM_Remark;
        private String PTM_KeyWord;
        private String PTM_Image;
        private String PTM_FloatImage;
        
        
        /**
         * 添加模板信息需要的参数
         * 
         */
        public ApiAddTemplateInfoParams(String PTM_Title, int PTM_Order, int PT_ID, String PTM_Description, String PTM_Remark, 
        		String PTM_KeyWord, String PTM_Image, String PTM_FloatImage) {
            super();
            this.PTM_Title = PTM_Title;
            this.PTM_Order = PTM_Order;
            this.PT_ID = PT_ID;
            this.PTM_Description = PTM_Description;
            this.PTM_Remark = PTM_Remark;
            this.PTM_KeyWord = PTM_KeyWord;
            this.PTM_Image = PTM_Image;
            this.PTM_FloatImage = PTM_FloatImage;
            
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PTM_Title=" + PTM_Title + "&PTM_Order="+PTM_Order + "&PT_ID="+PT_ID + "&PTM_Description="+PTM_Description
            		+ "&PTM_Remark="+PTM_Remark+ "&PTM_KeyWord="+PTM_KeyWord+ "&PTM_Image="+PTM_Image+ "&PTM_FloatImage="+PTM_FloatImage ;
        }
    }

    /**
     * 添加模板信息返回结果
     */
    public static class ApiAddTemplateInfoResponse extends BaseResponse {
        public AddTemplate addTemplate; 
    }

    public ApiAddTemplateInfoResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiAddTemplateInfoResponse response = new ApiAddTemplateInfoResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.addTemplate = gson.fromJson(baseResponse.getContent(), AddTemplate.class);
            Log.i(TAG, "response.addTmplate = " + response.addTemplate);
        }
        return response;
    }

}
