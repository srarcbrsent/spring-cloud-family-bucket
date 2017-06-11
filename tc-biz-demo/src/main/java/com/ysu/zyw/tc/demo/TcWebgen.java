package com.ysu.zyw.tc.demo;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.demo.dao.jooq.tables.TUiClass;
import com.ysu.zyw.tc.demo.dao.jooq.tables.TUiPerson;
import com.ysu.zyw.tc.demo.dao.jooq.tables.records.TUiClassRecord;
import com.ysu.zyw.tc.webgen.api.TcWebgenConfig;
import com.ysu.zyw.tc.webgen.api.TcWebgenMachine;

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
                .projectTermsLayerPackage("com.ysu.zyw.tc.demo.web.terms")
                // 去掉 'T_'
                .tableNamePostProcessor(tableName -> tableName.substring(2))
                // Controller 加版本号
                .controllerMappingPostProcessor(mapping -> "/v1" + mapping)
                .tableDetails(Lists.newArrayList(
                        TcWebgenConfig.TcWebgenTableDetail.builder()
                                .table(TUiClass.T_UI_CLASS)
                                .pojo(com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiClass.class)
                                .build(),
                        TcWebgenConfig.TcWebgenTableDetail.builder()
                                .table(TUiPerson.T_UI_PERSON)
                                .pojo(com.ysu.zyw.tc.demo.dao.jooq.tables.pojos.TUiPerson.class)
                                .build()
                )).build();

        TcWebgenMachine.gen(config);
    }

}
