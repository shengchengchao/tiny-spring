package com.xixi.tinyspring.bean;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class HelloWorldImpl implements HelloWorld {

    private String name;

    private OutputService outputService;



    public OutputService getOutputService() {
        return outputService;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void testHello() {
        outputService.output(name);
    }
}
