package com.xixi.tinyspring.bean.io;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author shengchengchao
 * @Description
 * @createTime 2021/4/11
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        return inputStream;
    }
}
