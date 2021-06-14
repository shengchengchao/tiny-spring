package com.xixi.tinyspring.context;

import com.xixi.tinyspring.bean.factory.BeanFactory;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface ApplicationContext extends BeanFactory {

    /**
     * 手动关闭
     */
    void registerShutdownHook();

    void close() throws Exception;
}
