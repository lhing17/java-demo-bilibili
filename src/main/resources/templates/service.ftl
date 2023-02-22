package ${servicePackage!};


import ${modelPackage!}.${modelName!};

public interface ${modelName!}Service {

        /**
        * 保存
        * @param ${modelName!} 实体
        * @return 实体
        */
        ${modelName!} save(${modelName!} ${modelName!});

        /**
        * 更新
        * @param ${modelName!} 实体
        * @return 实体
        */
        ${modelName!} update(${modelName!} ${modelName!});

        /**
        * 删除
        * @param id 主键
        * @return 是否成功
        */
        boolean delete(Long id);

        /**
        * 根据id获取
        * @param id 主键
        * @return 实体
        */
        ${modelName!} get(Long id);

}
