package com.xixi.tinyspring.bean;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/9
 */
public class DisposableBeanAdapter implements DisposableBean {

    private Object bean;
    private String destroyMethod;
    private String beanName;

    public DisposableBeanAdapter(Object bean, String destroyMethod, String beanName) {
        this.bean = bean;
        this.destroyMethod = destroyMethod;
        this.beanName = beanName;
    }

    /**
     * 注销
     *
     * @throws Exception
     */
    @Override
    public void destroy() {
        if(bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        if(StrUtil.isNotBlank(destroyMethod)){
            Method publicMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethod);
            ReflectUtil.invoke(bean,publicMethod);
        }
    }
}
