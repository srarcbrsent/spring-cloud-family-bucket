package com.ysu.zyw.tc.demo.web.terms;

import java.math.BigDecimal;
import java.util.List;
import java.sql.Timestamp;
import lombok.Data;

@Data()
public class UIPersonTerms {

    private Long pkClassEq;

    private Long pkClassNeq;

    private Long pkClassGt;

    private Long pkClassLt;

    private Long pkClassGte;

    private Long pkClassLte;

    private List<Long> pkClassIn;

    private Long pkPersonEq;

    private Long pkPersonNeq;

    private Long pkPersonGt;

    private Long pkPersonLt;

    private Long pkPersonGte;

    private Long pkPersonLte;

    private List<Long> pkPersonIn;

    private Integer demoIntEq;

    private Integer demoIntNeq;

    private Integer demoIntGt;

    private Integer demoIntLt;

    private Integer demoIntGte;

    private Integer demoIntLte;

    private List<Integer> demoIntIn;

    private Long demoLongEq;

    private Long demoLongNeq;

    private Long demoLongGt;

    private Long demoLongLt;

    private Long demoLongGte;

    private Long demoLongLte;

    private List<Long> demoLongIn;

    private BigDecimal demoBigdimalEq;

    private BigDecimal demoBigdimalNeq;

    private BigDecimal demoBigdimalGt;

    private BigDecimal demoBigdimalLt;

    private BigDecimal demoBigdimalGte;

    private BigDecimal demoBigdimalLte;

    private String demoStringEq;

    private String demoStringNeq;

    private List<String> demoStringIn;

    private Boolean demoBooleanEq;

    private Boolean demoBooleanNeq;

    private Timestamp demoTimestampEq;

    private Timestamp demoTimestampNeq;

    private Timestamp demoTimestampGt;

    private Timestamp demoTimestampLt;

    private Timestamp demoTimestampGte;

    private Timestamp demoTimestampLte;

    private Integer page;

    private Integer pageSize;
}
