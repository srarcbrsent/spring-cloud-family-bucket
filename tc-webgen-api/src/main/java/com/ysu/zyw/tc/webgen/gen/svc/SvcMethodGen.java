package com.ysu.zyw.tc.webgen.gen.svc;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ArgDefinition;
import com.ysu.zyw.tc.webgen.definiton.MethodDefinition;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;
import org.jooq.TableField;

import java.util.List;
import java.util.stream.Collectors;

public class SvcMethodGen {

    static MethodDefinition generateServiceInsertMethod(Config config,
                                                        Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.SERVICE_METHOD_INSERT)
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(NameHelper.findPojoLocalVariableName(config, tableDetail))
                                .build()
                ))
                .build();
    }

    static MethodDefinition generateServiceDeleteMethod(Config config,
                                                        Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.SERVICE_METHOD_DELETE)
                .args(findPrimaryKeyArgsDefinition(config, tableDetail))
                .build();
    }

    static MethodDefinition generateServiceUpdateMethod(Config config,
                                                        Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.SERVICE_METHOD_UPDATE)
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(NameHelper.findPojoLocalVariableName(config, tableDetail))
                                .build()
                ))
                .build();
    }

    static MethodDefinition generateServiceFindMethod(Config config,
                                                      Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(tableDetail.getPojo().getSimpleName())
                .name(Const.SERVICE_METHOD_FIND)
                .args(findPrimaryKeyArgsDefinition(config, tableDetail))
                .build();
    }

    static MethodDefinition generateServiceListMethod(Config config,
                                                      Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(NamingUtils.wrapListType(tableDetail.getPojo().getSimpleName()))
                .name(Const.SERVICE_METHOD_LIST)
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .build();
    }

    static MethodDefinition generateServiceCountMethod(Config config,
                                                       Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_LONG)
                .name(Const.SERVICE_METHOD_COUNT)
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .build();
    }

    static MethodDefinition generateServiceImplInsertMethod(Config config,
                                                            Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.SERVICE_METHOD_INSERT)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Override.class.getSimpleName())
                                .build()
                ))
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(NameHelper.findPojoLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(SvcMethodBodyGen.generateServiceInsertMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateServiceImplDeleteMethod(Config config,
                                                            Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.SERVICE_METHOD_DELETE)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Override.class.getSimpleName())
                                .build()
                ))
                .args(findPrimaryKeyArgsDefinition(config, tableDetail))
                .body(SvcMethodBodyGen.generateServiceDeleteMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateServiceImplUpdateMethod(Config config,
                                                            Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.SERVICE_METHOD_UPDATE)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Override.class.getSimpleName())
                                .build()
                ))
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(NameHelper.findPojoLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(SvcMethodBodyGen.generateServiceUpdateMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateServiceImplFindMethod(Config config,
                                                          Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(tableDetail.getPojo().getSimpleName())
                .name(Const.SERVICE_METHOD_FIND)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Override.class.getSimpleName())
                                .build()
                ))
                .args(findPrimaryKeyArgsDefinition(config, tableDetail))
                .body(SvcMethodBodyGen.generateServiceFindMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateServiceImplListMethod(Config config,
                                                          Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(NamingUtils.wrapListType(tableDetail.getPojo().getSimpleName()))
                .name(Const.SERVICE_METHOD_LIST)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Override.class.getSimpleName())
                                .build()
                ))
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(SvcMethodBodyGen.generateServiceListMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateServiceImplCountMethod(Config config,
                                                           Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_LONG)
                .name(Const.SERVICE_METHOD_COUNT)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Override.class.getSimpleName())
                                .build()
                ))
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(SvcMethodBodyGen.generateServiceCountMethodBody(config, tableDetail))
                .build();
    }

    private static List<ArgDefinition> findPrimaryKeyArgsDefinition(Config config,
                                                                    Config.TableDetail tableDetail) {
        @SuppressWarnings("unchecked")
        List<TableField> fields = tableDetail.getTable().getPrimaryKey().getFields();
        return fields.stream().map(field -> {
            String type = field.getType().getSimpleName();
            String name = NamingUtils.underlineToLowercaseCamel(field.getName());

            return ArgDefinition.builder()
                    .type(type)
                    .name(name)
                    .build();
        }).collect(Collectors.toList());
    }
}