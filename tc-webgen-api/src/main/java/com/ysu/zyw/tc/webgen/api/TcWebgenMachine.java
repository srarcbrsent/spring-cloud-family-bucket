package com.ysu.zyw.tc.webgen.api;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.api.builder.web.TcDefaultWebLayerGenBuilder;
import com.ysu.zyw.tc.webgen.api.definitons.TcClassDefinition;
import com.ysu.zyw.tc.webgen.api.utils.TcPathUtils;

public class TcWebgenMachine {

    public static void gen(TcWebgenConfig config) {
        String sourcePath = config.getProjectMavenBaseDir() + config.getProjectMavenSourceDir();
        String webLayerPath = sourcePath + TcPathUtils.convertPackageToPath(config.getProjectWebLayerPackage());
        // gen web
        config.getApiDetails().forEach(apiDetails -> {
            TcClassDefinition classDefinition = TcClassDefinition.builder()
                    .pkg(config.getProjectWebLayerPackage())
                    .imports(Lists.newArrayList(
                            "org.springframework.stereotype.Controller",
                            "org.springframework.web.bind.annotation.RequestMapping"
                    ))
                    .name("HelloController")
                    .annotations(Lists.newArrayList(
                            "Controller",
                            "RequestMapping"
                    )).build();

            TcDefaultWebLayerGenBuilder.builder()
                    .genPath(webLayerPath)
                    .tcClassDefinition(classDefinition)
                    .tcMethodDefinition(Lists.newArrayList())
                    .build()
                    .build();
        });
    }

}
