package com.shenzhoumeiwei.vcanmou.model;//

import java.util.List;
public class PosterInfo extends Response {

	public List<Poster> List;

	public class Poster {
		public PostersEntity PostersEntity;
		public PosterTypesEntity PosterTypesEntity;
	}

	public class PostersEntity {
		public String P_ID;// 海报ID
		public String MC_ID;// 商户ID
		public String U_ID;// 用户ID
		public String PTM_ID;// 模板ID
		public String PT_ID;//
		public String P_Guid;// 海报唯一标识
		public String P_Title;// 标题
		public String P_Code;// 海报代码
		public String P_Description;// 海报描述
		public String P_ViewCount;// 点击次数
		public String P_IssueCount;//
		public String P_UV;// 用户量
		public String P_ToCarteV;// 贡献菜谱浏览量
		public String P_ToOrderV;// 引流订单量
		public String P_ToOrderMoney;// 引流订单金额
		public String P_VTime;// 平均停留时长。单位：秒
		public String P_VStore;// 收藏数量
		public String P_IsPublic;// 是否公开海报
		public String P_Remark;// 海报备注
		public String P_KeyWord;// 页面关键字
		public String P_Image;// 页面图片
		public String P_FloatImage;// 浮动图片
		public String P_QRCode;//
		public String P_State;//
		public String LastUpdateTime;//
		public String SetTime;//
		public String Reamrk;//
		public String IsDelete;//
		public String SaveState;//
	}

	public class PosterTypesEntity {
		public String PT_ID;//
		public String PT_Code;//
		public String PT_Name;//
		public String PT_ParentID;//
		public String PT_Order;//
		public String LastUpdateTime;//
		public String SetTime;//
		public String Remark;//
		public String IsDelete;//
		public String SaveState;//
	}
}
