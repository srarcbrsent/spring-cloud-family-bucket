package com.ysu.zyw.tc.webgen.api;

import com.ysu.zyw.tc.webgen.api.utils.TcPathUtils;

public class TcWebgenSourcePathUtils {

    static String findSourceRootPath(TcWebgenConfig config) {
        return config.getProjectMavenBaseDir() + config.getProjectMavenSourceDir();
    }

    static String findTermsLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + "/" + TcPathUtils.convertPackageToPath(config.getProjectTermsLayerPackage());
    }

    static String findWebLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + "/" + TcPathUtils.convertPackageToPath(config.getProjectWebLayerPackage());
    }

    static String findSvcLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + "/" + TcPathUtils.convertPackageToPath(config.getProjectSvcLayerPackage());
    }

    static String findSvcImplLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + "/" + TcPathUtils.convertPackageToPath(config.getProjectSvcImplLayerPackage());
    }

    static String findDaoLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + "/" + TcPathUtils.convertPackageToPath(config.getProjectDaoLayerPackage());
    }

    static String findDaoImplLayerSourcePath(TcWebgenConfig config) {
        return findSourceRootPath(config) + "/" + TcPathUtils.convertPackageToPath(config.getProjectDaoImplLayerPackage());
    }
}