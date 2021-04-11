package com.xixi.tinyspring.aop;

/** 被代理的对象
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class TargetSource {

    private Class targetClass;

    private Object target;

    public TargetSource(Class targetClass, Object target) {
        this.targetClass = targetClass;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
