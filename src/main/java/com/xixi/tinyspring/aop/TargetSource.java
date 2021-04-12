package com.xixi.tinyspring.aop;

/** 被代理的对象
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class TargetSource {

    private Class<?> targetClass;

    private Object target;

    private Class<?>[] interfaces;

    public TargetSource(Object target,  Class<?> targetClass,Class<?>... interfaces) {
        this.targetClass = targetClass;
        this.target = target;
        this.interfaces = interfaces;
    }



    public Object getTarget() {
        return target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }
}
