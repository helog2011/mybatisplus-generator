package com.helog.generator.entity;

import java.io.Serializable;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class CodeItem implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    private String itemName;
    private String itemType;
    private String remarks;

    public CodeItem() {
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}