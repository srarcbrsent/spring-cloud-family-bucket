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
public class MethodDefinition {

    private String returnValueType;

    private String name;

    private String body;

    private List<AnnotationDefinition> annotations = Lists.newArrayList();

    private List<ArgDefinition> args = Lists.newArrayList();

}
