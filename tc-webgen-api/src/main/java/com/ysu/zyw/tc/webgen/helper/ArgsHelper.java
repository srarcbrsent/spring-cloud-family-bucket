package com.ysu.zyw.tc.webgen.helper;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ArgDefinition;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;
import org.jooq.TableField;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

public class ArgsHelper {
    public static List<ArgDefinition> findPrimaryKeyArgsDefinition(Config config,
                                                            Config.TableDetail tableDetail) {
        @SuppressWarnings("unchecked")
        List<TableField> fields = tableDetail.getTable().getPrimaryKey().getFields();
        return fields.stream().map(field -> {
            String type = field.getType().getSimpleName();
            String name = NamingUtils.underlineToLowercaseCamel(field.getName());

            return ArgDefinition
                    .builder()
                    .type(type)
                    .name(name)
                    .annotations(Lists.newArrayList(
                            AnnotationDefinition
                                    .builder()
                                    .annotation(PathVariable.class.getSimpleName())
                                    .fields(Lists.newArrayList(
                                            AnnotationDefinition.AnnotationFieldDefinition
                                                    .builder()
                                                    .name(Const.CONST_VALUE)
                                                    .value(name)
                                                    .build()
                                    )).build()
                    )).build();
        }).collect(Collectors.toList());
    }
}