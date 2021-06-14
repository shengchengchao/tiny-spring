package com.xixi.tinyspring.bean.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/7
 */
public class ClassPathResource implements Resource {

    private String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(path);
        if(resourceAsStream==null){
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return resourceAsStream;
    }
}
