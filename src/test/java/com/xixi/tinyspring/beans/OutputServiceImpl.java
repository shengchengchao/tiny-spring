package com.xixi.tinyspring.beans;

import org.junit.Assert;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/12
 */
public class OutputServiceImpl implements OutputService {


    public void output(String text){
        System.out.println(text);
    }



}
