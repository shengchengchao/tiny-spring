package com.xixi.tinyspring;

import lombok.Data;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
@Data
public class BeanDefinition {

    private String beanName;

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }
}
