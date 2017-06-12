package com.ysu.zyw.tc.webgen.definiton;

import com.github.javaparser.ast.expr.Expression;
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
public class AnnotationDefinition {

    private String annotation;

    private List<AnnotationFieldDefinition> fields = Lists.newArrayList();

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class AnnotationFieldDefinition {

        protected String name;

        private String value;

        private Expression expression;

    }

}
