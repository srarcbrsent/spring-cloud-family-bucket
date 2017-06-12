package com.ysu.zyw.tc.webgen.gen.web;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.FieldDefinition;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;

import javax.annotation.Resource;
import java.util.List;

public class WebFieldGen {

    static List<FieldDefinition> generateControllerFields(Config config,
                                                          Config.TableDetail tableDetail) {
        return Lists.newArrayList(
                FieldDefinition.builder()
                        .type(NameHelper.findServiceClassName(config, tableDetail))
                        .name(NameHelper.findServiceLocalVariableName(config, tableDetail))
                        .annotations(Lists.newArrayList(
                                AnnotationDefinition.builder()
                                        .annotation(Resource.class.getSimpleName())
                                        .build()
                        ))
                        .build()
        );
    }

}
