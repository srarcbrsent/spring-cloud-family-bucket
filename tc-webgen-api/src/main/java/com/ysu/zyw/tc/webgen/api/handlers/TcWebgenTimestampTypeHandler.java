package com.ysu.zyw.tc.webgen.api.handlers;

import com.google.common.collect.Lists;
import com.ysu.zyw.tc.webgen.api.definitons.TcFieldDefinition;
import org.jooq.Field;

import java.util.List;

public class TcWebgenTimestampTypeHandler extends TcAbstractWebgenTypeHandler {

    @Override
    public List<TcFieldDefinition> generate(Field field) {
        return Lists.newArrayList(
                generateEqField(field),
                generateNeqField(field),
                generateGtField(field),
                generateLtField(field),
                generateGteField(field),
                generateLteField(field)
        );
    }

}
