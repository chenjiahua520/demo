package com.generator.common.element;

import java.util.List;

/**
 * @author chenjiahua
 * @version 2019-01-30 18:00
 */
public class TableElement {

    private String tableName;

    private String tableRemarks;

    private String alias;

    private String javaClassName;

    private String javaClassNameAtRuntime;

    private List<ColumnElement> columnList;

    private ProjectElement project;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableRemarks() {
        return tableRemarks;
    }

    public void setTableRemarks(String tableRemarks) {
        this.tableRemarks = tableRemarks;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }

    public String getJavaClassNameAtRuntime() {
        return javaClassNameAtRuntime;
    }

    public void setJavaClassNameAtRuntime(String javaClassNameAtRuntime) {
        this.javaClassNameAtRuntime = javaClassNameAtRuntime;
    }

    public List<ColumnElement> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnElement> columnList) {
        this.columnList = columnList;
    }

    public ProjectElement getProject() {
        return project;
    }

    public void setProject(ProjectElement project) {
        this.project = project;
    }

    public boolean isExistDeleteFlag() {
        if (this.columnList != null) {
            for (ColumnElement columnElement : this.columnList) {
                if ("delete_flag".equalsIgnoreCase(columnElement.getColumnName())) {
                    return true;
                }
            }
        }
        return false;
    }

}
