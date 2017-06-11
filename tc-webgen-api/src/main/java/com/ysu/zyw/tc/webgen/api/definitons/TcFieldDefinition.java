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
public class TcFieldDefinition {

    private String type;

    private String name;

    private List<TcAnnotationDefinition> annotations = Lists.newArrayList();

}
