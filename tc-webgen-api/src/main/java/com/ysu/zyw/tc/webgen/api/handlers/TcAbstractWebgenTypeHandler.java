package com.ysu.zyw.tc.webgen.api.handlers;

import com.ysu.zyw.tc.webgen.api.constant.TcWebgenConst;
import com.ysu.zyw.tc.webgen.api.definitons.TcFieldDefinition;
import com.ysu.zyw.tc.webgen.api.utils.TcNameUtils;
import org.jooq.Field;

import java.util.List;

public abstract class TcAbstractWebgenTypeHandler {

    public abstract List<TcFieldDefinition> generate(Field field);

    protected TcFieldDefinition generateEqField(Field field) {
        return TcFieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(TcNameUtils.underlineToLowercaseCamel(field.getName()) + TcWebgenConst.TERMS_QUERY_EQ_SUFFIX)
                .build();
    }

    protected TcFieldDefinition generateNeqField(Field field) {
        return TcFieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(TcNameUtils.underlineToLowercaseCamel(field.getName()) + TcWebgenConst.TERMS_QUERY_NEQ_SUFFIX)
                .build();
    }

    protected TcFieldDefinition generateGtField(Field field) {
        return TcFieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(TcNameUtils.underlineToLowercaseCamel(field.getName()) + TcWebgenConst.TERMS_QUERY_GT_SUFFIX)
                .build();
    }

    protected TcFieldDefinition generateLtField(Field field) {
        return TcFieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(TcNameUtils.underlineToLowercaseCamel(field.getName()) + TcWebgenConst.TERMS_QUERY_LT_SUFFIX)
                .build();
    }

    protected TcFieldDefinition generateGteField(Field field) {
        return TcFieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(TcNameUtils.underlineToLowercaseCamel(field.getName()) + TcWebgenConst.TERMS_QUERY_GTE_SUFFIX)
                .build();
    }

    protected TcFieldDefinition generateLteField(Field field) {
        return TcFieldDefinition
                .builder()
                .type(field.getType().getSimpleName())
                .name(TcNameUtils.underlineToLowercaseCamel(field.getName()) + TcWebgenConst.TERMS_QUERY_LTE_SUFFIX)
                .build();
    }

    protected TcFieldDefinition generateInField(Field field) {
        return TcFieldDefinition
                .builder()
                .type("List<" + field.getType().getSimpleName() + ">")
                .name(TcNameUtils.underlineToLowercaseCamel(field.getName()) + TcWebgenConst.TERMS_QUERY_IN_SUFFIX)
                .build();
    }
}
