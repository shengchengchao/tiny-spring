package com.xixi.tinyspring.context;

import com.xixi.tinyspring.bean.BeanDefinition;
import com.xixi.tinyspring.bean.factory.AbstractFactory;
import com.xixi.tinyspring.bean.factory.AutoWireCapableBeanFactory;
import com.xixi.tinyspring.bean.io.ResourceLoader;
import com.xixi.tinyspring.bean.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Map;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String location;

    public ClassPathXmlApplicationContext(String location) throws Exception {

        this(location,new AutoWireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String location,AbstractFactory abstractBeanFactory) throws Exception {
        super(abstractBeanFactory);
        this.location = location;
        refresh();
    }


    @Override
    public void refresh() throws Exception {

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition(location);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            abstractBeanFactory.registerBean(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }
    }
}
