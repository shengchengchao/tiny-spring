package com.xixi.tinyspring.aop.Advice;

import org.aopalliance.aop.Advice;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface Advisor {

    Advice getAdvice();
}
