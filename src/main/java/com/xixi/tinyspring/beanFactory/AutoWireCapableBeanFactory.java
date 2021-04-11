package com.xixi.tinyspring.beanFactory;

import com.xixi.tinyspring.BeanDefinition;
import com.xixi.tinyspring.BeanReference;
import com.xixi.tinyspring.PropertyValue;
import com.xixi.tinyspring.PropertyValues;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class AutoWireCapableBeanFactory extends AbstractFactory {

    /**
     * 创建bean
     * @param beanDefinition
     */
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        String beanClassName = beanDefinition.getBeanClassName();
        beanDefinition.setBeanClass(beanClassName);
        Object bean = null;
        Object beanInstance = createBeanInstance(beanDefinition);
        beanDefinition.setBean(beanInstance);
        addPropertyValue(beanDefinition,beanInstance);
        return beanInstance;
    }

    /**
     * 添加参数值
     * @param beanDefinition
     * @param bean
     */
    private void addPropertyValue(BeanDefinition beanDefinition, Object bean) throws Exception {
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues().getPropertyValueList();
        Class beanClass = beanDefinition.getBeanClass();
        for (PropertyValue propertyValue : propertyValueList) {
            Field declaredField = beanClass.getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            declaredField.set(bean,value);
        }
    }

    /**
     * 创建实例
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();

    }
}
