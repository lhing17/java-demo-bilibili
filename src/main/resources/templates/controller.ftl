package ${controllerPackage!};

import ${servicePackage!}.${serviceName!};

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class ${modelName!}Controller {

    @Resource
    private ${modelName!}Service ${modelName?uncap_first!}Service;

}
