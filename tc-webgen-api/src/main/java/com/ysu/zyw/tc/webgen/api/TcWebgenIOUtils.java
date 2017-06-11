package com.ysu.zyw.tc.webgen.api;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class TcWebgenIOUtils {// --- io

    @SneakyThrows
    static void write(TcWebgenConfig config, String path, String className, String classStr) {
        String filepath = path + "/" + className + ".java";
        File file = new File(filepath);
        if (file.exists() && !config.isOverride()) {
            System.out.println("[ERROR] [" + filepath + "] already exists and not override ...");
            return;
        }
        // make path dir
        File directory = new File(path);
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
        }
        // delete and recreate
        boolean succ;
        if (!file.exists()) {
            succ = file.createNewFile();
        } else {
            succ = file.delete();
        }
        if (!succ) {
            System.out.println("[ERROR] [" + filepath + "] recreate file failed ...");
        }
        // write
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file))) {
            IOUtils.write(classStr, writer);
        }
        System.out.println("[SUCC] success generate file [" + filepath + "]");
    }
}