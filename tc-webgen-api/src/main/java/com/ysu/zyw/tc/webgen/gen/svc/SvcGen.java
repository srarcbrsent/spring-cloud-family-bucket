package com.ysu.zyw.tc.webgen.gen.svc;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.builder.ClassBuilder;
import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.definiton.AnnotationDefinition;
import com.ysu.zyw.tc.webgen.definiton.ClassDefinition;
import com.ysu.zyw.tc.webgen.helper.ImportHelper;
import com.ysu.zyw.tc.webgen.helper.NameHelper;
import com.ysu.zyw.tc.webgen.helper.SourcePathHelper;
import com.ysu.zyw.tc.webgen.utils.FileUtils;
import org.springframework.stereotype.Service;

public class SvcGen {

    public static void generateService(Config config, Config.TableDetail tableDetail) {
        ClassDefinition classDefinition = ClassDefinition
                .builder()
                .interfaze(true)
                .pkg(config.getProjectSvcLayerPackage())
                .imports(ImportHelper.findServiceImports(config, tableDetail))
                .name(NameHelper.findServiceClassName(config, tableDetail))
                .fields(Lists.newArrayList())
                .methods(Lists.newArrayList(
                        SvcMethodGen.generateServiceInsertMethod(config, tableDetail),
                        SvcMethodGen.generateServiceDeleteMethod(config, tableDetail),
                        SvcMethodGen.generateServiceUpdateMethod(config, tableDetail),
                        SvcMethodGen.generateServiceFindMethod(config, tableDetail),
                        SvcMethodGen.generateServiceListMethod(config, tableDetail),
                        SvcMethodGen.generateServiceCountMethod(config, tableDetail)
                )).build();

        String classStr = ClassBuilder.build(classDefinition);

        FileUtils.write(
                config,
                SourcePathHelper.findSvcLayerSourcePath(config),
                NameHelper.findServiceClassName(config, tableDetail),
                classStr);
    }

    public static void generateServiceImpl(Config config, Config.TableDetail tableDetail) {
        ClassDefinition classDefinition = ClassDefinition
                .builder()
                .pkg(config.getProjectSvcImplLayerPackage())
                .imports(ImportHelper.findServiceImplImports(config, tableDetail))
                .name(NameHelper.findServiceImplClassName(config, tableDetail))
                .implementsInterface(NameHelper.findServiceClassName(config, tableDetail))
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(Service.class.getSimpleName())
                                .build()
                ))
                .fields(Lists.newArrayList())
                .methods(Lists.newArrayList(
                        SvcMethodGen.generateServiceImplInsertMethod(config, tableDetail),
                        SvcMethodGen.generateServiceImplDeleteMethod(config, tableDetail),
                        SvcMethodGen.generateServiceImplUpdateMethod(config, tableDetail),
                        SvcMethodGen.generateServiceImplFindMethod(config, tableDetail),
                        SvcMethodGen.generateServiceImplListMethod(config, tableDetail),
                        SvcMethodGen.generateServiceImplCountMethod(config, tableDetail)
                )).build();

        String classStr = ClassBuilder.build(classDefinition);

        FileUtils.write(
                config,
                SourcePathHelper.findSvcImplLayerSourcePath(config),
                NameHelper.findServiceImplClassName(config, tableDetail),
                classStr);
    }

}