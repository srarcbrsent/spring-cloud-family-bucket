package com.ysu.zyw.tc.webgen.gen.terms;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.builder.ClassBuilder;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ClassDefinition;
import com.ysu.zyw.tc.webgen.definiton.FieldDefinition;
import com.ysu.zyw.tc.webgen.handler.TypeHandler;
import com.ysu.zyw.tc.webgen.handler.TypeHandlerHolder;
import com.ysu.zyw.tc.webgen.helper.ImportHelper;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.helper.SourcePathHelper;
import com.ysu.zyw.tc.webgen.utils.FileUtils;
import lombok.Data;
import org.jooq.Field;

import java.util.Arrays;
import java.util.List;

public class TermsGen {

    public static void generateQueryTerms(Config config, Config.TableDetail tableDetail) {
        Field[] fields = tableDetail.getTable().fields();
        List<FieldDefinition> fieldDefinitions = Lists.newArrayList();
        Arrays.stream(fields).forEach(field -> {
            if (!TypeHandlerHolder.typeHandlers.containsKey(field.getType())) {
                throw new RuntimeException("no type handler found for [" + field.getType() + "]");
            }
            TypeHandler typeHandler = TypeHandlerHolder.typeHandlers.get(field.getType());
            fieldDefinitions.addAll(typeHandler.generate(field));
        });

        fieldDefinitions.add(
                FieldDefinition.builder()
                        .type(Const.TYPE_INTEGER)
                        .name(Const.TERMS_PAGE)
                        .build()
        );
        fieldDefinitions.add(
                FieldDefinition.builder()
                        .type(Const.TYPE_INTEGER)
                        .name(Const.TERMS_PAGE_SIZE)
                        .build()
        );

        ClassDefinition classDefinition = ClassDefinition.builder()
                .pkg(config.getProjectTermsLayerPackage())
                .imports(ImportHelper.findTermsImports(fields))
                .name(NameHelper.findTermsClassName(config, tableDetail))
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Data.class.getSimpleName()).build()
                ))
                .fields(fieldDefinitions)
                .build();

        String classStr = ClassBuilder.build(classDefinition);

        FileUtils.write(
                config,
                SourcePathHelper.findTermsLayerSourcePath(config),
                NameHelper.findTermsClassName(config, tableDetail),
                classStr);
    }
}