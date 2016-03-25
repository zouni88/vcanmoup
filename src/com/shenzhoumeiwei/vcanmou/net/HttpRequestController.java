package com.shenzhoumeiwei.vcanmou.net;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.shenzhoumeiwei.vcanmou.model.AddBoardInfo;
import com.shenzhoumeiwei.vcanmou.model.UpdateBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddBoardInfo.ApiAddBoardInfoParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddBoardInfo.ApiAddBoardInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPoster;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPoster.ApiAddPosterParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPoster.ApiAddPosterResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPosterPage.ApiAddPosterPageParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPosterPage.ApiAddPosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddTemplateInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddTemplateInfo.ApiAddTemplateInfoParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddTemplateInfo.ApiAddTemplateInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg.ApiCreateTextImgParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg.ApiCreateTextImgResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeleteBoard;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeleteBoard.ApiDeleteBoardParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeleteBoard.ApiDeleteBoardResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeletePosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeletePosterPage.ApiDeletePosterPageParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeletePosterPage.ApiDeletePosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetBoardInfo.ApiGetBoardInfoParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetBoardInfo.ApiGetBoardInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterInfo.ApiGetPosterInfoParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterInfo.ApiGetPosterInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterType;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterType.ApiGetPosterTypeParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterType.ApiGetPosterTypeResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetTemplateInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetTemplateInfo.ApiGetTemplateInfoParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetTemplateInfo.ApiGetTemplateInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPage.ApiPosterPageParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPage.ApiPosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPageSort;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPageSort.ApiPosterPageSortParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPageSort.ApiPosterPageSortResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPublishPoster;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPublishPoster.ApiPublishPosterParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPublishPoster.ApiPublishPosterResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpLoadImg;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpLoadImg.ApiUpLoadImgParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpLoadImg.ApiUpLoadImgResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateBoardInfo.ApiUpdateBoardInfoParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateBoardInfo.ApiUpdateBoardInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePoster;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePoster.ApiUpdatePosterParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePoster.ApiUpdatePosterResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePosterPage.ApiUpdatePosterPageParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePosterPage.ApiUpdatePosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateTemplateInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateTemplateInfo.ApiUpdateTemplateInfoParams;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateTemplateInfo.ApiUpdateTemplateInfoResponse;

/**
 * Http请求的一个总接口，通过该类调用各Api请求服务器。由于网络请求比较耗时，所以需要在线程中作处理。
 */
public class HttpRequestController {

    private static final String TAG = "HttpRequestController";

    private static Handler mHandler = null;
    // 创建线程池
    private static final ThreadPoolExecutor mThreadPoolExecutor = (ThreadPoolExecutor) Executors
            .newCachedThreadPool();

    private HttpRequestController() {
    }

    private static void checkHandler() {
        try {
            if (mHandler == null) {
                mHandler = new Handler();
            }
        } catch (Exception e) {
            mHandler = null;
        }
    }

