package com.kd;

import java.io.Serializable;

public class AmendConfiguration implements Serializable {

    private String columnToSelect;

    private String groupColumns;

    public String getColumnToSelect() {
        return columnToSelect;
    }

    public void setColumnToSelect(String columnToSelect) {
        this.columnToSelect = columnToSelect;
    }

    public String getGroupColumns() {
        return groupColumns;
    }

    public void setGroupColumns(String groupColumns) {
        this.groupColumns = groupColumns;
    }
}
