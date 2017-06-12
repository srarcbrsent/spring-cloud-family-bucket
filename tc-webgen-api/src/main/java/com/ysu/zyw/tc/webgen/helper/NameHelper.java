package com.ysu.zyw.tc.webgen.helper;

import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;

import java.util.Objects;

public class NameHelper {

    // --- table name
    public static String findSimpleTableName(Config config,
                                             Config.TableDetail tableDetail) {
        String tableName = tableDetail.getTable().getName();
        if (Objects.nonNull(config.getTableNamePostProcessor())) {
            tableName = config.getTableNamePostProcessor().apply(tableName);
        }
        return tableName;
    }

    // --- class name
    public static String findTermsClassName(Config config, Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToUppercaseCamel(tableName) + Const.TERMS_NAME_SUFFIX;
    }

    public static String findControllerClassName(Config config, Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToUppercaseCamel(tableName) + Const.CONTROLLER_NAME_SUFFIX;
    }

    public static String findServiceClassName(Config config, Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToUppercaseCamel(tableName) + Const.SERVICE_NAME_SUFFIX;
    }

    public static String findServiceImplClassName(Config config, Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToUppercaseCamel(tableName) + Const.SERVICE_IMPL_NAME_SUFFIX;
    }

    public static String findDaoClassName(Config config, Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToUppercaseCamel(tableName) + Const.DAO_NAME_SUFFIX;
    }

    public static String findDaoImplClassName(Config config, Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToUppercaseCamel(tableName) + Const.DAO_IMPL_NAME_SUFFIX;
    }

    public static String findClientClassName(Config config, Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToUppercaseCamel(tableName) + Const.SERVICE_NAME_SUFFIX;
    }

    // --- class full name
    public static String findTermsClassFullName(Config config,
                                                Config.TableDetail tableDetail) {
        return config.getProjectTermsLayerPackage() + "." + findTermsClassName(config, tableDetail);
    }

    public static String findServiceClassFullName(Config config,
                                                  Config.TableDetail tableDetail) {
        return config.getProjectSvcLayerPackage() + "." + findServiceClassName(config, tableDetail);
    }

    // --- local variable name
    public static String findPojoLocalVariableName(Config config,
                                                   Config.TableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return NamingUtils.underlineToLowercaseCamel(tableName);
    }

    public static String findTermsLocalVariableName(Config config,
                                                    Config.TableDetail tableDetail) {
        String termsName = findTermsClassName(config, tableDetail);
        return NamingUtils.upperCamelToLowerCamel(termsName);
    }

    public static String findServiceLocalVariableName(Config config,
                                                    Config.TableDetail tableDetail) {
        String termsName = findServiceClassName(config, tableDetail);
        return NamingUtils.upperCamelToLowerCamel(termsName);
    }

}