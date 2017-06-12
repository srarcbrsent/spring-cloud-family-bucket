package com.ysu.zyw.tc.webgen.gen.web;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ArgDefinition;
import com.ysu.zyw.tc.webgen.definiton.MethodDefinition;
import com.ysu.zyw.tc.webgen.helper.ArgsHelper;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;
import org.springframework.web.bind.annotation.*;

public class WebMethodGen {

    static MethodDefinition generateControllerInsertMethod(Config config,
                                                           Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.CONTROLLER_METHOD_INSERT)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition
                                .builder()
                                .annotation(PostMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition
                                                .builder()
                                                .name(Const.CONST_VALUE)
                                                .value("")
                                                .build()
                                ))
                                .build())
                )
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(NameHelper.findPojoLocalVariableName(config, tableDetail))
                                .annotations(Lists.newArrayList(
                                        AnnotationDefinition
                                                .builder()
                                                .annotation(RequestBody.class.getSimpleName())
                                                .build()
                                ))
                                .build()
                ))
                .body(WebMethodBodyGen.generateControllerInsertMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateControllerDeleteMethod(Config config,
                                                           Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.CONTROLLER_METHOD_DELETE)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition
                                .builder()
                                .annotation(DeleteMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition
                                                .builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findPrimaryKeysMapping(config, tableDetail))
                                                .build()
                                ))
                                .build())
                )
                .args(ArgsHelper.findPrimaryKeyArgsDefinition(config, tableDetail))
                .body(WebMethodBodyGen.generateControllerDeleteMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateControllerUpdateMethod(Config config,
                                                           Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.CONTROLLER_METHOD_UPDATE)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition
                                .builder()
                                .annotation(PutMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition
                                                .builder()
                                                .name(Const.CONST_VALUE)
                                                .value("")
                                                .build()
                                )).build())
                )
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(tableDetail.getPojo().getSimpleName())
                                .name(NameHelper.findPojoLocalVariableName(config, tableDetail))
                                .annotations(Lists.newArrayList(
                                        AnnotationDefinition
                                                .builder()
                                                .annotation(RequestBody.class.getSimpleName())
                                                .build()
                                ))
                                .build()
                ))
                .body(WebMethodBodyGen.generateControllerUpdateMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateControllerFindMethod(Config config,
                                                         Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(tableDetail.getPojo().getSimpleName())
                .name(Const.CONTROLLER_METHOD_FIND)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition
                                .builder()
                                .annotation(GetMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition
                                                .builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findPrimaryKeysMapping(config, tableDetail))
                                                .build()
                                )).build())
                )
                .args(ArgsHelper.findPrimaryKeyArgsDefinition(config, tableDetail))
                .body(WebMethodBodyGen.generateControllerFindMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateControllerListMethod(Config config,
                                                         Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(NamingUtils.wrapListType(tableDetail.getPojo().getSimpleName()))
                .name(Const.CONTROLLER_METHOD_LIST)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition
                                .builder()
                                .annotation(GetMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition
                                                .builder()
                                                .name(Const.CONST_VALUE)
                                                .value(Const.PATH_LIST)
                                                .build()
                                )).build())
                )
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(WebMethodBodyGen.generateControllerListMethodBody(config, tableDetail))
                .build();
    }

    static MethodDefinition generateControllerCountMethod(Config config,
                                                          Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_LONG)
                .name(Const.CONTROLLER_METHOD_COUNT)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition
                                .builder()
                                .annotation(GetMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition
                                                .builder()
                                                .name(Const.CONST_VALUE)
                                                .value(Const.PATH_COUNT)
                                                .build()
                                ))
                                .build())
                )
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .body(WebMethodBodyGen.generateControllerCountMethodBody(config, tableDetail))
                .build();
    }

}