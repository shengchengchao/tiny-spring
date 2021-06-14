package com.xixi.tinyspring.beans;

import cn.hutool.core.io.IoUtil;
import com.xixi.tinyspring.bean.io.DefaultResourceLoader;
import com.xixi.tinyspring.bean.io.FileResource;
import com.xixi.tinyspring.bean.io.Resource;
import org.junit.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/7
 */
public class XmlReaderTest {

    @Test()
    public void classPathTest() throws Exception {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        Resource resource = defaultResourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        assertThat(content).isEqualTo("hello world");

        Resource txtResource = defaultResourceLoader.getResource("src/test/resources/hello.txt");
        assertThat(txtResource instanceof FileResource).isTrue();
        InputStream inputStream1 = txtResource.getInputStream();
        String txtContent = IoUtil.readUtf8(inputStream1);
        System.out.println(txtContent);
        assertThat(txtContent).isEqualTo("hello world");

        
    }
}
