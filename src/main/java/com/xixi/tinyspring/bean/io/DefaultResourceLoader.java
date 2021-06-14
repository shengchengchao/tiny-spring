package com.xixi.tinyspring.bean.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/7
 */
public class DefaultResourceLoader extends ResourceLoader {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";


    @Override
    public Resource getResource(String location) {
         if(location.startsWith(CLASSPATH_URL_PREFIX)){
             return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
         }else {
             try {
                 URL url = new URL(location);
                 return new UrlResource(url);
             } catch (MalformedURLException e) {
                 return new FileResource(location);
             }
         }

    }
}
