package com.ysu.zyw.tc.webgen.definiton;

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
public class ClassDefinition {

    private boolean interfaze;

    private String pkg;

    private String name;

    private String implementsInterface;

    private List<String> imports = Lists.newArrayList();

    private List<AnnotationDefinition> annotations = Lists.newArrayList();

    private List<FieldDefinition> fields = Lists.newArrayList();

    private List<MethodDefinition> methods = Lists.newArrayList();

}
