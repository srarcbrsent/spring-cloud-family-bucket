package com.ysu.zyw.tc.webgen.helper;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.config.Config;
import lombok.Data;
import org.jooq.Field;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ImportHelper {
    public static List<String> findTermsImports(Field[] fields) {
        Set<String> importsSet = Arrays.stream(fields)
                .filter(field -> !field.getType().getName().startsWith("java.lang"))
                .map(field -> field.getType().getName()).collect(Collectors.toSet());
        importsSet.add(List.class.getTypeName());
        importsSet.add(Data.class.getTypeName());
        return Lists.newArrayList(importsSet);
    }

    public static List<String> findControllerImports(Config config, Config.TableDetail tableDetail) {
        return Lists.newArrayList(
                // - class
                Controller.class.getTypeName(),
                RequestMapping.class.getTypeName(),
                // - param annotation
                PathVariable.class.getTypeName(),
                RequestBody.class.getTypeName(),
                // - method
                PostMapping.class.getTypeName(),
                DeleteMapping.class.getTypeName(),
                PutMapping.class.getTypeName(),
                GetMapping.class.getTypeName(),
                // - field
                Resource.class.getTypeName(),
                // - pojo
                tableDetail.getPojo().getTypeName(),
                // - list
                List.class.getTypeName(),
                // - terms
                NameHelper.findTermsClassFullName(config, tableDetail),
                // - svc
                NameHelper.findServiceClassFullName(config, tableDetail)
        );
    }

    public static List<String> findServiceImports(Config config, Config.TableDetail tableDetail) {
        return Lists.newArrayList(
                tableDetail.getPojo().getTypeName(),
                // - list
                List.class.getTypeName(),
                // - terms
                NameHelper.findTermsClassFullName(config, tableDetail)
        );
    }

    public static List<String> findServiceImplImports(Config config, Config.TableDetail tableDetail) {
        return Lists.newArrayList(
                tableDetail.getPojo().getTypeName(),
                // - list
                List.class.getTypeName(),
                // - annotation
                Service.class.getTypeName(),
                // - terms
                NameHelper.findTermsClassFullName(config, tableDetail),
                // - service
                NameHelper.findServiceClassFullName(config, tableDetail)
        );
    }

    public static List<String> findClientImports(Config config, Config.TableDetail tableDetail) {
        return Lists.newArrayList(
                tableDetail.getPojo().getTypeName(),
                // - list
                List.class.getTypeName(),
                // - terms
                NameHelper.findTermsClassFullName(config, tableDetail),
                // - feign client
                FeignClient.class.getTypeName(),
                // - request mapping
                RequestMapping.class.getTypeName(),
                RequestMethod.class.getTypeName(),
                // - param annotation
                PathVariable.class.getTypeName(),
                RequestBody.class.getTypeName()
        );
    }

}