/*
 * This file is generated by jOOQ.
*/
package com.ysu.zyw.tc.demo.dao.jooq.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TUiPerson implements Serializable {

    private static final long serialVersionUID = 1478693884;

    private Long       pkClass;
    private Long       pkPerson;
    private Integer    demoInt;
    private Long       demoLong;
    private BigDecimal demoBigdimal;
    private String     demoString;
    private Boolean    demoBoolean;
    private Timestamp  demoTimestamp;

    public TUiPerson() {}

    public TUiPerson(TUiPerson value) {
        this.pkClass = value.pkClass;
        this.pkPerson = value.pkPerson;
        this.demoInt = value.demoInt;
        this.demoLong = value.demoLong;
        this.demoBigdimal = value.demoBigdimal;
        this.demoString = value.demoString;
        this.demoBoolean = value.demoBoolean;
        this.demoTimestamp = value.demoTimestamp;
    }

    public TUiPerson(
        Long       pkClass,
        Long       pkPerson,
        Integer    demoInt,
        Long       demoLong,
        BigDecimal demoBigdimal,
        String     demoString,
        Boolean    demoBoolean,
        Timestamp  demoTimestamp
    ) {
        this.pkClass = pkClass;
        this.pkPerson = pkPerson;
        this.demoInt = demoInt;
        this.demoLong = demoLong;
        this.demoBigdimal = demoBigdimal;
        this.demoString = demoString;
        this.demoBoolean = demoBoolean;
        this.demoTimestamp = demoTimestamp;
    }

    public Long getPkClass() {
        return this.pkClass;
    }

    public void setPkClass(Long pkClass) {
        this.pkClass = pkClass;
    }

    public Long getPkPerson() {
        return this.pkPerson;
    }

    public void setPkPerson(Long pkPerson) {
        this.pkPerson = pkPerson;
    }

    public Integer getDemoInt() {
        return this.demoInt;
    }

    public void setDemoInt(Integer demoInt) {
        this.demoInt = demoInt;
    }

    public Long getDemoLong() {
        return this.demoLong;
    }

    public void setDemoLong(Long demoLong) {
        this.demoLong = demoLong;
    }

    public BigDecimal getDemoBigdimal() {
        return this.demoBigdimal;
    }

    public void setDemoBigdimal(BigDecimal demoBigdimal) {
        this.demoBigdimal = demoBigdimal;
    }

    public String getDemoString() {
        return this.demoString;
    }

    public void setDemoString(String demoString) {
        this.demoString = demoString;
    }

    public Boolean getDemoBoolean() {
        return this.demoBoolean;
    }

    public void setDemoBoolean(Boolean demoBoolean) {
        this.demoBoolean = demoBoolean;
    }

    public Timestamp getDemoTimestamp() {
        return this.demoTimestamp;
    }

    public void setDemoTimestamp(Timestamp demoTimestamp) {
        this.demoTimestamp = demoTimestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TUiPerson (");

        sb.append(pkClass);
        sb.append(", ").append(pkPerson);
        sb.append(", ").append(demoInt);
        sb.append(", ").append(demoLong);
        sb.append(", ").append(demoBigdimal);
        sb.append(", ").append(demoString);
        sb.append(", ").append(demoBoolean);
        sb.append(", ").append(demoTimestamp);

        sb.append(")");
        return sb.toString();
    }
}
