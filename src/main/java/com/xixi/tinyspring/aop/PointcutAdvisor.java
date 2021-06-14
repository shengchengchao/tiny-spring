package com.xixi.tinyspring.aop;

import com.xixi.tinyspring.aop.Advice.Advisor;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();

}
