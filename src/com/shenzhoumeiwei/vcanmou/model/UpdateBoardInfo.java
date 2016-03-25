package com.shenzhoumeiwei.vcanmou.model;

/***
 * 修改版块
 * @author Administrator
 *
 */
public class UpdateBoardInfo {
	public String PPE_ID; // 模块ID
	public String PP_ID;// 海报页面ID
	public String PPE_Type;// 0,页面元素类型（0文字、1 图片）
	public String PPE_Height;// 元素高度
	public String PPE_Width;// 元素宽度
	public String PPE_Index;// 元素层级
	public String PPE_Image;// 图片文件名称（logic.jpg）
	public String PPE_ImageBorder;// 图片边框
	public String PPE_Href;// 链接地址
	public String PPE_FontSize;// 字体大小
	public String PPE_FontFamily;// 字体
	public String PPE_FontText;// 文本内容
	public String PPE_TextAlign;// 对齐方式
	public String PPE_FontColor;// 字体颜色
	public String PPE_FontBackColor;// 字体背景色
	public String PPE_InWay;// 元素进入方式（淡入...）
	public String PPE_InDirection;// 元素进入方向（上 、下、左、右）（1、2、3、4）
	public String PPE_Margin;// margin:10px 5px 15px 20px;
	public String PPE_PointCenter;// 元素中心位置 "{x,y}"
	public String PPE_PointLeftUp;// 元素左上位置 "{x,y}"
	public String PPE_PointRightUp;// 元素左下位置 "{x,y}"
	public String PPE_PointLeftDown;// 元素右上位置 "{x,y}"
	public String PPE_PointRightDown;// 元素右下位置 "{x,y}"

	@Override
	public String toString() {
		return "UpdateBoardInfo [PPE_ID=" + PPE_ID + ", PP_ID=" + PP_ID
				+ ", PPE_Type=" + PPE_Type + ", PPE_Height=" + PPE_Height
				+ ", PPE_Width=" + PPE_Width + ", PPE_Index=" + PPE_Index
				+ ", PPE_Image=" + PPE_Image + ", PPE_ImageBorder="
				+ PPE_ImageBorder + ", PPE_Href=" + PPE_Href
				+ ", PPE_FontSize=" + PPE_FontSize + ", PPE_FontFamily="
				+ PPE_FontFamily + ", PPE_FontText=" + PPE_FontText
				+ ", PPE_TextAlign=" + PPE_TextAlign + ", PPE_FontColor="
				+ PPE_FontColor + ", PPE_FontBackColor=" + PPE_FontBackColor
				+ ", PPE_InWay=" + PPE_InWay + ", PPE_InDirection="
				+ PPE_InDirection + ", PPE_Margin=" + PPE_Margin
				+ ", PPE_PointCenter=" + PPE_PointCenter + ", PPE_PointLeftUp="
				+ PPE_PointLeftUp + ", PPE_PointRightUp=" + PPE_PointRightUp
				+ ", PPE_PointLeftDown=" + PPE_PointLeftDown
				+ ", PPE_PointRightDown=" + PPE_PointRightDown + "]";
	}

}
