package com.shenzhoumeiwei.vcanmou.model;

/**
 * 发布海报
 * @author Administrator
 *
 */
public class PublishPoster {

	public String IsSuccess;
    public String ErrorMessage;
    public String ErrorCode;
    public String PageEntity;
    public Data Data;
//    ": {
//        "content": "http://localhost:8880/Posters/Get?P_Guid=cc4a95469a0c47c49ae712d2545cbf3c",
//        "path": "/Images/MC_1/Poster/cc4a95469a0c47c49ae712d2545cbf3c.jpg"
//    },
    public class Data{
    	public String content;
    	public String path;
    }
}
