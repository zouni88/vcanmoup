package com.shenzhoumeiwei.vcanmou.activity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.shenzhoumeiwei.vcanmou.R;
import com.shenzhoumeiwei.vcanmou.model.AddBoardInfo;
import com.shenzhoumeiwei.vcanmou.model.UpdateBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.BaseResponse;
import com.shenzhoumeiwei.vcanmou.net.HttpRequestController;
import com.shenzhoumeiwei.vcanmou.net.HttpResponseListener;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddBoardInfo.ApiAddBoardInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPoster;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPoster.ApiAddPosterResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddPosterPage.ApiAddPosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddTemplateInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiAddTemplateInfo.ApiAddTemplateInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg;
import com.shenzhoumeiwei.vcanmou.net.api.ApiCreateTextImg.ApiCreateTextImgResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeleteBoard;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeleteBoard.ApiDeleteBoardResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeletePosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiDeletePosterPage.ApiDeletePosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetBoardInfo.ApiGetBoardInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterInfo.ApiGetPosterInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterType;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetPosterType.ApiGetPosterTypeResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetTemplateInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiGetTemplateInfo.ApiGetTemplateInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPage.ApiPosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPageSort;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPosterPageSort.ApiPosterPageSortResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPublishPoster;
import com.shenzhoumeiwei.vcanmou.net.api.ApiPublishPoster.ApiPublishPosterResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpLoadImg;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpLoadImg.ApiUpLoadImgResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateBoardInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateBoardInfo.ApiUpdateBoardInfoResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePoster;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePoster.ApiUpdatePosterResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePosterPage;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdatePosterPage.ApiUpdatePosterPageResponse;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateTemplateInfo;
import com.shenzhoumeiwei.vcanmou.net.api.ApiUpdateTemplateInfo.ApiUpdateTemplateInfoResponse;
import com.shenzhoumeiwei.vcanmou.utils.Base64;
import com.shenzhoumeiwei.vcanmou.utils.Utils;

public class MainActivity extends Activity {
	private MainActivity context = MainActivity.this;
	
