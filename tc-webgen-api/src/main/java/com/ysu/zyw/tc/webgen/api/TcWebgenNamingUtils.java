package com.ysu.zyw.tc.webgen.api;

import com.ysu.zyw.tc.webgen.api.constant.TcWebgenConst;
import com.ysu.zyw.tc.webgen.api.utils.TcNameUtils;

import java.util.Objects;

public class TcWebgenNamingUtils {

    static String findTermsName(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return TcNameUtils.underlineToUppercaseCamel(tableName) + TcWebgenConst.TERMS_NAME_SUFFIX;
    }

    static String findControllerName(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return TcNameUtils.underlineToUppercaseCamel(tableName) + TcWebgenConst.CONTROLLER_NAME_SUFFIX;
    }

    static String findServiceName(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return TcNameUtils.underlineToUppercaseCamel(tableName) + TcWebgenConst.SERVICE_NAME_SUFFIX;
    }

    static String findServiceImplName(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return TcNameUtils.underlineToUppercaseCamel(tableName) + TcWebgenConst.SERVICE_IMPL_NAME_SUFFIX;
    }

    static String findDaoName(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return TcNameUtils.underlineToUppercaseCamel(tableName) + TcWebgenConst.DAO_NAME_SUFFIX;
    }

    static String findDaoImplName(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return TcNameUtils.underlineToUppercaseCamel(tableName) + TcWebgenConst.DAO_IMPL_NAME_SUFFIX;
    }

    static String findSimpleTableName(TcWebgenConfig config,
                                      TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = tableDetail.getTable().getName();
        if (Objects.nonNull(config.getTableNamePostProcessor())) {
            tableName = config.getTableNamePostProcessor().apply(tableName);
        }
        return tableName;
    }

    static String findPojoLocalVariableName(TcWebgenConfig config,
                                            TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = findSimpleTableName(config, tableDetail);
        return TcNameUtils.underlineToLowercaseCamel(tableName);
    }

    static String findTermsLocalVariableName(TcWebgenConfig config,
                                             TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String termsName = findTermsName(config, tableDetail);
        return TcNameUtils.upperCamelToLowerCamel(termsName);
    }

    static String findTermsFullName(TcWebgenConfig config,
                                    TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        return config.getProjectTermsLayerPackage() + "." + findTermsName(config, tableDetail);
    }
}