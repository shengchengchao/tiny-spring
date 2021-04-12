package com.xixi.tinyspring.beans;

import com.xixi.tinyspring.context.ApplicationContext;
import com.xixi.tinyspring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public class ApplicationContextTest {


    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        HelloWorld helloWorldService = (HelloWorld) applicationContext.getBean("helloWorld");
        helloWorldService.testHello();
    }


}
