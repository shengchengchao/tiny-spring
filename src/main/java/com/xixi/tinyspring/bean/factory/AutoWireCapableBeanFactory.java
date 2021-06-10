package com.xixi.tinyspring.bean.factory;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.xixi.tinyspring.aop.BeanFactoryAware;
import com.xixi.tinyspring.bean.BeanDefinition;
import com.xixi.tinyspring.bean.BeanReference;
import com.xixi.tinyspring.bean.InitializingBean;
import com.xixi.tinyspring.bean.PropertyValue;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
        Object beanInstance = createBeanInstance(beanDefinition);
        if(BeanDefinition.SINGLETON.equals(beanDefinition.getScope())){
            beanDefinition.setBean(beanInstance);
        }


        addPropertyValue(beanDefinition,beanInstance);
        // InitializingBean 使用
        invokeInitMethod(beanDefinition,beanInstance,beanClassName);
        return beanInstance;
    }

    /**
     *  添加初始化方法
     * @param beanDefinition
     * @param beanInstance
     * @param beanClassName
     */
    private void invokeInitMethod(BeanDefinition beanDefinition, Object beanInstance, String beanClassName) throws Exception {
          if (beanInstance instanceof InitializingBean){
              ((InitializingBean) beanInstance).afterPropertiesSet();
          }
          if(beanDefinition.getInitMethodName()!=null){
              String initMethod = beanDefinition.getInitMethodName();
              Method publicMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethod);
              if(publicMethod ==null){
                  throw new RuntimeException("Could not find an init method named '" + initMethod + "' on bean with name '" + beanClassName + "'");
              }
              publicMethod.invoke(beanInstance);
          }
    }

    /**
     * 添加参数值
     * @param beanDefinition
     * @param bean
     */
    private void addPropertyValue(BeanDefinition beanDefinition, Object bean) throws Exception {
        //这一步的目的是为了是的AspectAwareAdvisorAutoProxyCreator中具有beanFactory，方便从中获取AspectJExpressionPointcutAdvisor.class类的实例
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues().getPropertyValueList();
        Class beanClass = beanDefinition.getBeanClass();
        for (PropertyValue propertyValue : propertyValueList) {
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean,value);
            } catch (Exception e) {
                Field declaredField = beanClass.getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean,value);
            }

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
