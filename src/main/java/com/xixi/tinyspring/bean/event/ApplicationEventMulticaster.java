package com.xixi.tinyspring.bean.event;


/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/10
 */
public interface ApplicationEventMulticaster {
    /**
     *   添加事件
     * @param listener
     */
    void addEvent(ApplicationEventListener<?> listener);

    /**
     * 删除事件 s
     * @param listener
     */
    void removeEvent(ApplicationEventListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
