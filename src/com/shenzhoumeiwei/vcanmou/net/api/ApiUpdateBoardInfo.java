package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.AddBoardInfo;
import com.shenzhoumeiwei.vcanmou.model.UpdateBoardInfo;
import com.shenzhoumeiwei.vcanmou.model.UpdateBoardResponse;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 修改版块信息
 */
public class ApiUpdateBoardInfo extends HttpApiBase {

    private static final String TAG = "ApiUpdateBoardInfo";

    /**
     * 修改版块信息
     * 
     * @param context
     *            上下文
     * @param params
     *            修改版块需要的参数
     */
    public ApiUpdateBoardInfo(Context context, ApiUpdateBoardInfoParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_UPDATE_BOARD,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 修改版块需要的参数
     */
    public static class ApiUpdateBoardInfoParams extends BaseRequestParams {

        private UpdateBoardInfo updateBoardInfo; 
        
        /**
         * 修改版块需要的参数
         * 
         */
        public ApiUpdateBoardInfoParams(UpdateBoardInfo updateBoardInfo) {
            super();
            this.updateBoardInfo = updateBoardInfo;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PPE_ID=" + updateBoardInfo.PPE_ID +"&PP_ID="+updateBoardInfo.PP_ID+ "&PPE_Type=" + updateBoardInfo.PPE_Type+"&PPE_Height="+updateBoardInfo.PPE_Height
            		+"&PPE_Width="+updateBoardInfo.PPE_Width+"&PPE_Index="+updateBoardInfo.PPE_Index+"&PPE_Image="+updateBoardInfo.PPE_Image
            		+"&PPE_ImageBorder="+updateBoardInfo.PPE_ImageBorder+"&PPE_Href="+updateBoardInfo.PPE_Href+"&PPE_FontSize="+updateBoardInfo.PPE_FontSize
            		+"&PPE_FontFamily="+updateBoardInfo.PPE_FontFamily+"&PPE_FontText="+updateBoardInfo.PPE_FontText
            		+"&PPE_TextAlign="+updateBoardInfo.PPE_TextAlign+"&PPE_FontColor="+updateBoardInfo.PPE_FontColor
            		+"&PPE_FontBackColor="+updateBoardInfo.PPE_FontBackColor+"&PPE_InWay="+updateBoardInfo.PPE_InWay+"&PPE_InDirection="+updateBoardInfo.PPE_InDirection
            		+"&PPE_Margin="+updateBoardInfo.PPE_Margin+"&PPE_PointCenter="+updateBoardInfo.PPE_PointCenter+"&PPE_PointLeftUp="+updateBoardInfo.PPE_PointLeftUp
            		+"&PPE_PointLeftDown="+updateBoardInfo.PPE_PointLeftDown+"&PPE_PointRightUp="+updateBoardInfo.PPE_PointRightUp+"&PPE_PointRightDown="+updateBoardInfo.PPE_PointRightDown;
        }
    }

    /**
     * 修改版块返回结果
     */
    public static class ApiUpdateBoardInfoResponse extends BaseResponse {
        public UpdateBoardResponse updateBoardResponse; // 版块信息
    }

    public ApiUpdateBoardInfoResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiUpdateBoardInfoResponse response = new ApiUpdateBoardInfoResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            // 获得成功，返回版块信息
            Gson gson = new Gson();
            response.updateBoardResponse = gson.fromJson(baseResponse.getContent(), UpdateBoardResponse.class);
            Log.i(TAG, "response.updateBoardResponse = " + response.updateBoardResponse);
        }
        return response;
    }

}
