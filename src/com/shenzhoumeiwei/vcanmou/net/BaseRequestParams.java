package com.shenzhoumeiwei.vcanmou.net;

/**
 * Http请求参数的一个基类
 */
public abstract class BaseRequestParams {

    /**
     * 生成请求参数的抽象方法，子类需要实现
     * 
     * @return
     */
    public abstract String generateRequestParams();

}
