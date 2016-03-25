package com.shenzhoumeiwei.vcanmou.model;

import java.util.List;

public class BoardInfo {
	public String IsSuccess;//操作是否成功（true/false）
	public String ErrorMessage;// 错误信息/
	public String ErrorCode;//错误代码
	public String Data;//图片地址
	public String PageEntity;
	public List<Board> List;
	
	public class Board{
		 public String PPE_ID;//海报板块ID1,
         public String PP_ID ;//海报页面ID
         public String PPE_Type;// 0,页面元素类型（0文字、1 图片）
         public String PPE_Height;//   元素高度
         public String PPE_Width;//  元素宽度
         public String PPE_Index;//元素层级
         public String PPE_Image;//图片文件名称（logic.jpg）
         public String PPE_ImageBorder;// 图片边框
         public String PPE_Href;// 链接地址
         public String PPE_FontSize;// 字体大小
         public String PPE_FontFamily;//字体
         public String PPE_FontText;//  文本内容
         public String PPE_FontColor;// 字体颜色
         public String PPE_FontBackColor;//字体背景色
         public String PPE_InWay;// 元素进入方式（淡入...）
         public String PPE_InDirection;//元素进入方向（上 、下、左、右）（1、2、3、4）
         public String PPE_Margin;//  margin:10px 5px 15px 20px;
         public String PPE_PointCenter;// 元素中心位置 "{x,y}"
         public String PPE_PointLeftUp;//元素左上位置 "{x,y}"
         public String PPE_PointRightUp;//元素左下位置 "{x,y}"
         public String PPE_PointLeftDown;// 元素右上位置 "{x,y}"
         public String PPE_PointRightDown;// 元素右下位置 "{x,y}"
         public String LastUpdateTime;// 
         public String SetTime;// 
         public String Reamrk;// 
         public String IsDelete;//  
         public String SaveState;// 0
	}
	
}
