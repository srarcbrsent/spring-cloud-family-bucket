package com.ysu.zyw.tc.webgen.gen.client;

import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.TypeExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ArgDefinition;
import com.ysu.zyw.tc.webgen.definiton.MethodDefinition;
import com.ysu.zyw.tc.webgen.gen.web.WebUrlGen;
import com.ysu.zyw.tc.webgen.helper.ArgsHelper;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;
import org.jooq.TableField;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMethodGen {

    static MethodDefinition generateClientInsertMethod(Config config,
                                                       Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.CONTROLLER_METHOD_INSERT)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findControllerMapping(config, tableDetail) + "")
                                                .build(),
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_METHOD)
                                                .expression(new FieldAccessExpr(
                                                        new TypeExpr(
                                                                null,
                                                                new ClassOrInterfaceType(
                                                                        RequestMethod.class.getSimpleName())),
                                                        Const.HTTP_METHOD_POST))
                                                .build()
                                ))
                                .build()
                ))
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
                .build();
    }

    static MethodDefinition generateClientDeleteMethod(Config config,
                                                       Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.CONTROLLER_METHOD_DELETE)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findControllerMapping(config, tableDetail) + WebUrlGen.findPrimaryKeysMapping(config, tableDetail))
                                                .build(),
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_METHOD)
                                                .expression(new FieldAccessExpr(
                                                        new TypeExpr(
                                                                null,
                                                                new ClassOrInterfaceType(
                                                                        RequestMethod.class.getSimpleName())),
                                                        Const.HTTP_METHOD_DELETE))
                                                .build()
                                ))
                                .build()
                ))
                .args(ArgsHelper.findPrimaryKeyArgsDefinition(config, tableDetail))
                .build();
    }

    static MethodDefinition generateClientUpdateMethod(Config config,
                                                       Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_INTEGER)
                .name(Const.CONTROLLER_METHOD_UPDATE)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findControllerMapping(config, tableDetail) + "")
                                                .build(),
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_METHOD)
                                                .expression(new FieldAccessExpr(
                                                        new TypeExpr(
                                                                null,
                                                                new ClassOrInterfaceType(
                                                                        RequestMethod.class.getSimpleName())),
                                                        Const.HTTP_METHOD_PUT))
                                                .build()
                                ))
                                .build()
                ))
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
                .build();
    }

    static MethodDefinition generateClientFindMethod(Config config,
                                                     Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(tableDetail.getPojo().getSimpleName())
                .name(Const.CONTROLLER_METHOD_FIND)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findControllerMapping(config, tableDetail) + WebUrlGen.findPrimaryKeysMapping(config, tableDetail))
                                                .build(),
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_METHOD)
                                                .expression(new FieldAccessExpr(
                                                        new TypeExpr(
                                                                null,
                                                                new ClassOrInterfaceType(
                                                                        RequestMethod.class.getSimpleName())),
                                                        Const.HTTP_METHOD_GET))
                                                .build()
                                ))
                                .build()
                ))
                .args(ArgsHelper.findPrimaryKeyArgsDefinition(config, tableDetail))
                .build();
    }

    static MethodDefinition generateClientListMethod(Config config,
                                                     Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(NamingUtils.wrapListType(tableDetail.getPojo().getSimpleName()))
                .name(Const.CONTROLLER_METHOD_LIST)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findControllerMapping(config, tableDetail) + Const.PATH_LIST)
                                                .build(),
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_METHOD)
                                                .expression(new FieldAccessExpr(
                                                        new TypeExpr(
                                                                null,
                                                                new ClassOrInterfaceType(
                                                                        RequestMethod.class.getSimpleName())),
                                                        Const.HTTP_METHOD_GET))
                                                .build()
                                ))
                                .build()
                ))
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .build();
    }

    static MethodDefinition generateClientCountMethod(Config config,
                                                      Config.TableDetail tableDetail) {
        return MethodDefinition
                .builder()
                .returnValueType(Const.TYPE_LONG)
                .name(Const.CONTROLLER_METHOD_COUNT)
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findControllerMapping(config, tableDetail) + Const.PATH_COUNT)
                                                .build(),
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_METHOD)
                                                .expression(new FieldAccessExpr(
                                                        new TypeExpr(
                                                                null,
                                                                new ClassOrInterfaceType(
                                                                        RequestMethod.class.getSimpleName())),
                                                        Const.HTTP_METHOD_GET))
                                                .build()
                                ))
                                .build()
                ))
                .args(Lists.newArrayList(
                        ArgDefinition
                                .builder()
                                .type(NameHelper.findTermsClassName(config, tableDetail))
                                .name(NameHelper.findTermsLocalVariableName(config, tableDetail))
                                .build()
                ))
                .build();
    }

}