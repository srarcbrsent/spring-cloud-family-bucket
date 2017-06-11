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
public class TcMethodDefinition {

    private String returnValueType;

    private String name;

    private String body;

    private List<TcAnnotationDefinition> annotations = Lists.newArrayList();

    private List<TcArgDefinition> args = Lists.newArrayList();

}