	private final String TAG = "MainActivity";
	private Intent intent;
	InputStream is ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			is = getResources().getAssets().open("sophie.png");
		} catch (IOException e) {
		}
	}

	public void inPosterPage(View view) {
		intent = new Intent(this, PosterHomePageActivity.class);
		startActivity(intent);
	}

	public void editPage(View view) {
		intent = new Intent(this, EditPosterActivity.class);
		startActivity(intent);
	}

	/***
	 * 图片上传
	 * @param view
	 * @throws IOException
	 */
	public void testApiUpLoadImg(View view) throws IOException {
		
		byte[] b = InputStreamToByte(is);

		HttpRequestController.upLoadImg(this, "1", "zl", Base64.encode(b),
			new HttpResponseListener<ApiUpLoadImg.ApiUpLoadImgResponse>() {
				@Override
				public void onResult(ApiUpLoadImgResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.authorization = "
								+ response.upLoadImage);
					}
					Toast.makeText(MainActivity.this,
							response.getRetInfo(), Toast.LENGTH_SHORT)
							.show();
				}
			});
	}

	/***
	 * 上传文字生成图片
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public void testApiTextImg(View view) throws IOException {
		HttpRequestController.createTextImg(this, "1", "zl", "哇啦个擦擦","微软雅黑","14",
			new HttpResponseListener<ApiCreateTextImg.ApiCreateTextImgResponse>() {
				@Override
				public void onResult(ApiCreateTextImgResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.createTextImg = " + response.createTextImg);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 *获取版块信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiGetBoardInfo(View view) throws IOException {
		HttpRequestController.getBoardInfo(this, "1", "zl",
			new HttpResponseListener<ApiGetBoardInfo.ApiGetBoardInfoResponse>() {
				@Override
				public void onResult(ApiGetBoardInfoResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.boardInfo = " + response.boardInfo);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 添加版块信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiAddBoardInfo(View view) throws IOException {
		HttpRequestController.addBoardInfo(this, new AddBoardInfo(),
			new HttpResponseListener<ApiAddBoardInfo.ApiAddBoardInfoResponse>() {
				@Override
				public void onResult(ApiAddBoardInfoResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.addBoardInfo = " + response.addBoardInfo);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 修改版块信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiUpdateBoardInfo(View view) throws IOException {
		HttpRequestController.updateBoardInfo(this, new UpdateBoardInfo(),
			new HttpResponseListener<ApiUpdateBoardInfo.ApiUpdateBoardInfoResponse>() {
				@Override
				public void onResult(ApiUpdateBoardInfoResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.updateBoardResponse = " + response.updateBoardResponse);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 删除版块信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiDeleteBoard(View view) throws IOException {
		HttpRequestController.deleteBoardInfo(this, "","",
			new HttpResponseListener<ApiDeleteBoard.ApiDeleteBoardResponse>() {
				@Override
				public void onResult(ApiDeleteBoardResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.deleteBoard = " + response.deleteBoard);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 获取海报页面
	 * @param view
	 * @throws IOException
	 */
	public void testApiGetPosterPage(View view) throws IOException {
		HttpRequestController.getPosterPage(this, "","",
			new HttpResponseListener<ApiPosterPage.ApiPosterPageResponse>() {
				@Override
				public void onResult(ApiPosterPageResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.posterPage = " + response.posterPage);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	/***
	 * 海报页面排序
	 * @param view
	 * @throws IOException
	 */
	public void testApiPosterPageSort(View view) throws IOException {
		HttpRequestController.posterPageSort(this, "1","1","1",
			new HttpResponseListener<ApiPosterPageSort.ApiPosterPageSortResponse>() {
				@Override
				public void onResult(ApiPosterPageSortResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.posterPageSort = " + response.posterPageSort);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 添加海报页面
	 * @param view
	 * @throws IOException
	 */
	public void testApiAddPosterPage(View view) throws IOException {
		HttpRequestController.addPosterPage(this, "1","1","1","","",
			new HttpResponseListener<ApiAddPosterPage.ApiAddPosterPageResponse>() {
				@Override
				public void onResult(ApiAddPosterPageResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.posterPageSort = " + response.addPosterPage);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	public void testApiUpdatePosterPage(View view) throws IOException {
		HttpRequestController.updatePosterPage(this,"","1","1","1","","",
			new HttpResponseListener<ApiUpdatePosterPage.ApiUpdatePosterPageResponse>() {
				@Override
				public void onResult(ApiUpdatePosterPageResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.updatePosterPage = " + response.updatePosterPage);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 删除海报页面
	 * @param view
	 * @throws IOException
	 */
	public void testApiDeletePosterPage(View view) throws IOException {
		HttpRequestController.deletePosterPage(this,"",
			new HttpResponseListener<ApiDeletePosterPage.ApiDeletePosterPageResponse>() {
				@Override
				public void onResult(ApiDeletePosterPageResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.deletePosterPage = " + response.deletePosterPage);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/**
	 * 获取海报信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiGetPoster(View view) throws IOException {
		HttpRequestController.getPoster(this,"","","","","",
			new HttpResponseListener<ApiGetPosterInfo.ApiGetPosterInfoResponse>() {
				@Override
				public void onResult(ApiGetPosterInfoResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.deletePosterPage = " + response.posterBoard);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 添加海报
	 * @param view
	 * @throws IOException
	 */
	public void testApiAddPoster(View view) throws IOException {
		HttpRequestController.addPoster(this,"","","","","","","","","",
			new HttpResponseListener<ApiAddPoster.ApiAddPosterResponse>() {
				@Override
				public void onResult(ApiAddPosterResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.addPoster = " + response.addPoster);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 修改海报
	 * @param view
	 * @throws IOException
	 */
	public void testApiUpdatePoster(View view) throws IOException {
		HttpRequestController.updatePoster(this,"","","","","","","","","","",
			new HttpResponseListener<ApiUpdatePoster.ApiUpdatePosterResponse>() {
				@Override
				public void onResult(ApiUpdatePosterResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.updatePoster = " + response.updatePoster);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 发布海报
	 * @param view
	 * @throws IOException
	 */
	public void testApiPublishPoster(View view) throws IOException {
		HttpRequestController.publishPoster(this,"","",
			new HttpResponseListener<ApiPublishPoster.ApiPublishPosterResponse>() {
				@Override
				public void onResult(ApiPublishPosterResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.publishPoster = " + response.publishPoster);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/**
	 * 获取模板信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiGetTemplateInfo(View view) throws IOException {
		HttpRequestController.getTemplateInfo(this,"","","","",
			new HttpResponseListener<ApiGetTemplateInfo.ApiGetTemplateInfoResponse>() {
				@Override
				public void onResult(ApiGetTemplateInfoResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.templateInfo = " + response.templateInfo);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/**
	 * 添加模板信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiAddTemplateInfo(View view) throws IOException {
		HttpRequestController.addTemplateInfo(this,"",1,1,"","","","","",
			new HttpResponseListener<ApiAddTemplateInfo.ApiAddTemplateInfoResponse>() {
				@Override
				public void onResult(ApiAddTemplateInfoResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.addTemplateInfo = " + response.addTemplate);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 修改模板信息
	 * @param view
	 * @throws IOException
	 */
	public void testApiUpdateTemplateInfo(View view) throws IOException {
		HttpRequestController.updateTemplateInfo(this,1,"",1,"","","","","",
			new HttpResponseListener<ApiUpdateTemplateInfo.ApiUpdateTemplateInfoResponse>() {
				@Override
				public void onResult(ApiUpdateTemplateInfoResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.updateTemplate = " + response.updateTemplate);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	/***
	 * 获取海报类型
	 * @param view
	 * @throws IOException
	 */
	public void testApiGetPosterType(View view) throws IOException {
		HttpRequestController.getPosterType(this,1,1,
			new HttpResponseListener<ApiGetPosterType.ApiGetPosterTypeResponse>() {
				@Override
				public void onResult(ApiGetPosterTypeResponse response) {
					if (response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK) {
						Log.i(TAG, "response.getPosterType = " + response.getPosterType);
					}
					Utils.toast(context, response.getRetInfo()+"");
				}
			});
	}
	
	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}
	
}
