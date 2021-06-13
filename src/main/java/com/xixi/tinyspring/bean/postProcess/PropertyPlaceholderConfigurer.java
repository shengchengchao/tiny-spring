package com.xixi.tinyspring.bean.postProcess;

import com.xixi.tinyspring.bean.BeanDefinition;
import com.xixi.tinyspring.bean.PropertyValue;
import com.xixi.tinyspring.bean.PropertyValues;
import com.xixi.tinyspring.bean.factory.AbstractFactory;
import com.xixi.tinyspring.bean.io.DefaultResourceLoader;
import com.xixi.tinyspring.bean.io.Resource;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/13
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String PLACEHOLDER_PREFIX = "${";

    public static final String PLACEHOLDER_SUFFIX = "}";
    
    private String location;
    /**
     * 在bean 实例话前 修改propertyValue中的值
     *
     * @param beanFactory
     * @throws Exception
     */
    @Override
    public void postProcessBeanFactory(AbstractFactory beanFactory) throws Exception {
        Properties properties = loadProperties();
        processProperties(beanFactory, properties);
    }

    /**
     * 修改值
     * @param beanFactory
     * @param properties
     */
    private void processProperties(AbstractFactory beanFactory, Properties properties) {
        Map<String, BeanDefinition> beanDefinitionMap = beanFactory.getBeanDefinitionMap();
        beanDefinitionMap.forEach((k,v)->{
            resolvePropertyValues(v,properties);
        });
    }

    private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        List<PropertyValue> propertyValueList = propertyValues.getPropertyValueList();
        for (PropertyValue propertyValue : propertyValueList) {
            Object value = propertyValue.getValue();
            if(value instanceof String){
                String s = String.valueOf(value);
                int start = s.indexOf(PLACEHOLDER_PREFIX);
                int end = s.indexOf(PLACEHOLDER_SUFFIX);
                if(start!= -1 && end != -1 && start < end){
                    String key =s.substring(start+2,end);
                    String data = properties.getProperty(key);
                    propertyValues.addPropertyValue(new PropertyValue(key,data));
                }
            }
        }
    }

    private Properties loadProperties() throws Exception {

        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource(location);
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        return properties;
    }
}
