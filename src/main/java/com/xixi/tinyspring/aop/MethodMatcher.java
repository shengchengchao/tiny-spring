package com.xixi.tinyspring.aop;

import java.lang.reflect.Method;

/** 方法匹配器
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
