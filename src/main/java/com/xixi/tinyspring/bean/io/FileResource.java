package com.xixi.tinyspring.bean.io;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author shengchengchao
 * @Description
 * @createTime 2021/6/7
 */
public class FileResource implements Resource {

    private String filePath;

    public FileResource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        Path path = new File(filePath).toPath();
        return Files.newInputStream(path);

    }
}
