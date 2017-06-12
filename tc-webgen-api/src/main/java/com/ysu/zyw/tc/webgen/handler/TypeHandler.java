package com.ysu.zyw.tc.webgen.handler;

import com.ysu.zyw.tc.webgen.constant.Const;
import com.ysu.zyw.tc.webgen.definiton.FieldDefinition;
import com.ysu.zyw.tc.webgen.utils.NamingUtils;
import org.jooq.Field;

import java.util.List;

public abstract class TypeHandler {

    public abstract List<FieldDefinition> generate(Field field);

    protected FieldDefinition generateEqField(Field field) {
        return FieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(NamingUtils.underlineToLowercaseCamel(field.getName()) + Const.TERMS_QUERY_EQ_SUFFIX)
                .build();
    }

    protected FieldDefinition generateNeqField(Field field) {
        return FieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(NamingUtils.underlineToLowercaseCamel(field.getName()) + Const.TERMS_QUERY_NEQ_SUFFIX)
                .build();
    }

    protected FieldDefinition generateGtField(Field field) {
        return FieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(NamingUtils.underlineToLowercaseCamel(field.getName()) + Const.TERMS_QUERY_GT_SUFFIX)
                .build();
    }

    protected FieldDefinition generateLtField(Field field) {
        return FieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(NamingUtils.underlineToLowercaseCamel(field.getName()) + Const.TERMS_QUERY_LT_SUFFIX)
                .build();
    }

    protected FieldDefinition generateGteField(Field field) {
        return FieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(NamingUtils.underlineToLowercaseCamel(field.getName()) + Const.TERMS_QUERY_GTE_SUFFIX)
                .build();
    }

    protected FieldDefinition generateLteField(Field field) {
        return FieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(NamingUtils.underlineToLowercaseCamel(field.getName()) + Const.TERMS_QUERY_LTE_SUFFIX)
                .build();
    }

    protected FieldDefinition generateInField(Field field) {
        return FieldDefinition
                .builder()
                .type("List<" + field.getType().getSimpleName() + ">")
                .name(NamingUtils.underlineToLowercaseCamel(field.getName()) + Const.TERMS_QUERY_IN_SUFFIX)
                .build();
    }
}
