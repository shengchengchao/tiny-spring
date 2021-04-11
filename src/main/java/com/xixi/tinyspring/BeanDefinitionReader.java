package com.xixi.tinyspring;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface BeanDefinitionReader {

    /**
     * 加载资源
     * @param resource
     * @throws Exception
     */
    void loadBeanDefinition(String resource) throws Exception;
}
