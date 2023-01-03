package com.example.demo;

import fj.F;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFileDemo {
    public static void main(String[] args) throws IOException {
        // 1. JDK自带的文件复制、移动、删除
//        Files.copy(Paths.get("doc/后端自测要点.md"), Paths.get("doc/1.md"));
//        Files.copy(Files.newInputStream(Paths.get("doc/后端自测要点.md")), Paths.get("doc/2.md"), StandardCopyOption.REPLACE_EXISTING);
//        Files.copy(Paths.get("doc/后端自测要点.md"), Files.newOutputStream(Paths.get("doc/3.md")));

//        try(InputStream inputStream = Files.newInputStream(Paths.get("doc/后端自测要点.md"));
//            OutputStream out = Files.newOutputStream(Paths.get("doc/4.md"))) {
//            inputStream.transferTo(out);
//        }

//        Files.move(Paths.get("doc/4.md"), Paths.get("doc/5.md"));
//        Files.delete(Paths.get("doc/1.md"));


        // 2. apache commons-io的相关工具方法
//        FileUtils.copyDirectory(new File("doc"), new File("doc2"));
//        FileUtils.copyDirectoryToDirectory(new File("doc"), new File("doc2"));
//        FileUtils.copyFileToDirectory(new File("doc/2.md"), new File("doc2"));

        FileUtils.cleanDirectory(new File("doc2"));

    }
}