    /**
     * 上传图片
     * 
     * @param context
     *            上下文
     * @param mcId
     *            商户ID
     * @param modules
     *            模块代码
     * @param imgBase
     *            图片
     * @param listener
     *            请求完成后的回调
     */
    public static void upLoadImg(final Context context, final String mcId,final String modules,final String imgBase,
            final HttpResponseListener<ApiUpLoadImgResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ApiUpLoadImgParams params = new ApiUpLoadImgParams(mcId,modules,imgBase);

                ApiUpLoadImg apiAddTakeoutOrder = new ApiUpLoadImg(context, params);
                final ApiUpLoadImgResponse response = apiAddTakeoutOrder.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 创建文本图片
     * @param context  上下文
     * @param mcId  商户ID
     * @param modules  模块代码
     * @param text   文本内容
     * @param fontFamily   字体
     * @param fontSize  字体大小
     * @param listener  
     */
    public static void createTextImg(final Context context, final String mcId,final String modules,final String text,
    		final String fontFamily,final String fontSize,
            final HttpResponseListener<ApiCreateTextImgResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ApiCreateTextImgParams params = new ApiCreateTextImgParams(mcId,modules,text,fontFamily,fontSize);

                ApiCreateTextImg apiCreateTextImg = new ApiCreateTextImg(context, params);
                final ApiCreateTextImgResponse response = apiCreateTextImg.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    public static void getBoardInfo(final Context context, final String PPE_ID,final String PP_ID,
            final HttpResponseListener<ApiGetBoardInfoResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiGetBoardInfoParams params = new ApiGetBoardInfoParams(PPE_ID,PP_ID);

            	ApiGetBoardInfo apiGetBoardInfo = new ApiGetBoardInfo(context, params);
                final ApiGetBoardInfoResponse response = apiGetBoardInfo.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 添加版块
     * @param context
     * @param addBoardInfo
     * @param listener
     */
    public static void addBoardInfo(final Context context, final AddBoardInfo addBoardInfo,
            final HttpResponseListener<ApiAddBoardInfoResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiAddBoardInfoParams params = new ApiAddBoardInfoParams(addBoardInfo);

            	ApiAddBoardInfo addBoardInfo = new ApiAddBoardInfo(context, params);
                final ApiAddBoardInfoResponse response = addBoardInfo.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /**
     * 修改版块信息
     * @param context
     * @param updateBoardInfo
     * @param listener
     */
    public static void updateBoardInfo(final Context context, final UpdateBoardInfo updateBoardInfo,
            final HttpResponseListener<ApiUpdateBoardInfoResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiUpdateBoardInfoParams params = new ApiUpdateBoardInfoParams(updateBoardInfo);

            	ApiUpdateBoardInfo updateBoardInfo = new ApiUpdateBoardInfo(context, params);
                final ApiUpdateBoardInfoResponse response = updateBoardInfo.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }    
    
    /***
     * 删除版块
     * @param context
     * @param PPE_ID
     * @param PP_ID
     * @param listener
     */
    public static void deleteBoardInfo(final Context context, final String PPE_ID,final String PP_ID,
            final HttpResponseListener<ApiDeleteBoardResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiDeleteBoardParams params = new ApiDeleteBoardParams(PPE_ID,PP_ID);

            	ApiDeleteBoard deleteBoard = new ApiDeleteBoard(context, params);
                final ApiDeleteBoardResponse response = deleteBoard.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 获取海报页面
     * @param context
     * @param PP_Id  海报页面ID
     * @param P_Id	海报ID
     * @param listener
     */
    public static void getPosterPage(final Context context, final String PP_Id,final String P_Id,
            final HttpResponseListener<ApiPosterPageResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiPosterPageParams params = new ApiPosterPageParams(PP_Id,P_Id);

            	ApiPosterPage posterPage = new ApiPosterPage(context, params);
                final ApiPosterPageResponse response = posterPage.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 海报排序
     * @param context
     * @param P_Id  海报页面ID
     * @param startIndex  开始位置
     * @param endIndex  结束位置
     * @param listener
     */
    public static void posterPageSort(final Context context, final String P_Id,final String startIndex,final String endIndex,
            final HttpResponseListener<ApiPosterPageSortResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiPosterPageSortParams params = new ApiPosterPageSortParams(P_Id,startIndex,endIndex);

            	ApiPosterPageSort posterPageSort = new ApiPosterPageSort(context, params);
                final ApiPosterPageSortResponse response = posterPageSort.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 添加海报页面
     * @param context
     * @param PP_BackImage   页面背景
     * @param PP_Type	类型（0 海报、1活动）
     * @param PP_Order	排序字段
     * @param P_ID	海报ID
     * @param PTM_ID	模板ID
     * @param listener
     */
    public static void addPosterPage(final Context context, final String PP_BackImage , final String PP_Type, final String PP_Order,final String P_ID,final String PTM_ID,
            final HttpResponseListener<ApiAddPosterPageResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiAddPosterPageParams params = new ApiAddPosterPageParams(PP_BackImage,PP_Type,PP_Order,P_ID,PTM_ID);

            	ApiAddPosterPage addPosterPage = new ApiAddPosterPage(context, params);
                final ApiAddPosterPageResponse response = addPosterPage.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 更新海报页面
     * @param context
     * @param PP_Id	海报页面ID
     * @param PP_BackImage	页面背景
     * @param PP_Type	类型（0 海报、1活动）
     * @param PP_Order	排序字段
     * @param P_ID	海报ID
     * @param PTM_ID	模板ID
     * @param listener
     */
    public static void updatePosterPage(final Context context,final String PP_Id, final String PP_BackImage , final String PP_Type, final String PP_Order,final String P_ID,final String PTM_ID,
            final HttpResponseListener<ApiUpdatePosterPageResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiUpdatePosterPageParams params = new ApiUpdatePosterPageParams(PP_Id,PP_BackImage,PP_Type,PP_Order,P_ID,PTM_ID);

            	ApiUpdatePosterPage updatePosterPage = new ApiUpdatePosterPage(context, params);
                final ApiUpdatePosterPageResponse response = updatePosterPage.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 删除海报页面
     * @param context
     * @param PP_Id
     * @param listener
     */
    public static void deletePosterPage(final Context context,final String PP_Id,
            final HttpResponseListener<ApiDeletePosterPageResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiDeletePosterPageParams params = new ApiDeletePosterPageParams(PP_Id);

            	ApiDeletePosterPage deletePosterPage = new ApiDeletePosterPage(context, params);
                final ApiDeletePosterPageResponse response = deletePosterPage.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 获取海报信息
     * @param context
     * @param MC_ID 商户ID
     * @param P_Id	海报ID
     * @param PTM_ID	海报模板ID
     * @param PageIndex	分页索引
     * @param PageSize	分页大小
     * @param listener
     */
    public static void getPoster(final Context context,final String MC_ID, final String P_Id, final String PTM_ID, final String PageIndex, final String PageSize,
            final HttpResponseListener<ApiGetPosterInfoResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiGetPosterInfoParams params = new ApiGetPosterInfoParams(MC_ID,P_Id,PTM_ID,PageIndex,PageSize);

            	ApiGetPosterInfo getPosterInfo = new ApiGetPosterInfo(context, params);
                final ApiGetPosterInfoResponse response = getPosterInfo.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 添加海报
     * @param context
     * @param MC_ID 商户ID
     * @param U_ID	用户ID
     * @param P_Title	海报标题
     * @param PTM_ID	模板ID
     * @param P_Description	海报描述
     * @param P_Remark	海报备注
     * @param P_KeyWord	页面关键字	
     * @param P_Image	页面图片
     * @param P_FloatImage	浮动图片
     * @param listener
     */
    public static void addPoster(final Context context,final String MC_ID, final String U_ID, final String P_Title, final String PTM_ID, 
    		final String P_Description, final String P_Remark, final String P_KeyWord, final String P_Image, final String P_FloatImage,
            final HttpResponseListener<ApiAddPosterResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiAddPosterParams params = new ApiAddPosterParams(MC_ID,U_ID,P_Title,PTM_ID,P_Description,P_Remark,P_KeyWord,P_Image,P_FloatImage);

            	ApiAddPoster addPoster = new ApiAddPoster(context, params);
                final ApiAddPosterResponse response = addPoster.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 添加海报
     * @param context
     * @param P_ID 海报ID
     * @param MC_ID 商户ID
     * @param U_ID	用户ID
     * @param P_Title	海报标题
     * @param PTM_ID	模板ID
     * @param P_Description	海报描述
     * @param P_Remark	海报备注
     * @param P_KeyWord	页面关键字	
     * @param P_Image	页面图片
     * @param P_FloatImage	浮动图片
     * @param listener
     */
    public static void updatePoster(final Context context,final String P_ID,final String MC_ID, final String U_ID, final String P_Title, final String PTM_ID, 
    		final String P_Description, final String P_Remark, final String P_KeyWord, final String P_Image, final String P_FloatImage,
            final HttpResponseListener<ApiUpdatePosterResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiUpdatePosterParams params = new ApiUpdatePosterParams(P_ID,MC_ID,U_ID,P_Title,PTM_ID,P_Description,P_Remark,P_KeyWord,P_Image,P_FloatImage);

            	ApiUpdatePoster updatePoster = new ApiUpdatePoster(context, params);
                final ApiUpdatePosterResponse response = updatePoster.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 发布海报
     * @param context
     * @param P_ID	海报ID
     * @param MC_ID	商户ID
     * @param listener
     */
    public static void publishPoster(final Context context,final String P_ID,final String MC_ID, 
            final HttpResponseListener<ApiPublishPosterResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiPublishPosterParams params = new ApiPublishPosterParams(P_ID,MC_ID);

            	ApiPublishPoster PublishPoster = new ApiPublishPoster(context, params);
                final ApiPublishPosterResponse response = PublishPoster.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /**
     * 获取模板信息
     * @param context
     * @param PTM_Id	模板ID
     * @param PT_Ids	模板类型ID （多个类型用“，”隔开,如："1,2,3"）
     * @param PageIndex	分页索引
     * @param PageSize	分页大小
     * @param listener
     */
    public static void getTemplateInfo(final Context context,final String PTM_Id,final String PT_Ids, final String PageIndex, final String PageSize, 
            final HttpResponseListener<ApiGetTemplateInfoResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiGetTemplateInfoParams params = new ApiGetTemplateInfoParams(PTM_Id,PT_Ids,PageIndex,PageSize);

            	ApiGetTemplateInfo templateInfo = new ApiGetTemplateInfo(context, params);
                final ApiGetTemplateInfoResponse response = templateInfo.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 添加模板信息
     * @param context
     * @param PTM_Title	标题
     * @param PTM_Order	排序字段 （多个类型用“，”隔开,如："1,2,3"）
     * @param PT_ID	模板类型
     * @param PTM_Description	描述
     * @param PTM_Remark	v备注
     * @param PTM_KeyWord	页面关键字
     * @param PTM_Image	图片
     * @param PTM_FloatImage	浮动图片
     * @param listener
     */
    public static void addTemplateInfo(final Context context,final String PTM_Title, final int PTM_Order, final int PT_ID, final String PTM_Description, final String PTM_Remark, 
    		final String PTM_KeyWord, final String PTM_Image, final String PTM_FloatImage, 
            final HttpResponseListener<ApiAddTemplateInfoResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiAddTemplateInfoParams params = new ApiAddTemplateInfoParams(PTM_Title,PTM_Order,PT_ID,PTM_Description,PTM_Remark,PTM_KeyWord,PTM_Image,PTM_FloatImage);

            	ApiAddTemplateInfo addTemplate = new ApiAddTemplateInfo(context, params);
                final ApiAddTemplateInfoResponse response = addTemplate.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 修改模板信息
     * @param context
     * @param PTM_Id	模板ID
     * @param PTM_Title	标题
     * @param PTM_Order	排序字段 （多个类型用“，”隔开,如："1,2,3"）
     * @param PTM_Description	描述
     * @param PTM_Remark	备注
     * @param PTM_KeyWord	页面关键字
     * @param PTM_Image	图片
     * @param PTM_FloatImage	浮动图片
     * @param listener
     */
    public static void updateTemplateInfo(final Context context,final int PTM_Id, final String PTM_Title, final int PTM_Order, final String PTM_Description
    		,final String PTM_Remark,final String PTM_KeyWord,final String PTM_Image,final String PTM_FloatImage, 
            final HttpResponseListener<ApiUpdateTemplateInfoResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiUpdateTemplateInfoParams params = new ApiUpdateTemplateInfoParams(PTM_Id,PTM_Title,PTM_Order,PTM_Description,PTM_Remark,PTM_KeyWord,PTM_Image,PTM_FloatImage);

            	ApiUpdateTemplateInfo addTemplate = new ApiUpdateTemplateInfo(context, params);
                final ApiUpdateTemplateInfoResponse response = addTemplate.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
    
    /***
     * 获取海报类型
     * @param context
     * @param PT_ID	ID	
     * @param PT_ParentID	父ID
     * @param listener	
     */
    public static void getPosterType(final Context context,final int PT_ID, final int PT_ParentID, 
            final HttpResponseListener<ApiGetPosterTypeResponse> listener) {
        checkHandler();
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
            	ApiGetPosterTypeParams params = new ApiGetPosterTypeParams(PT_ID,PT_ParentID);

            	ApiGetPosterType addTemplate = new ApiGetPosterType(context, params);
                final ApiGetPosterTypeResponse response = addTemplate.getHttpResponse();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(response);
                        Log.i(TAG, "" + response.getRetCode());
                    }
                });
            }
        });
    }
}
