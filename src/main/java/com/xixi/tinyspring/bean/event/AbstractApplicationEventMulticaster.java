package com.xixi.tinyspring.bean.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/12
 */
public class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private List<ApplicationEventListener<ApplicationEvent>>  list = new ArrayList<>();

    /**
     * 添加事件
     */
    @Override
    public void addEvent(ApplicationEventListener<?> listener) {
        list.add((ApplicationEventListener<ApplicationEvent>)listener);
    }

    /**
     * 删除事件
     */
    @Override
    public void removeEvent(ApplicationEventListener<?> listener) {
        list.remove((ApplicationEventListener<ApplicationEvent>)listener);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationEventListener<ApplicationEvent> applicationEventApplicationEventListener : list) {
            if(supportsEvent(applicationEventApplicationEventListener,event)){
                applicationEventApplicationEventListener.onApplicationEvent(event);
            }
        }
    }


    protected boolean supportsEvent(ApplicationEventListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
