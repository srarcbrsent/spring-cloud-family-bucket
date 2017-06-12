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
public class FieldDefinition {

    private String type;

    private String name;

    private List<AnnotationDefinition> annotations = Lists.newArrayList();

}
