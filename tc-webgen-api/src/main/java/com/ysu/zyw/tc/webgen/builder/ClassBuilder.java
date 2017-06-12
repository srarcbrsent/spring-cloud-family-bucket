package com.ysu.zyw.tc.webgen.builder;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ClassDefinition;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ClassBuilder {

    public static String build(ClassDefinition classDefinition) {
        CompilationUnit compilationUnit = new CompilationUnit();

        // set the package
        compilationUnit.setPackageDeclaration(new PackageDeclaration(Name.parse(classDefinition.getPkg())));

        // add type declaration
        ClassOrInterfaceDeclaration classDeclaration;
        if (classDefinition.isInterfaze()) {
            classDeclaration = compilationUnit.addInterface(classDefinition.getName());
        } else {
            classDeclaration = compilationUnit.addClass(classDefinition.getName());
        }

        // add implements interface
        if (Objects.nonNull(classDefinition.getImplementsInterface())) {
            classDeclaration.addImplements(classDefinition.getImplementsInterface());
        }

        // add imports
        doIfNonNull(classDefinition.getImports(), imports -> {
            Sets.newHashSet(imports).forEach(compilationUnit::addImport);
        });

        // add class field
        doIfNonNull(classDefinition.getFields(), fields -> {
            fields.forEach(field -> {
                ClassOrInterfaceType type = new ClassOrInterfaceType(field.getType());
                // add field annotation
                doIfNonNull(field.getAnnotations(), annotations -> {
                    annotations.forEach(tcAnnotationDefinition -> {
                        type.addAnnotation(new NormalAnnotationExpr(
                                Name.parse(tcAnnotationDefinition.getAnnotation()),
                                NodeList.nodeList(
                                        // annotation fields
                                        mapIfNonNull(tcAnnotationDefinition.getFields(),
                                                annotationFields -> annotationFields.stream().map(annotationField ->
                                                        new MemberValuePair(annotationField.getName(),
                                                                findAnnotationValue(annotationField)))
                                                        .collect(Collectors.toList()))
                                )
                        ));
                    });
                });
                classDeclaration.addField(type, field.getName(), Modifier.PRIVATE);
            });
        });

        // add class annotations
        doIfNonNull(classDefinition.getAnnotations(), annotations -> {
            annotations.forEach(tcAnnotationDefinition -> {
                classDeclaration.addAnnotation(new NormalAnnotationExpr(
                        Name.parse(tcAnnotationDefinition.getAnnotation()),
                        NodeList.nodeList(
                                // annotation fields
                                mapIfNonNull(tcAnnotationDefinition.getFields(),
                                        fields -> fields.stream().map(field ->
                                                new MemberValuePair(field.getName(),
                                                        findAnnotationValue(field)))
                                                .collect(Collectors.toList()))
                        )
                ));
            });
        });

        // add methods
        doIfNonNull(classDefinition.getMethods(), methods -> {
            methods.forEach(tcMethodDefinition -> {
                // single method
                EnumSet<Modifier> modifiers = EnumSet.noneOf(Modifier.class);
                if (!classDefinition.isInterfaze()) {
                    modifiers = EnumSet.of(Modifier.PUBLIC);
                }

                MethodDeclaration method = new MethodDeclaration(
                        modifiers,
                        new ClassOrInterfaceType(tcMethodDefinition.getReturnValueType()),
                        tcMethodDefinition.getName());

                // add method annotations
                doIfNonNull(tcMethodDefinition.getAnnotations(), annotations -> {
                    annotations.forEach(tcAnnotationDefinition -> {
                        method.addAnnotation(new NormalAnnotationExpr(
                                Name.parse(tcAnnotationDefinition.getAnnotation()),
                                NodeList.nodeList(
                                        mapIfNonNull(tcAnnotationDefinition.getFields(), fields ->
                                                fields.stream().map(field ->
                                                        new MemberValuePair(
                                                                field.getName(),
                                                                findAnnotationValue(field)))
                                                        .collect(Collectors.toList()))
                                )
                        ));
                    });
                });

                // add method params
                doIfNonNull(tcMethodDefinition.getArgs(), args -> {
                    args.forEach(tcArgDefinition -> {
                        // single param
                        Parameter param = new Parameter(
                                new ClassOrInterfaceType(tcArgDefinition.getType()),
                                tcArgDefinition.getName());

                        // add param annotations
                        doIfNonNull(tcArgDefinition.getAnnotations(), annotations -> {
                            annotations.forEach(tcAnnotationDefinition -> {
                                param.addAnnotation(new NormalAnnotationExpr(
                                        Name.parse(tcAnnotationDefinition.getAnnotation()),
                                        NodeList.nodeList(
                                                mapIfNonNull(tcAnnotationDefinition.getFields(), fields ->
                                                        fields.stream().map(field ->
                                                                new MemberValuePair(
                                                                        field.getName(),
                                                                        findAnnotationValue(field)))
                                                                .collect(Collectors.toList()))
                                        )
                                ));
                            });
                        });

                        method.addParameter(param);
                    });
                });

                if (!classDefinition.isInterfaze()) {
                    // method body
                    if (Objects.nonNull(tcMethodDefinition.getBody())) {
                        BlockStmt block = new BlockStmt();
                        block.addStatement(tcMethodDefinition.getBody());
                        method.setBody(block);
                    }
                } else {
                    method.setBody(null);
                }

                classDeclaration.addMember(method);
            });
        });

        return compilationUnit.toString();
    }

    private static <T> void doIfNonNull(Collection<T> t, Consumer<Collection<T>> consumer) {
        if (Objects.nonNull(t)) {
            consumer.accept(t);
        }
    }

    private static <T, R> List<R> mapIfNonNull(List<T> t, Function<List<T>, List<R>> fun) {
        if (Objects.nonNull(t)) {
            return fun.apply(t);
        }
        return Lists.newArrayList();
    }

    private static Expression findAnnotationValue(AnnotationDefinition.AnnotationFieldDefinition fieldDefinition) {
        if (Objects.nonNull(fieldDefinition.getExpression())) {
            return fieldDefinition.getExpression();
        } else {
            return new StringLiteralExpr(fieldDefinition.getValue());
        }
    }

}
