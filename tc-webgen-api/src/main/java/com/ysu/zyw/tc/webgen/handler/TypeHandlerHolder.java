package com.ysu.zyw.tc.webgen.handler;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

public class TypeHandlerHolder {

    public static final Map<Class, TypeHandler> typeHandlers = Maps.newHashMap(
            ImmutableMap.<Class, TypeHandler>builder()
                    .put(Integer.class, new IntegerTypeHandler())
                    .put(Long.class, new LongTypeHandler())
                    .put(Boolean.class, new BooleanTypeHandler())
                    .put(BigDecimal.class, new BigDecimalTypeHandler())
                    .put(String.class, new StringTypeHandler())
                    .put(Timestamp.class, new TimestampTypeHandler())
                    .build()
    );

}
