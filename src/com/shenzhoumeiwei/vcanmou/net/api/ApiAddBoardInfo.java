package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.AddBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 添加版块信息
 */
public class ApiAddBoardInfo extends HttpApiBase {

    private static final String TAG = "ApiAddBoardInfo";

    /**
     * 添加版块信息
     * 
     * @param context
     *            上下文
     * @param params
     *            添加版块需要的参数
     */
    public ApiAddBoardInfo(Context context, ApiAddBoardInfoParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_ADD_BOARD_INFO,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 添加版块需要的参数
     */
    public static class ApiAddBoardInfoParams extends BaseRequestParams {

        private AddBoardInfo addBoardInfo; 
        
        /**
         * 添加版块需要的参数
         * 
         */
        public ApiAddBoardInfoParams(AddBoardInfo addBoardInfo) {
            super();
            this.addBoardInfo = addBoardInfo;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PP_ID=" + addBoardInfo.PP_ID + "&PPE_Type=" + addBoardInfo.PPE_Type+"&PPE_Height="+addBoardInfo.PPE_Height
            		+"&PPE_Width="+addBoardInfo.PPE_Width+"&PPE_Index="+addBoardInfo.PPE_Index+"&PPE_Image="+addBoardInfo.PPE_Image
            		+"&PPE_ImageBorder="+addBoardInfo.PPE_ImageBorder+"&PPE_Href="+addBoardInfo.PPE_Href+"&PPE_FontSize="+addBoardInfo.PPE_FontSize
            		+"&PPE_FontFamily="+addBoardInfo.PPE_FontFamily+"&PPE_FontText="+addBoardInfo.PPE_FontText+"&PPE_FontColor="+addBoardInfo.PPE_FontColor
            		+"&PPE_FontBackColor="+addBoardInfo.PPE_FontBackColor+"&PPE_InWay="+addBoardInfo.PPE_InWay+"&PPE_InDirection="+addBoardInfo.PPE_InDirection
            		+"&PPE_Margin="+addBoardInfo.PPE_Margin+"&PPE_PointCenter="+addBoardInfo.PPE_PointCenter+"&PPE_PointLeftUp="+addBoardInfo.PPE_PointLeftUp
            		+"&PPE_PointLeftDown="+addBoardInfo.PPE_PointLeftDown+"&PPE_PointRightUp="+addBoardInfo.PPE_PointRightUp+"&PPE_PointRightDown="+addBoardInfo.PPE_PointRightDown;
        }
    }

    /**
     * 添加版块返回结果
     */
    public static class ApiAddBoardInfoResponse extends BaseResponse {
        public AddBoardInfo addBoardInfo; // 版块信息
    }

    public ApiAddBoardInfoResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiAddBoardInfoResponse response = new ApiAddBoardInfoResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            // 获得成功，返回版块信息
            Gson gson = new Gson();
            response.addBoardInfo = gson.fromJson(baseResponse.getContent(), AddBoardInfo.class);
            Log.i(TAG, "response.addBoardInfo = " + response.addBoardInfo);
        }
        return response;
    }

}
