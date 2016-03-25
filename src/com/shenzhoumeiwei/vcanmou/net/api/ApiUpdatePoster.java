package com.shenzhoumeiwei.vcanmou.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.shenzhoumeiwei.vcanmou.model.UpdatePoster;
import com.shenzhoumeiwei.vcanmou.net.BaseRequestParams;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequest;
import com.shenzhoumeiwei.vcanmou.utils.Constant;

/**
 * 修改海报
 */
public class ApiUpdatePoster extends HttpApiBase {

    private static final String TAG = "ApiUpdatePoster";

    /**
     * 修改海报
     * 
     * @param context
     *            上下文
     * @param params
     *            添加海报需要的参数
     */
    public ApiUpdatePoster(Context context, ApiUpdatePosterParams params) {
        super(context);
        mHttpRequest = new HttpRequest(Constant.HTTP_UPDATE_POSTER,
                HttpRequest.REQUEST_METHOD_HTTP_POST, HttpRequest.RESPONSE_TYPE_JSON, params);
    }

    /**
     * 修改海报需要的参数
     */
    public static class ApiUpdatePosterParams extends BaseRequestParams {

    	private String P_ID;
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
         * 修改海报需要的参数
         * 
         */
        public ApiUpdatePosterParams(String P_ID,String MC_ID, String U_ID, String P_Title, String PTM_ID, String P_Description,
        		String P_Remark, String P_KeyWord, String P_Image, String P_FloatImage) {
            super();
            this.P_ID = P_ID;
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
            return "?P_ID=" + P_ID +"&MC_ID=" + MC_ID + "&U_ID="+U_ID + "&P_Title="+P_Title + "&PTM_ID="+PTM_ID 
            		+ "&P_Description="+P_Description + "&P_Remark="+P_Remark + "&P_KeyWord="+P_KeyWord + "&P_Image="+P_Image + "&P_FloatImage="+P_FloatImage;
        }
    }

    /**
     * 修改海报返回结果
     */
    public static class ApiUpdatePosterResponse extends BaseResponse {
        public UpdatePoster updatePoster; 
    }

    public ApiUpdatePosterResponse getHttpResponse() {
        BaseResponse baseResponse = getHttpContent();

        ApiUpdatePosterResponse response = new ApiUpdatePosterResponse();
        response.setRetCode(baseResponse.getRetCode());
        response.setRetInfo(baseResponse.getRetInfo());

        if (baseResponse.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
            Gson gson = new Gson();
            response.updatePoster = gson.fromJson(baseResponse.getContent(), UpdatePoster.class);
            Log.i(TAG, "response.posterPage = " + response.updatePoster);
        }
        return response;
    }

}
