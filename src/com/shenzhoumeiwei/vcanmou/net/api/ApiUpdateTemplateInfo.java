package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.UpdateTemplate;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 修改模板信息
 */
public class ApiUpdateTemplateInfo extends HttpApiBase {

    private static final String TAG = "ApiUpdateTemplateInfo";

    /**
     * 修改模板信息
     * 
     * @param context
     *            上下文
     * @param params
     *            修改模板信息需要的参数
     */
    public ApiUpdateTemplateInfo(Context context, ApiUpdateTemplateInfoParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_UPDATE_TEMPLATE_INFO,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 修改模板信息需要的参数
     */
    public static class ApiUpdateTemplateInfoParams extends BaseRequestParams {

        private int PTM_Id;
        private String PTM_Title;
        private int PTM_Order;
        private String PTM_Description;
        private String PTM_Remark;
        private String PTM_KeyWord;
        private String PTM_Image;
        private String PTM_FloatImage;
        
        
        /**
         * 修改模板信息需要的参数
         * 
         */
        public ApiUpdateTemplateInfoParams(int PTM_Id, String PTM_Title, int PTM_Order, String PTM_Description
        		,String PTM_Remark,String PTM_KeyWord,String PTM_Image,String PTM_FloatImage) {
            super();
            this.PTM_Id = PTM_Id;
            this.PTM_Title = PTM_Title;
            this.PTM_Order = PTM_Order;
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
            return "?PTM_Id=" + PTM_Id + "&PTM_Title="+PTM_Title + "&PTM_Order="+PTM_Order + "&PTM_Description="+PTM_Description 
            		+ "&PTM_Remark="+PTM_Remark + "&PTM_KeyWord="+PTM_KeyWord + "&PTM_Image="+PTM_Image + "&PTM_FloatImage="+PTM_FloatImage ;
        }
    }

    /**
     * 修改模板信息返回结果
     */
    public static class ApiUpdateTemplateInfoResponse extends BaseResponse {
        public UpdateTemplate updateTemplate; 
    }

    public ApiUpdateTemplateInfoResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiUpdateTemplateInfoResponse response = new ApiUpdateTemplateInfoResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.updateTemplate = gson.fromJson(baseResponse.getContent(), UpdateTemplate.class);
            Log.i(TAG, "response.updateTemplate = " + response.updateTemplate);
        }
        return response;
    }

}
