package com.xixi.tinyspring.bean.aware;

import com.xixi.tinyspring.context.ApplicationContext;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/10
 */
public interface ApplicationContextAware extends Aware {

    /**
     * 得到对应的ApplicationContext
     * @param applicationContext
     */
    void setApplicationContext(ApplicationContext applicationContext);
}
