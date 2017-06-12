package com.ysu.zyw.tc.webgen.gen.web;

import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;
import org.jooq.TableField;

import java.util.List;
import java.util.stream.Collectors;

public class WebMethodBodyGen {

    static String generateControllerInsertMethodBody(Config config,
                                                     Config.TableDetail tableDetail) {
        String template = "return #{service}.#{method}(#{params});";
        return template.replace("#{service}",
                NameHelper.findServiceLocalVariableName(config, tableDetail))
                .replace("#{method}", Const.SERVICE_METHOD_INSERT)
                .replace("#{params}", NameHelper.findPojoLocalVariableName(config, tableDetail));
    }

    static String generateControllerDeleteMethodBody(Config config,
                                                     Config.TableDetail tableDetail) {
        String template = "return #{service}.#{method}(#{params});";
        return template.replace("#{service}",
                NameHelper.findServiceLocalVariableName(config, tableDetail))
                .replace("#{method}", Const.SERVICE_METHOD_DELETE)
                .replace("#{params}", findPrimaryKeyArgs(config, tableDetail));
    }

    static String generateControllerUpdateMethodBody(Config config,
                                                     Config.TableDetail tableDetail) {
        String template = "return #{service}.#{method}(#{params});";
        return template.replace("#{service}",
                NameHelper.findServiceLocalVariableName(config, tableDetail))
                .replace("#{method}", Const.SERVICE_METHOD_UPDATE)
                .replace("#{params}", NameHelper.findPojoLocalVariableName(config, tableDetail));
    }

    static String generateControllerFindMethodBody(Config config,
                                                   Config.TableDetail tableDetail) {
        String template = "return #{service}.#{method}(#{params});";
        return template.replace("#{service}",
                NameHelper.findServiceLocalVariableName(config, tableDetail))
                .replace("#{method}", Const.SERVICE_METHOD_FIND)
                .replace("#{params}", findPrimaryKeyArgs(config, tableDetail));
    }

    static String generateControllerListMethodBody(Config config,
                                                   Config.TableDetail tableDetail) {
        String template = "return #{service}.#{method}(#{params});";
        return template.replace("#{service}",
                NameHelper.findServiceLocalVariableName(config, tableDetail))
                .replace("#{method}", Const.SERVICE_METHOD_LIST)
                .replace("#{params}", NameHelper.findTermsLocalVariableName(config, tableDetail));
    }

    static String generateControllerCountMethodBody(Config config,
                                                    Config.TableDetail tableDetail) {
        String template = "return #{service}.#{method}(#{params});";
        return template.replace("#{service}",
                NameHelper.findServiceLocalVariableName(config, tableDetail))
                .replace("#{method}", Const.SERVICE_METHOD_COUNT)
                .replace("#{params}", NameHelper.findTermsLocalVariableName(config, tableDetail));
    }

    private static String findPrimaryKeyArgs(Config config,
                                             Config.TableDetail tableDetail) {
        @SuppressWarnings("unchecked")
        List<TableField> fields = tableDetail.getTable().getPrimaryKey().getFields();
        return fields.stream().map(field ->
                NamingUtils.underlineToLowercaseCamel(field.getName()))
                .collect(Collectors.joining(", "));
    }
}
