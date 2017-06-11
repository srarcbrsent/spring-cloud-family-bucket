package com.ysu.zyw.tc.webgen.api;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.api.builder.TcClassBuilder;
import com.ysu.zyw.tc.webgen.api.constant.TcWebgenConst;
import com.ysu.zyw.tc.webgen.api.definitons.TcAnnotationDefinition;
import com.ysu.zyw.tc.webgen.api.definitons.TcArgDefinition;
import com.ysu.zyw.tc.webgen.api.definitons.TcClassDefinition;
import com.ysu.zyw.tc.webgen.api.definitons.TcMethodDefinition;
import com.ysu.zyw.tc.webgen.api.utils.TcNameUtils;
import org.jooq.Field;
import org.jooq.TableField;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TcWebgenControllerUtils {
    static void generateController(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        TcClassDefinition classDefinition = TcClassDefinition
                .builder()
                .pkg(config.getProjectWebLayerPackage())
                .imports(Lists.newArrayList(
                        // - class
                        Controller.class.getTypeName(),
                        RequestMapping.class.getTypeName(),
                        // - class annotation
                        PathVariable.class.getTypeName(),
                        // - method
                        PostMapping.class.getTypeName(),
                        DeleteMapping.class.getTypeName(),
                        PutMapping.class.getTypeName(),
                        GetMapping.class.getTypeName(),
                        // - pojo
                        tableDetail.getPojo().getTypeName(),
                        // - param annotation
                        RequestBody.class.getTypeName(),
                        // - list
                        List.class.getTypeName(),
                        // - terms
                        TcWebgenNamingUtils.findTermsFullName(config, tableDetail)
                ))
                .name(TcWebgenNamingUtils.findControllerName(config, tableDetail))
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition
                                .builder()
                                .annotation(Controller.class.getSimpleName())
                                .build(),
                        TcAnnotationDefinition
                                .builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                .builder()
                                                .name("value")
                                                .value(findControllerMapping(config, tableDetail))
                                                .build()
                                )).build()
                ))
                .fields(Lists.newArrayList())
                .methods(Lists.newArrayList(
                        generateControllerInsertMethod(config, tableDetail),
                        generateControllerDeleteMethod(config, tableDetail),
                        generateControllerUpdateMethod(config, tableDetail),
                        generateControllerFindMethod(config, tableDetail),
                        generateControllerListMethod(config, tableDetail),
                        generateControllerCountMethod(config, tableDetail)
                )).build();

        String classStr = TcClassBuilder.build(classDefinition);
        TcWebgenIOUtils.write(config, TcWebgenSourcePathUtils.findWebLayerSourcePath(config), TcWebgenNamingUtils.findControllerName(config, tableDetail), classStr);
    }// --- Controller方法生成组

    static TcMethodDefinition generateControllerInsertMethod(TcWebgenConfig config,
                                                             TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        return TcMethodDefinition
                .builder()
                .returnValueType(TcWebgenConst.RETURN_VALUE_TYPE_INTEGER)
                .name(TcWebgenConst.CONTROLLER_METHOD_INSERT)
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition
                                .builder()
                                .annotation(PostMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                .builder()
                                                .name("value")
                                                .value("")
                                                .build()
                                ))
                                .build())
                )
                .args(Lists.newArrayList(
                        TcArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(TcWebgenNamingUtils.findPojoLocalVariableName(config, tableDetail))
                                .annotations(Lists.newArrayList(
                                        TcAnnotationDefinition
                                                .builder()
                                                .annotation(RequestBody.class.getSimpleName())
                                                .build()
                                ))
                                .build()
                ))
                .body(TcWebgenControllerMethodBodyUtils.generateControllerInsertMethodBody(config, tableDetail))
                .build();
    }

    static TcMethodDefinition generateControllerDeleteMethod(TcWebgenConfig config,
                                                             TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        return TcMethodDefinition
                .builder()
                .returnValueType(TcWebgenConst.RETURN_VALUE_TYPE_INTEGER)
                .name(TcWebgenConst.CONTROLLER_METHOD_DELETE)
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition
                                .builder()
                                .annotation(DeleteMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                .builder()
                                                .name("value")
                                                .value(findPrimaryKeyMapping(config, tableDetail))
                                                .build()
                                ))
                                .build())
                )
                .args(findPrimaryKeyArgsDefinition(config, tableDetail))
                .body(TcWebgenControllerMethodBodyUtils.generateControllerDeleteMethodBody(config, tableDetail))
                .build();
    }

    static TcMethodDefinition generateControllerUpdateMethod(TcWebgenConfig config,
                                                             TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        return TcMethodDefinition
                .builder()
                .returnValueType(TcWebgenConst.RETURN_VALUE_TYPE_INTEGER)
                .name(TcWebgenConst.CONTROLLER_METHOD_UPDATE)
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition
                                .builder()
                                .annotation(PutMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                .builder()
                                                .name("value")
                                                .value("")
                                                .build()
                                )).build())
                )
                .args(Lists.newArrayList(
                        TcArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(TcWebgenNamingUtils.findPojoLocalVariableName(config, tableDetail))
                                .annotations(Lists.newArrayList(
                                        TcAnnotationDefinition
                                                .builder()
                                                .annotation(RequestBody.class.getSimpleName())
                                                .build()
                                ))
                                .build()
                ))
                .body(TcWebgenControllerMethodBodyUtils.generateControllerUpdateMethodBody(config, tableDetail))
                .build();
    }

    static TcMethodDefinition generateControllerFindMethod(TcWebgenConfig config,
                                                           TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        return TcMethodDefinition
                .builder()
                .returnValueType(tableDetail.getPojo().getSimpleName())
                .name(TcWebgenConst.CONTROLLER_METHOD_FIND)
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition
                                .builder()
                                .annotation(GetMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                .builder()
                                                .name("value")
                                                .value(findPrimaryKeyMapping(config, tableDetail))
                                                .build()
                                )).build())
                )
                .args(findPrimaryKeyArgsDefinition(config, tableDetail))
                .body(TcWebgenControllerMethodBodyUtils.generateControllerFindMethodBody(config, tableDetail))
                .build();
    }

    static TcMethodDefinition generateControllerListMethod(TcWebgenConfig config,
                                                           TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        // TODO
        return TcMethodDefinition
                .builder()
                .returnValueType(TcNameUtils.wrapListType(tableDetail.getPojo().getSimpleName()))
                .name(TcWebgenConst.CONTROLLER_METHOD_LIST)
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition
                                .builder()
                                .annotation(GetMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                .builder()
                                                .name("value")
                                                .value(TcWebgenConst.PATH_LIST)
                                                .build()
                                )).build())
                )
                .args(Lists.newArrayList(
                        TcArgDefinition
                                .builder()
                                .type(TcWebgenNamingUtils.findTermsName(config, tableDetail))
                                .name(TcWebgenNamingUtils.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(TcWebgenControllerMethodBodyUtils.generateControllerListMethodBody(config, tableDetail))
                .build();
    }

    static TcMethodDefinition generateControllerCountMethod(TcWebgenConfig config,
                                                            TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        // TODO
        return TcMethodDefinition
                .builder()
                .returnValueType(TcWebgenConst.RETURN_VALUE_TYPE_LONG)
                .name(TcWebgenConst.CONTROLLER_METHOD_COUNT)
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition
                                .builder()
                                .annotation(GetMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                .builder()
                                                .name("value")
                                                .value(TcWebgenConst.PATH_COUNT)
                                                .build()
                                ))
                                .build())
                )
                .args(Lists.newArrayList(
                        TcArgDefinition
                                .builder()
                                .type(TcWebgenNamingUtils.findTermsName(config, tableDetail))
                                .name(TcWebgenNamingUtils.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(TcWebgenControllerMethodBodyUtils.generateControllerCountMethodBody(config, tableDetail))
                .build();
    }

    private static String findControllerMapping(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        String tableName = TcWebgenNamingUtils.findSimpleTableName(config, tableDetail);
        String lowercaseTableName = TcNameUtils.underlineToLowercase(tableName);
        lowercaseTableName = "/" + lowercaseTableName;
        if (Objects.nonNull(config.getControllerMappingPostProcessor())) {
            return config.getControllerMappingPostProcessor().apply(lowercaseTableName);
        } else {
            return lowercaseTableName;
        }
    }

    private static String findPrimaryKeyMapping(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        List<String> primaryKeys = findPrimaryKeySimpleNameOrdered(config, tableDetail);
        primaryKeys = primaryKeys.stream().map(TcNameUtils::underlineToLowercaseCamel).collect(Collectors.toList());
        return wrapPathVariables(primaryKeys);
    }

    private static List<TcArgDefinition> findPrimaryKeyArgsDefinition(TcWebgenConfig config,
                                                                      TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        @SuppressWarnings("unchecked")
        List<TableField> fields = tableDetail.getTable().getPrimaryKey().getFields();
        return fields.stream().map(field -> {
            String type = field.getType().getSimpleName();
            String name = TcNameUtils.underlineToLowercaseCamel(field.getName());

            return TcArgDefinition
                    .builder()
                    .type(type)
                    .name(name)
                    .annotations(Lists.newArrayList(
                            TcAnnotationDefinition
                                    .builder()
                                    .annotation(PathVariable.class.getSimpleName())
                                    .fields(Lists.newArrayList(
                                            TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                    .builder()
                                                    .name("value")
                                                    .value(name)
                                                    .build()
                                    )).build()
                    )).build();
        }).collect(Collectors.toList());
    }

    private static List<String> findPrimaryKeySimpleNameOrdered(TcWebgenConfig config,
                                                                TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        @SuppressWarnings("unchecked")
        List<TableField> fields = tableDetail.getTable().getPrimaryKey().getFields();
        return fields.stream().map(Field::getName).collect(Collectors.toList());
    }

    private static String wrapPathVariables(List<String> pathVars) {
        return "/" + pathVars.stream().map(pathVar ->
                "{" + pathVar + "}").collect(Collectors.joining("/"));
    }

}