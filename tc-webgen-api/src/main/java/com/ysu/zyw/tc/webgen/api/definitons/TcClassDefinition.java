package com.ysu.zyw.tc.webgen.api.definitons;

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

    private String path;

    private String pkg;

    private String name;

    private List<TcAnnotationDefinition> annotations;

    private List<TcFieldDefinition> fields;

    private List<TcMethodDefinition> methods;

    public static class TcFieldDefinition {

        private String type;

        private String name;

        private List<TcAnnotationDefinition>

    }

    public static class TcMethodDefinition {

        private String name;

        private List<TcAnnotationDefinition> annotations;

        private List<TcArgDefinition> args;

        public static class TcArgDefinition {

        }

    }

}
