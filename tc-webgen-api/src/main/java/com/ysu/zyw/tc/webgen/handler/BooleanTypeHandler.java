package com.ysu.zyw.tc.webgen.handler;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.definiton.FieldDefinition;
import org.jooq.Field;

import java.util.List;

public class BooleanTypeHandler extends TypeHandler {

    @Override
    public List<FieldDefinition> generate(Field field) {
        return Lists.newArrayList(
                generateEqField(field),
                generateNeqField(field)
        );
    }

}
