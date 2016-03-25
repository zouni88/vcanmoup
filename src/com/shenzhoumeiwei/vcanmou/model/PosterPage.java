package com.shenzhoumeiwei.vcanmou.model;

import java.util.List;

public class PosterPage extends Response {
	public List<PosterPageItem> List;

	public class PosterPageItem {
		public String PP_ID;// 海报页面ID
		public String P_ID;// 海报ID
		public String PTM_ID;// 海报模板ID
		public String PP_BackImage;// 页面背景
		public String PP_Type;// 类型（0 海报、1活动）
		public String PP_Order;// 排序字段
		public String IsSuccess;// 操作是否成功（true/false）
		public String ErrorMessage;// 错误信息
		public String ErrorCode;// 错误代码
		
		@Override
		public String toString() {
			return "PosterPageItem [PP_ID=" + PP_ID + ", P_ID=" + P_ID
					+ ", PTM_ID=" + PTM_ID + ", PP_BackImage=" + PP_BackImage
					+ ", PP_Type=" + PP_Type + ", PP_Order=" + PP_Order
					+ ", IsSuccess=" + IsSuccess + ", ErrorMessage="
					+ ErrorMessage + ", ErrorCode=" + ErrorCode + "]";
		}
		
	}

}
