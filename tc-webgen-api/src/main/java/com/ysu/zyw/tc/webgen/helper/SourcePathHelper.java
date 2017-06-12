package com.ysu.zyw.tc.webgen.helper;

import com.ysu.zyw.tc.webgen.config.Config;
import com.ysu.zyw.tc.webgen.utils.PathUtils;

public class SourcePathHelper {

    public static String findSourceRootPath(Config config) {
        return config.getProjectMavenBaseDir() + config.getProjectMavenSourceDir();
    }

    public static String findTermsLayerSourcePath(Config config) {
        return findSourceRootPath(config) + "/" + PathUtils.convertPackageToPath(config.getProjectTermsLayerPackage());
    }

    public static String findWebLayerSourcePath(Config config) {
        return findSourceRootPath(config) + "/" + PathUtils.convertPackageToPath(config.getProjectWebLayerPackage());
    }

    public static String findSvcLayerSourcePath(Config config) {
        return findSourceRootPath(config) + "/" + PathUtils.convertPackageToPath(config.getProjectSvcLayerPackage());
    }

    public static String findSvcImplLayerSourcePath(Config config) {
        return findSourceRootPath(config) + "/" + PathUtils.convertPackageToPath(config.getProjectSvcImplLayerPackage());
    }

    public static String findDaoLayerSourcePath(Config config) {
        return findSourceRootPath(config) + "/" + PathUtils.convertPackageToPath(config.getProjectDaoLayerPackage());
    }

    public static String findDaoImplLayerSourcePath(Config config) {
        return findSourceRootPath(config) + "/" + PathUtils.convertPackageToPath(config.getProjectDaoImplLayerPackage());
    }

    public static String findClientLayerSourcePath(Config config) {
        return findSourceRootPath(config) + "/" + PathUtils.convertPackageToPath(config.getProjectClientLayerPackage());
    }
}