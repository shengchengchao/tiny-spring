package com.xixi.tinyspring.bean.io;

import java.io.InputStream;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public interface Resource {
    /**
     * 得到输入流
     * @return
     * @throws Exception
     */
    InputStream getInputStream() throws Exception;
}
