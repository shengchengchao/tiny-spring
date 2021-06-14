package com.xixi.tinyspring.bean.event;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/13
 */
public interface ApplicationEventListener<E extends ApplicationEvent>  {

    void onApplicationEvent(E event);

}
