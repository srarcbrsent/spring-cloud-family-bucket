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
public class TcClassDefinition {

    private String pkg;

    private String name;

    private String implementsInterface;

    private List<String> imports = Lists.newArrayList();

    private List<TcAnnotationDefinition> annotations = Lists.newArrayList();

    private List<TcFieldDefinition> fields = Lists.newArrayList();

    private List<TcMethodDefinition> methods = Lists.newArrayList();

}
