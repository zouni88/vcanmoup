package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.UpLoadImage;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 上传图片
 */
public class ApiUpLoadImg extends HttpApiBase {

    private static final String TAG = "ApiUpLoadImg";

    /**
     * 上传图片
     * 
     * @param context
     *            上下文
     * @param params
     *            上传单个图片需要的参数
     */
    public ApiUpLoadImg(Context context, ApiUpLoadImgParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_UP_IMAGE_URL,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 上传图片需要的参数
     */
    public static class ApiUpLoadImgParams extends BaseRequestParams {

        private String MC_Id; 
        private String Modules;
        private String imgBase64;

        /**
         * 上传图片需要的参数
         * 
         */
        public ApiUpLoadImgParams(String mcId, String modules,String imgBase) {
            super();
            this.MC_Id = mcId;
            this.Modules = modules;
            this.imgBase64 = imgBase;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?MC_Id=" + MC_Id + "&Modules="
                    + Modules+"&imgBase64="+imgBase64;
        }
    }

    /**
     * 上传图片返回结果
     */
    public static class ApiUpLoadImgResponse extends BaseResponse {
        public UpLoadImage upLoadImage; // 授权信息
    }

    public ApiUpLoadImgResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiUpLoadImgResponse response = new ApiUpLoadImgResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            // 授权成功，返回授权信息
            Gson gson = new Gson();
            UpLoadImage upLoadImg = new UpLoadImage();
            upLoadImg.imageUrl = baseResponse.getContent();
            response.upLoadImage = upLoadImg;
            Log.i(TAG, "response.restaurant = " + response.upLoadImage);
            Log.i(TAG, "response.restaurant = " + response.upLoadImage);
        }
        return response;
    }

}
