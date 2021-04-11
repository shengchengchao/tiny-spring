package com.xixi.tinyspring.bean.io;

import java.net.URL;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class ResourceLoader {

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
