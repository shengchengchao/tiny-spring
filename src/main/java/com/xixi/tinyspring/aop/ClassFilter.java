package com.xixi.tinyspring.aop;

/** 类过滤器
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
