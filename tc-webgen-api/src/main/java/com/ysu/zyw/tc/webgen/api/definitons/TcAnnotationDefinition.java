package com.ysu.zyw.tc.webgen.api.definitons;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TcAnnotationDefinition {

    private String annotation;

    private List<TcAnnotationFieldDefinition> fields = Lists.newArrayList();

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class TcAnnotationFieldDefinition {

        private String name;

        private String value;

    }

}
