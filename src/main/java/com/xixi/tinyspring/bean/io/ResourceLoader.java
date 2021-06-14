package com.xixi.tinyspring.bean.io;

import java.net.URL;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public abstract   class ResourceLoader {

    public abstract Resource getResource(String location);
}
