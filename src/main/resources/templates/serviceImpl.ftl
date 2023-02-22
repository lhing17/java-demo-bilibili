package ${serviceImplPackage!};

import ${servicePackage!}.${serviceName!};
import ${daoPackage!}.${daoName!};
import ${modelPackage!}.${modelName!};

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ${serviceImplName!} implements ${serviceName!} {

    @Resource
    private ${daoName!} ${modelName?uncap_first!}Dao;

    /**
    * 保存
    * @param ${modelName!} 实体
    * @return 实体
    */
    public ${modelName!} save(${modelName!} ${modelName!}) {
        ${modelName?uncap_first!}Dao.insert(${modelName!});
        return ${modelName!};
    }

    /**
    * 更新
    * @param ${modelName!} 实体
    * @return 实体
    */
    public ${modelName!} update(${modelName!} ${modelName!}) {
        ${modelName?uncap_first!}Dao.updateById(${modelName!});
        return ${modelName!};
    }

    /**
    * 删除
    * @param id 主键
    * @return 是否成功
    */
    public boolean delete(Long id) {
        return ${modelName?uncap_first!}Dao.deleteById(id) > 0;
    }

    /**
    * 根据id获取
    * @param id 主键
    * @return 实体
    */
    public ${modelName!} get(Long id) {
        return ${modelName?uncap_first!}Dao.findById(id);
    }
}
