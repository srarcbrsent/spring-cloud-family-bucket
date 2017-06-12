package com.ysu.zyw.tc.webgen.gen.web;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.builder.ClassBuilder;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ClassDefinition;
import com.ysu.zyw.tc.webgen.helper.ImportHelper;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.helper.SourcePathHelper;
import com.ysu.zyw.tc.webgen.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class WebGen {

    public static void generateController(Config config, Config.TableDetail tableDetail) {
        ClassDefinition classDefinition = ClassDefinition
                .builder()
                .pkg(config.getProjectWebLayerPackage())
                .imports(ImportHelper.findControllerImports(config, tableDetail))
                .name(NameHelper.findControllerClassName(config, tableDetail))
                .annotations(Lists.newArrayList(
                        AnnotationDefinition
                                .builder()
                                .annotation(Controller.class.getSimpleName())
                                .build(),
                        AnnotationDefinition
                                .builder()
                                .annotation(RequestMapping.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition
                                                .builder()
                                                .name(Const.CONST_VALUE)
                                                .value(WebUrlGen.findControllerMapping(config, tableDetail))
                                                .build()
                                )).build()
                ))
                .fields(Lists.newArrayList(
                        WebFieldGen.generateControllerFields(config, tableDetail)
                ))
                .methods(Lists.newArrayList(
                        WebMethodGen.generateControllerInsertMethod(config, tableDetail),
                        WebMethodGen.generateControllerDeleteMethod(config, tableDetail),
                        WebMethodGen.generateControllerUpdateMethod(config, tableDetail),
                        WebMethodGen.generateControllerFindMethod(config, tableDetail),
                        WebMethodGen.generateControllerListMethod(config, tableDetail),
                        WebMethodGen.generateControllerCountMethod(config, tableDetail)
                )).build();

        String classStr = ClassBuilder.build(classDefinition);
        FileUtils.write(config, SourcePathHelper.findWebLayerSourcePath(config), NameHelper.findControllerClassName(config, tableDetail), classStr);
    }

}