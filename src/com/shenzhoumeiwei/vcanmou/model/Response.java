package com.shenzhoumeiwei.vcanmou.model;

public class Response {
	public String IsSuccess;// 操作是否成功（true/false）
	public String ErrorMessage;// 错误信息/
	public String ErrorCode;// 错误代码
	public String Data;//
	public String PageEntity;

	@Override
	public String toString() {
		return "UpdateBoardResponse [IsSuccess=" + IsSuccess
				+ ", ErrorMessage=" + ErrorMessage + ", ErrorCode=" + ErrorCode
				+ ", Data=" + Data + ", PageEntity=" + PageEntity + "]";
	}
}
