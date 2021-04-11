package com.xixi.tinyspring.aop;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
