package com.ysu.zyw.tc.webgen.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.Table;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TcWebgenConfig {

    private String projectMavenBaseDir;

    private String projectMavenSourceDir;

    private String projectDaoLayerPackage;

    private String projectDaoImplLayerPackage;

    private String projectSvcLayerPackage;

    private String projectSvcImplLayerPackage;

    private String projectWebLayerPackage;

    private List<TcWebgenApiDetails> apiDetails;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class TcWebgenApiDetails {

        private Table table;

        private Class<?> pojo;

    }

}
