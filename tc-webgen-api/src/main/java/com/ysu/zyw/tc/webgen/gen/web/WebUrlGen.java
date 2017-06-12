package com.ysu.zyw.tc.webgen.gen.web;

import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;
import org.jooq.Field;
import org.jooq.TableField;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WebUrlGen {
    public static String findControllerMapping(Config config, Config.TableDetail tableDetail) {
        String tableName = NameHelper.findSimpleTableName(config, tableDetail);
        String lowercaseTableName = NamingUtils.underlineToLowercase(tableName);
        lowercaseTableName = "/" + lowercaseTableName;
        if (Objects.nonNull(config.getControllerMappingPostProcessor())) {
            return config.getControllerMappingPostProcessor().apply(lowercaseTableName);
        } else {
            return lowercaseTableName;
        }
    }

    public static String findPrimaryKeysMapping(Config config, Config.TableDetail tableDetail) {
        List<String> primaryKeys = findPrimaryKeySimpleNameOrdered(config, tableDetail);
        primaryKeys = primaryKeys.stream().map(NamingUtils::underlineToLowercaseCamel).collect(Collectors.toList());
        return wrapPathVariables(primaryKeys);
    }

    private static String wrapPathVariables(List<String> pathVars) {
        return "/" + pathVars.stream().map(pathVar ->
                "{" + pathVar + "}").collect(Collectors.joining("/"));
    }

    private static List<String> findPrimaryKeySimpleNameOrdered(Config config,
                                                                Config.TableDetail tableDetail) {
        @SuppressWarnings("unchecked")
        List<TableField> fields = tableDetail.getTable().getPrimaryKey().getFields();
        return fields.stream().map(Field::getName).collect(Collectors.toList());
    }
}