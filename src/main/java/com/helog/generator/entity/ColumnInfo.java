package com.helog.generator.entity;

import java.io.Serializable;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class ColumnInfo implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    private String colName;
    private String colType;
    private String comments;

    public ColumnInfo() {
    }

    public String getColName() {
        return this.colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColType() {
        return this.colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

