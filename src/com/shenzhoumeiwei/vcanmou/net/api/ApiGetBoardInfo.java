package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.BoardInfo;
import com.shenzhoumeiwei.vcanmou.model.CreateTextImg;
import com.shenzhoumeiwei.vcanmou.model.UpLoadImage;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 获取版块信息
 */
public class ApiGetBoardInfo extends HttpApiBase {

    private static final String TAG = "ApiGetBoardInfo";

    /**
     * 获取版块信息
     * 
     * @param context
     *            上下文
     * @param params
     *            生成文字图片需要的参数
     */
    public ApiGetBoardInfo(Context context, ApiGetBoardInfoParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_GET_BOARD_INFO,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 获取板块信息需要的参数
     */
    public static class ApiGetBoardInfoParams extends BaseRequestParams {

        private String PPE_ID; 
        private String PP_ID;
        
        /**
         * 板块信息需要的参数
         * 
         */
        public ApiGetBoardInfoParams(String PPE_ID, String PP_ID) {
            super();
            this.PPE_ID = PPE_ID;
            this.PP_ID = PP_ID;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PPE_ID=" + PPE_ID + "&PP_ID=" + PP_ID;
        }
    }

    /**
     * 获取版块信息返回结果
     */
    public static class ApiGetBoardInfoResponse extends BaseResponse {
        public BoardInfo boardInfo; // 版块信息
    }

    public ApiGetBoardInfoResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiGetBoardInfoResponse response = new ApiGetBoardInfoResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            // 获得成功，返回版块信息
            Gson gson = new Gson();
            response.boardInfo = gson.fromJson(baseResponse.getContent(), BoardInfo.class);
            Log.i(TAG, "response.restaurant = " + response.boardInfo);
        }
        return response;
    }

}
