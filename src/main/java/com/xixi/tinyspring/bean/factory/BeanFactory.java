package com.xixi.tinyspring.bean.factory;

import com.xixi.tinyspring.bean.BeanDefinition;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public interface BeanFactory {




    /**
     * 得到bean
     * @param name
     * @return
     */
    Object getBean(String name) throws Exception;


}
