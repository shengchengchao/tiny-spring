package com.xixi.tinyspring.beans;

import org.junit.Assert;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class OutputService {

    private HelloWorld helloWorld;



    public void output(String text){
        Assert.assertNotNull(helloWorld);
        System.out.println(text);
    }


    public void setHelloWorld(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }
}
