package com.xixi.tinyspring.aop;

import com.xixi.tinyspring.aop.Advice.AdvisedSupport;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    /**
     * 这里 要根据当前类是否有接口 去创建 JDK代理 还是Cglib 代理
     * @return
     */
    @Override
    public Object getProxy() {
        Class<?>[] interfaces = this.getTargetSource().getInterfaces();
        if(interfaces.length>0){
            System.out.println("使用jdk创建代理");
            return createJdkProxy(this).getProxy();
        }else {
            System.out.println("使用cglib创建代理");
            return createCglibProxy(this).getProxy();
        }

    }

    private Cglib2AopProxy createCglibProxy(AdvisedSupport advisedSupport) {
        return new Cglib2AopProxy(advisedSupport);
    }

    protected JdkDynamicAopProxy createJdkProxy(AdvisedSupport advisedSupport) {

        return new JdkDynamicAopProxy(advisedSupport);
    }

}
