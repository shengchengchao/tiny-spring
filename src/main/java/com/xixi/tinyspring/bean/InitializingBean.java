package com.xixi.tinyspring.bean;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/9
 */
public interface InitializingBean {
    /**
     * 修改一些参数值
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
