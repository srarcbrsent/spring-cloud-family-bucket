package com.ysu.zyw.tc.demo;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.demo.dao.jooq.tables.TUiClass;
import com.ysu.zyw.tc.webgen.api.TcWebgenConfig;
import com.ysu.zyw.tc.webgen.api.TcWebgenMachine;
import org.springframework.stereotype.Controller;

public class TcWebgen {

    public static void main(String[] args) {
        TcWebgenConfig config = TcWebgenConfig.builder()
                .override(true)
                .projectMavenBaseDir("/Users/zhangyaowu/env/sources/spring-cloud-family-bucket/tc-biz-demo")
                .projectMavenSourceDir("/src/main/java")
                .projectDaoLayerPackage("com.ysu.zyw.tc.demo.dao")
                .projectDaoImplLayerPackage("com.ysu.zyw.tc.demo.dao.impl")
                .projectSvcLayerPackage("com.ysu.zyw.tc.demo.svc")
                .projectSvcImplLayerPackage("com.ysu.zyw.tc.demo.svc.impl")
                .projectWebLayerPackage("com.ysu.zyw.tc.demo.web")
                .apiDetails(Lists.newArrayList(
                        TcWebgenConfig.TcWebgenApiDetails.builder()
                                .table(TUiClass.T_UI_CLASS)
                                .pojo(com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiClass.class)
                                .build()
                )).build();

        TcWebgenMachine.gen(config);
    }

}
