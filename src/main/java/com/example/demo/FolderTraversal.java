package com.example.demo;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class FolderTraversal {

    public static void main(String[] args) throws IOException {
        // 1. 需求：打印文件夹内所有文件名称（含子级）
//        Files.walk(Paths.get("src")).forEach(System.out::println);


        // 2. 注意事项 (1) try-with-resources (2) 遍历结果包含文件夹
//        try(Stream<Path> pathStream = Files.walk(Paths.get("src"))) {
//            pathStream.filter(Files::isRegularFile).forEach(System.out::println);
//        }


        // 3. 需求：在文件夹的所有java文件中寻找单词gender
        Files.walkFileTree(Paths.get("src"), new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java")) {
                    String content = Files.readString(file);
                    if (content.contains("gender")) {
                        System.out.println(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });



    }
}
