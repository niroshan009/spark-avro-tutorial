package com.kd.amend;

import jdk.jfr.DataAmount;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;

import java.io.Serializable;


public class GroupLogicConfig implements Serializable {



    private Integer order;


    private String groupColumns;

    private String aggregateColumns;

    private boolean struct;

    private String alias;


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getGroupColumns() {
        return groupColumns;
    }

    public void setGroupColumns(String groupColumns) {
        this.groupColumns = groupColumns;
    }

    public String getAggregateColumns() {
        return aggregateColumns;
    }

    public void setAggregateColumns(String aggregateColumns) {
        this.aggregateColumns = aggregateColumns;
    }

    public boolean getStruct() {
        return this.struct;
    }

    public void setStruct(boolean struct) {
        this.struct = struct;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
