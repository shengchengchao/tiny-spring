package com.xixi.tinyspring;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/10
 */
public class HelloWorld {

    private String name;

    private OutputService outputService;

    public void testHello(){
        outputService.output(name);
    }

    public OutputService getOutputService() {
        return outputService;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    public void setName(String name) {
        this.name = name;
    }
}
