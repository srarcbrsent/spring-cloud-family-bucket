package com.ysu.zyw.tc.webgen.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.Record;
import org.jooq.Table;

import java.util.List;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TcWebgenConfig {

    private boolean override;

    private String projectMavenBaseDir;

    private String projectMavenSourceDir;

    private String projectDaoLayerPackage;

    private String projectDaoImplLayerPackage;

    private String projectSvcLayerPackage;

    private String projectSvcImplLayerPackage;

    private String projectWebLayerPackage;

    private String projectTermsLayerPackage;

    private Function<String, String> tableNamePostProcessor;

    private Function<String ,String> controllerMappingPostProcessor;

    private List<TcWebgenTableDetail> tableDetails;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class TcWebgenTableDetail {

        private Table table;

        private Class<?> pojo;

    }

}
