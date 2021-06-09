package com.xixi.tinyspring.bean;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/9
 */
public interface DisposableBean {
    /**
     * 注销
     * @throws Exception
     */
    void destroy() ;
}
