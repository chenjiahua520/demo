package com.generator.common.element;

/**
 * @author chenjiahua
 * @version 2019-01-31 10:02
 */
public class ColumnElement {

    private String columnName;

    private String columnRemarks;

    private String javaType;

    private String jdbcType;

    private String javaFieldName;

    private String javaFieldNameAtRuntime;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnRemarks() {
        return columnRemarks;
    }

    public void setColumnRemarks(String columnRemarks) {
        this.columnRemarks = columnRemarks;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaFieldName() {
        return javaFieldName;
    }

    public void setJavaFieldName(String javaFieldName) {
        this.javaFieldName = javaFieldName;
    }

    public String getJavaFieldNameAtRuntime() {
        return javaFieldNameAtRuntime;
    }

    public void setJavaFieldNameAtRuntime(String javaFieldNameAtRuntime) {
        this.javaFieldNameAtRuntime = javaFieldNameAtRuntime;
    }
}
