package com.ysu.zyw.tc.webgen.api;

import com.google.common.collect.Lists;
import lombok.Data;
import org.jooq.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TcWebgenImportUtils {
    static List<String> findTermsFieldImports(Field[] fields) {
        Set<String> importsSet = Arrays.stream(fields)
                .filter(field -> !field.getType().getName().startsWith("java.lang"))
                .map(field -> field.getType().getName()).collect(Collectors.toSet());
        importsSet.add(List.class.getTypeName());
        importsSet.add(Data.class.getTypeName());
        return Lists.newArrayList(importsSet);
    }
}