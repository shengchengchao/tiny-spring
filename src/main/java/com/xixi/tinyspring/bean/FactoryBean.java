package com.xixi.tinyspring.bean;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/10
 */
public interface FactoryBean<T> {
    /**
     * 得到对应结果
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;


}
