package com.xixi.tinyspring.bean.event;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/12
 */
public abstract class ApplicationEvent {
    private  Object source;

    public  ApplicationEvent(Object source){
         this.source = source;
    }
}
