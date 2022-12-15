package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TextFileReader {

    public static void main(String[] args) throws URISyntaxException, IOException {
        // 如何快速将文本文件读取为字符串
        // 示例需求：将resources/templates/test.json读取并转为Map对象
        URL resource = TextFileReader.class.getClassLoader().getResource("templates/test.json");
        URI uri;
        if (resource != null) {
            uri = resource.toURI();
            Path path = Paths.get(uri);
            byte[] bytes = Files.readAllBytes(path);
            String s = new String(bytes);
            System.out.println(s);
            JSONObject jsonObject = JSON.parseObject(s);
            System.out.println(jsonObject);
        }


        // 如何将Map对象转为json字符串并写入文本文件中
        Map<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", 30);
        map.put("gender", 0);
        String s = JSON.toJSONString(map);
        Path path = Paths.get("a.txt");
        Files.write(path, s.getBytes(StandardCharsets.UTF_8));


        // 如果你用的是jdk11以上版本...
        String s1 = Files.readString(Paths.get(resource.toURI()));
        Files.writeString(Paths.get("b.txt"), s1);


        // 如果你需要逐行读取文件
        String s2 = Files.lines(Paths.get(resource.toURI())).collect(Collectors.joining("|"));
        System.out.println(s2);

    }
}
