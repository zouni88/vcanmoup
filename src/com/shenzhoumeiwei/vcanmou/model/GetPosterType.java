package com.shenzhoumeiwei.vcanmou.model;

/***
 * 获取海报类型
 * @author Administrator
 *
 */
public class GetPosterType {

	public String IsSuccess;
	public String ErrorMessage;
	public String ErrorCode;
	public String PageEntity;
	public Data Data;

	public class Data {
		public String PT_ID;
		public String PT_Code;
		public String PT_Name;
		public String PT_ParentID;
		public String PT_Order;
		public String LastUpdateTime;
		public String SetTime;
		public String Remark;
		public String IsDelete;
		public String SaveState;

		@Override
		public String toString() {
			return "Data [PT_ID=" + PT_ID + ", PT_Code=" + PT_Code
					+ ", PT_Name=" + PT_Name + ", PT_ParentID=" + PT_ParentID
					+ ", PT_Order=" + PT_Order + ", LastUpdateTime="
					+ LastUpdateTime + ", SetTime=" + SetTime + ", Remark="
					+ Remark + ", IsDelete=" + IsDelete + ", SaveState="
					+ SaveState + "]";
		}

	}
}
