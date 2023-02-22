package ${daoPackage!};

import ${modelPackage!}.${modelName!};
import org.springframework.stereotype.Repository;

@Repository
public interface ${modelName!}Dao {
    int insert(${modelName!} ${modelName?uncap_first!});

    int updateById(${modelName!} ${modelName?uncap_first!});

    int deleteById(Long id);

    ${modelName!} findById(Long id);

}
