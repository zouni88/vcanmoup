package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.AddPoster;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 添加海报
 */
public class ApiAddPoster extends HttpApiBase {

    private static final String TAG = "ApiAddPoster";

    /**
     * 添加海报
     * 
     * @param context
     *            上下文
     * @param params
     *            添加海报需要的参数
     */
    public ApiAddPoster(Context context, ApiAddPosterParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_ADD_POSTER,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 添加海报需要的参数
     */
    public static class ApiAddPosterParams extends BaseRequestParams {

        private String MC_ID;
        private String U_ID;
        private String P_Title;
        private String PTM_ID;
        private String P_Description;
        private String P_Remark;
        private String P_KeyWord;
        private String P_Image;
        private String P_FloatImage;
        
        
        /**
         * 添加海报需要的参数
         * 
         */
        public ApiAddPosterParams(String MC_ID, String U_ID, String P_Title, String PTM_ID, String P_Description,
        		String P_Remark, String P_KeyWord, String P_Image, String P_FloatImage) {
            super();
            this.MC_ID = MC_ID;
            this.U_ID = U_ID;
            this.P_Title = P_Title;
            this.PTM_ID = PTM_ID;
            this.P_Description = P_Description;
            this.P_Remark = P_Remark;
            this.P_KeyWord = P_KeyWord;
            this.P_Image = P_Image;
            this.P_FloatImage = P_FloatImage;
        }

        /**
         * 根据成员变量生成参数
         */
        @Override
        public String generateRequestParams() {
            return "?MC_ID=" + MC_ID + "&U_ID="+U_ID + "&P_Title="+P_Title + "&PTM_ID="+PTM_ID 
            		+ "&P_Description="+P_Description + "&P_Remark="+P_Remark + "&P_KeyWord="+P_KeyWord + "&P_Image="+P_Image + "&P_FloatImage="+P_FloatImage;
        }
    }

    /**
     * 添加海报返回结果
     */
    public static class ApiAddPosterResponse extends BaseResponse {
        public AddPoster addPoster; 
    }

    public ApiAddPosterResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiAddPosterResponse response = new ApiAddPosterResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.addPoster = gson.fromJson(baseResponse.getContent(), AddPoster.class);
            Log.i(TAG, "response.posterPage = " + response.addPoster);
        }
        return response;
    }

}
