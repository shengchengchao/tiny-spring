package com.xixi.tinyspring.io;

import java.io.InputStream;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface Resource {

    InputStream getInputStream() throws Exception;
}
