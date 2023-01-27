package com.example.demo;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipDemo {

    public static void main(String[] args) throws IOException {

        // 将现有文件夹压缩为zip文件
        Path testZipPath = Path.of("tmp/test.zip");
        Files.createDirectories(testZipPath.getParent());
        try (OutputStream out = Files.newOutputStream(testZipPath);
             ZipOutputStream zipOutputStream = new ZipOutputStream(out);
             Stream<Path> dir = Files.walk(Paths.get("doc"))) {
            dir.forEachOrdered(
                    path -> {
                        try {
                            if (Files.isRegularFile(path)) {
                                zipOutputStream.putNextEntry(new ZipEntry(path.toString()));
                                Files.copy(path, zipOutputStream);
                            } else {
                                zipOutputStream.putNextEntry(new ZipEntry(path + "/"));
                            }
                            zipOutputStream.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }

        // 读取zip文件
        ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(testZipPath));
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            System.out.println(zipEntry.getName());
            Path tmp = Path.of("tmp");
            if (zipEntry.getName().endsWith("/")) {
                Files.createDirectories(tmp.resolve(zipEntry.getName()));
            } else {
                Files.copy(zipInputStream, tmp.resolve(zipEntry.getName()));
            }
            zipInputStream.closeEntry();
        }

        // 使用apache vfs2读取zip文件
        FileSystemManager fsManager = VFS.getManager();
        FileObject fileObject = fsManager.resolveFile("zip:///Users/lianghao/IdeaProjects/demo/tmp/test.zip!/doc/3.md");
        fileObject.getContent().write(Files.newOutputStream(Path.of("tmp/3.md")));
    }
}
