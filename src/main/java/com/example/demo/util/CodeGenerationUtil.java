package com.example.demo.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class CodeGenerationUtil {
    private CodeGenerationUtil() {
    }


    public static String getClassName(String path) {
        String[] split = path.split("/");
        String className = split[split.length - 1];
        return className.substring(0, className.length() - 5);
    }


    /**
     * 使用freemarker生成Controller代码
     */
    public static void generateAllCodes(String path) {
        String modelName = getClassName(path);
        String controllerName = modelName + "Controller";
        String serviceName = modelName + "Service";
        String serviceImplName = modelName + "ServiceImpl";
        String daoName = modelName + "Dao";

        String modelPackage = "com.example.demo.model";
        String servicePackage = "com.example.demo.service";
        String daoPackage = "com.example.demo.dao";
        String controllerPackage = "com.example.demo.controller";
        String serviceImplPackage = "com.example.demo.service.impl";

        String controllerPath = "src/main/java/com/example/demo/controller/";
        String servicePath = "src/main/java/com/example/demo/service/";
        String serviceImplPath = "src/main/java/com/example/demo/service/impl/";
        String daoPath = "src/main/java/com/example/demo/dao/";

        String controllerTemplate = "controller.ftl";
        String serviceTemplate = "service.ftl";
        String serviceImplTemplate = "serviceImpl.ftl";
        String daoTemplate = "dao.ftl";

        try {
            generateCode(controllerPath, controllerTemplate, controllerName, Map.of("controllerPackage", controllerPackage, "serviceName", serviceName, "modelPackage", modelPackage, "modelName", modelName, "servicePackage", servicePackage));
            generateCode(servicePath, serviceTemplate, serviceName, Map.of("servicePackage", servicePackage, "modelPackage", modelPackage, "modelName", modelName));
            generateCode(serviceImplPath, serviceImplTemplate, serviceImplName, Map.of("serviceImplPackage", serviceImplPackage, "serviceImplName", serviceImplName, "modelPackage", modelPackage, "modelName", modelName, "servicePackage", servicePackage, "serviceName", serviceName, "daoPackage", daoPackage, "daoName", daoName));
            generateCode(daoPath, daoTemplate, daoName, Map.of("daoPackage", daoPackage, "modelPackage", modelPackage, "modelName", modelName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateCode(String path, String templateName, String className, Map<String, Object> params) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassLoaderForTemplateLoading(CodeGenerationUtil.class.getClassLoader(), "templates");
        try {
            Template template = configuration.getTemplate(templateName);
            template.process(params, Files.newBufferedWriter(Path.of(path + className + ".java")));
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        generateAllCodes("src/main/java/com/example/demo/model/Teacher.java");
    }


}
