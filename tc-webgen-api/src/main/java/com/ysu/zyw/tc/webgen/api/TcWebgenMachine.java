package com.ysu.zyw.tc.webgen.api;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.api.builder.TcClassBuilder;
import com.ysu.zyw.tc.webgen.api.definitons.TcAnnotationDefinition;
import com.ysu.zyw.tc.webgen.api.definitons.TcArgDefinition;
import com.ysu.zyw.tc.webgen.api.definitons.TcClassDefinition;
import com.ysu.zyw.tc.webgen.api.definitons.TcMethodDefinition;
import com.ysu.zyw.tc.webgen.api.utils.TcPathUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class TcWebgenMachine {

    public static void gen(TcWebgenConfig config) {

        // gen web
        config.getApiDetails().forEach(apiDetails -> {
            TcClassDefinition classDefinition = TcClassDefinition
                    .builder()
                    .pkg(config.getProjectWebLayerPackage())
                    .imports(Lists.newArrayList(
                            Controller.class.getTypeName(),
                            RequestMapping.class.getTypeName(),
                            RequestBody.class.getTypeName()
                    ))
                    .name("HelloController")
                    .annotations(Lists.newArrayList(
                            TcAnnotationDefinition
                                    .builder()
                                    .annotation(Controller.class.getSimpleName())
                                    .build(),
                            TcAnnotationDefinition
                                    .builder()
                                    .annotation(RequestMapping.class.getSimpleName())
                                    .fields(Lists.newArrayList(
                                            TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                    .builder()
                                                    .name("value")
                                                    .value("/123")
                                                    .build()
                                    )).build()
                    ))
                    .fields(Lists.newArrayList())
                    .methods(Lists.newArrayList(
                            TcMethodDefinition
                                    .builder()
                                    .returnValueType("String")
                                    .name("find")
                                    .annotations(Lists.newArrayList(
                                            TcAnnotationDefinition
                                                    .builder()
                                                    .annotation(GetMapping.class.getSimpleName())
                                                    .fields(Lists.newArrayList(
                                                            TcAnnotationDefinition.TcAnnotationFieldDefinition
                                                                    .builder()
                                                                    .name("value")
                                                                    .value("123")
                                                                    .build()
                                                    )).build())
                                    )
                                    .args(Lists.newArrayList(
                                            TcArgDefinition
                                                    .builder()
                                                    .type("Hello")
                                                    .name("helo")
                                                    .annotations(Lists.newArrayList(
                                                            TcAnnotationDefinition
                                                                    .builder()
                                                                    .annotation(RequestBody.class.getSimpleName())
                                                                    .build()
                                                    )).build()
                                    )).build()
                    )).build();

            String classStr = TcClassBuilder.build(classDefinition);
            System.out.println(classStr);
        });
    }

    // ---

    // ---
    private static String findSourceRootPath(TcWebgenConfig config) {
        return config.getProjectMavenBaseDir() + config.getProjectMavenSourceDir();
    }

    private String findWebLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + TcPathUtils.convertPackageToPath(config.getProjectWebLayerPackage());
    }

    private String findSvcLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + TcPathUtils.convertPackageToPath(config.getProjectSvcLayerPackage());
    }

    private String findSvcImplLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + TcPathUtils.convertPackageToPath(config.getProjectSvcImplLayerPackage());
    }

    private String findDaoLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + TcPathUtils.convertPackageToPath(config.getProjectDaoLayerPackage());
    }

    private String findDaoImplLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + TcPathUtils.convertPackageToPath(config.getProjectDaoImplLayerPackage());
    }

}
