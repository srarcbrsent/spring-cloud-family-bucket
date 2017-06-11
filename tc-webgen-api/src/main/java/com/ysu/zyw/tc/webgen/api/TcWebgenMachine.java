package com.ysu.zyw.tc.webgen.api;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ysu.zyw.tc.webgen.api.builder.TcClassBuilder;
import com.ysu.zyw.tc.webgen.api.definitons.*;
import com.ysu.zyw.tc.webgen.api.handlers.*;
import lombok.Data;
import org.jooq.Field;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


public class TcWebgenMachine {

    private static final Map<Class, TcAbstractWebgenTypeHandler> typeHandlers = Maps.newHashMap(
            ImmutableMap.<Class, TcAbstractWebgenTypeHandler>builder()
                    .put(Integer.class, new TcWebgenIntegerTypeHandler())
                    .put(Long.class, new TcWebgenLongTypeHandler())
                    .put(Boolean.class, new TcWebgenBooleanTypeHandler())
                    .put(BigDecimal.class, new TcWebgenBigDecimalTypeHandler())
                    .put(String.class, new TcWebgenStringTypeHandler())
                    .put(Timestamp.class, new TcWebgenTimestampTypeHandler())
                    .build()
    );

    public static void gen(TcWebgenConfig config) {
        config.getTableDetails().forEach(tableDetail -> {
            // gen terms
            generateQueryTerms(config, tableDetail);
            // gen web
            TcWebgenControllerUtils.generateController(config, tableDetail);
            // gen svc
            // gen svcimpl
            // gen dao
            // gen daoimpl
        });
    }

    // --- 层次生成组
    private static void generateQueryTerms(TcWebgenConfig config, TcWebgenConfig.TcWebgenTableDetail tableDetail) {
        Field[] fields = tableDetail.getTable().fields();
        List<TcFieldDefinition> fieldDefinitions = Lists.newArrayList();
        Arrays.stream(fields).forEach(field -> {
            if (!typeHandlers.containsKey(field.getType())) {
                throw new RuntimeException("no type handler found for [" + field.getType() + "]");
            }
            TcAbstractWebgenTypeHandler typeHandler = typeHandlers.get(field.getType());
            fieldDefinitions.addAll(typeHandler.generate(field));
        });

        TcClassDefinition classDefinition = TcClassDefinition.builder()
                .pkg(config.getProjectTermsLayerPackage())
                .imports(TcWebgenImportUtils.findTermsFieldImports(fields))
                .name(TcWebgenNamingUtils.findTermsName(config, tableDetail))
                .annotations(Lists.newArrayList(
                        TcAnnotationDefinition.builder()
                                .annotation(Data.class.getSimpleName()).build()
                ))
                .fields(fieldDefinitions)
                .build();

        String classStr = TcClassBuilder.build(classDefinition);

        TcWebgenIOUtils.write(config, TcWebgenSourcePathUtils.findTermsLayerSourcePath(config), TcWebgenNamingUtils.findTermsName(config, tableDetail), classStr);
    }

}
