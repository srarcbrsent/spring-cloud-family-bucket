package com.ysu.zyw.tc.webgen.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathUtils {

    public static String convertPackageToPath(String pkg) {
        return pkg.replace(".", "/");
    }

    public static String convertPathToPackage(String path) {
        return path.replace("/", ".");
    }

}
