package com.helog.generator.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class TableInfo implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    private String tableName;
    private String comments;
    private List<ColumnInfo> listColumn;

    public TableInfo() {
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<ColumnInfo> getListColumn() {
        return this.listColumn;
    }

    public void setListColumn(List<ColumnInfo> listColumn) {
        this.listColumn = listColumn;
    }
}
