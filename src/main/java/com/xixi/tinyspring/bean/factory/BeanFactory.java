package com.xixi.tinyspring.bean.factory;


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
