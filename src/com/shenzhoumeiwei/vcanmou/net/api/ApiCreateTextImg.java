package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.CreateTextImg;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 上传文字生成图片
 */
public class ApiCreateTextImg extends HttpApiBase {

    private static final String TAG = "ApiCreateTextImg";

    /**
     * 上传文字生成图片
     * 
     * @param context
     *            上下文
     * @param params
     *            生成文字图片需要的参数
     */
    public ApiCreateTextImg(Context context, ApiCreateTextImgParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_CREATE_TEXT_IMG,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 生成文字图片需要的参数
     */
    public static class ApiCreateTextImgParams extends BaseRequestParams {

        private String MC_Id; 
        private String Modules;
        private String context;
        private String fontFamily;
        private String fontSize;
        
        
        /**
         * 上传图片需要的参数
         * 
         */
        public ApiCreateTextImgParams(String mcId, String modules,String context,String fontFamily,String fontSize) {
            super();
            this.MC_Id = mcId;
            this.Modules = modules;
            this.context = context;
            this.fontFamily = fontFamily;
            this.fontSize = fontSize;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?MC_Id=" + MC_Id + "&Modules="
                    + Modules+"&text="+context+"&fontFamily="+fontFamily+"&fontSize="+fontSize;
        }
    }

    /**
     * 上传图片返回结果
     */
    public static class ApiCreateTextImgResponse extends BaseResponse {
        public CreateTextImg createTextImg; // 授权信息
    }

    public ApiCreateTextImgResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiCreateTextImgResponse response = new ApiCreateTextImgResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            // 授权成功，返回授权信息
            Gson gson = new Gson();
            response.createTextImg = gson.fromJson(baseResponse.getContent(), CreateTextImg.class);
            Log.i(TAG, "response.restaurant = " + response.createTextImg);
        }
        return response;
    }

}
