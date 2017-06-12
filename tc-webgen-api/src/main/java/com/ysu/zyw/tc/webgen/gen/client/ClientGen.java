package com.ysu.zyw.tc.webgen.gen.client;

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
import org.springframework.cloud.netflix.feign.FeignClient;

public class ClientGen {

    public static void generateClient(Config config, Config.TableDetail tableDetail) {
        ClassDefinition classDefinition = ClassDefinition
                .builder()
                .interfaze(true)
                .pkg(config.getProjectClientLayerPackage())
                .imports(ImportHelper.findClientImports(config, tableDetail))
                .name(NameHelper.findClientClassName(config, tableDetail))
                .annotations(Lists.newArrayList(
                        AnnotationDefinition.builder()
                                .annotation(FeignClient.class.getSimpleName())
                                .fields(Lists.newArrayList(
                                        AnnotationDefinition.AnnotationFieldDefinition.builder()
                                                .name(Const.CONST_VALUE)
                                                .value(config.getProjectClientModuleName())
                                                .build()
                                )).build()
                ))
                .fields(Lists.newArrayList())
                .methods(Lists.newArrayList(
                        ClientMethodGen.generateClientInsertMethod(config, tableDetail),
                        ClientMethodGen.generateClientDeleteMethod(config, tableDetail),
                        ClientMethodGen.generateClientUpdateMethod(config, tableDetail),
                        ClientMethodGen.generateClientFindMethod(config, tableDetail),
                        ClientMethodGen.generateClientListMethod(config, tableDetail),
                        ClientMethodGen.generateClientCountMethod(config, tableDetail)
                )).build();

        String classStr = ClassBuilder.build(classDefinition);

        FileUtils.write(
                config,
                SourcePathHelper.findClientLayerSourcePath(config),
                NameHelper.findClientClassName(config, tableDetail),
                classStr);
    }

}