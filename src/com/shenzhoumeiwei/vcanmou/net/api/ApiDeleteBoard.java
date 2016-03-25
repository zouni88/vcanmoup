package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.DeleteBoard;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 删除版块
 */
public class ApiDeleteBoard extends HttpApiBase {

    private static final String TAG = "ApiDeleteBoard";

    /**
     * 删除版块
     * 
     * @param context
     *            上下文
     * @param params
     *            删除版块需要的参数
     */
    public ApiDeleteBoard(Context context, ApiDeleteBoardParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_DELETE_BOARD,
                HttpRequest.REQUEST_METHOD_HTTP_GET, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 删除版块需要的参数
     */
    public static class ApiDeleteBoardParams extends BaseRequestParams {

        private String PPE_Id;
        private String PP_Id;
        
        
        /**
         * 删除版块需要的参数
         * 
         */
        public ApiDeleteBoardParams(String PPE_Id, String PP_Id) {
            super();
            this.PPE_Id = PPE_Id;
            this.PP_Id = PP_Id;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?PPE_Id=" + PPE_Id + "&PP_Id="+PP_Id;
        }
    }

    /**
     * 删除版块返回结果
     */
    public static class ApiDeleteBoardResponse extends BaseResponse {
        public DeleteBoard deleteBoard; 
    }

    public ApiDeleteBoardResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiDeleteBoardResponse response = new ApiDeleteBoardResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.deleteBoard = gson.fromJson(baseResponse.getContent(), DeleteBoard.class);
            Log.i(TAG, "response.deleteBoard = " + response.deleteBoard);
        }
        return response;
    }

}
