package com.example.demo;

import com.example.demo.model.Excel;
import com.example.demo.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExcelExporter {


    public static void export(List<User> users, OutputStream outputStream) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();

            // 获取所有包含Excel注解的字段
            Field[] declaredFields = User.class.getDeclaredFields();
            List<Field> filteredFields = Stream.of(declaredFields)
                    .filter(f -> f.isAnnotationPresent(Excel.class))
                    .collect(Collectors.toList());

            // 写入标题行
            writeTitleRow(sheet, filteredFields);

            // 写入数据行
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                Row userRow = sheet.createRow(i + 1);
                writeDataRow(userRow, user, filteredFields);
            }

            // 写入文件
            workbook.write(outputStream);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private static void writeTitleRow(Sheet sheet, List<Field> filteredFields) {
        Row row = sheet.createRow(0);

        for (int i = 0; i < filteredFields.size(); i++) {
            Cell cell = row.createCell(i);
            Excel excel = filteredFields.get(i).getAnnotation(Excel.class);
            cell.setCellValue(excel.name());
        }
    }

    private static void writeDataRow(Row userRow, User user, List<Field> filteredFields) throws IllegalAccessException {

        // 构造字段的值字典
        Map<Field, Map<String, String>> fieldDict = buildFieldDict(filteredFields);

        for (int i = 0; i < filteredFields.size(); i++) {
            Cell cell = userRow.createCell(i);
            Field field = filteredFields.get(i);
            field.setAccessible(true);
            Excel annotation = field.getAnnotation(Excel.class);
            if (StringUtils.hasLength(annotation.dictValue())) {
                String key = String.valueOf(field.get(user));
                cell.setCellValue(fieldDict.get(field).get(key));
            } else {
                cell.setCellValue(String.valueOf(field.get(user)));
            }
            field.setAccessible(false);
        }

    }

    private static Map<Field, Map<String, String>> buildFieldDict(List<Field> filteredFields) {
        Map<Field, Map<String, String>> fieldDict = new HashMap<>();
        for (Field field : filteredFields) {
            Excel annotation = field.getAnnotation(Excel.class);
            if (StringUtils.hasLength(annotation.dictValue())) {
                String d = annotation.dictValue();
                String[] kvs = d.split(",");
                Map<String, String> map = new HashMap<>();
                for (String kv : kvs) {
                    String[] split = kv.split("=");
                    map.put(split[0], split[1]);
                }
                fieldDict.put(field, map);
            }
        }
        return fieldDict;
    }

    public static void main(String[] args) throws IOException {
        List<User> users = List.of(
                new User("张三", 20, 1),
                new User("李四", 30, 0),
                new User("王五", 40, 1)
        );
        OutputStream outputStream = Files.newOutputStream(Paths.get("target/users.xlsx"));
        ExcelExporter.export(users, outputStream);
    }
}
