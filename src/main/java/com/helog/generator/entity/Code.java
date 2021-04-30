package com.helog.generator.entity;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class Code implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    private Integer funcId;
    private String funcCode;
    private String funcName;
    private String url;
    private String authType;
    private String isMenu;
    private Integer parentId;
    private String viewLevelNo;
    private String createUserId;
    private Date createDateTime;
    private String modifyUserId;
    private Date modifyDateTime;
    private String deleteFlag;

    public Code() {
    }

    public Integer getFuncId() {
        return this.funcId;
    }

    public void setFuncId(Integer value) {
        this.funcId = value;
    }

    public String getFuncCode() {
        return this.funcCode;
    }

    public void setFuncCode(String value) {
        this.funcCode = value;
    }

    public String getFuncName() {
        return this.funcName;
    }

    public void setFuncName(String value) {
        this.funcName = value;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String value) {
        this.url = value;
    }

    public String getAuthType() {
        return this.authType;
    }

    public void setAuthType(String value) {
        this.authType = value;
    }

    public String getIsMenu() {
        return this.isMenu;
    }

    public void setIsMenu(String value) {
        this.isMenu = value;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer value) {
        this.parentId = value;
    }

    public String getViewLevelNo() {
        return this.viewLevelNo;
    }

    public void setViewLevelNo(String value) {
        this.viewLevelNo = value;
    }

    public String getCreateUserId() {
        return this.createUserId;
    }

    public void setCreateUserId(String value) {
        this.createUserId = value;
    }

    public Date getCreateDateTime() {
        return this.createDateTime;
    }

    public void setCreateDateTime(Date value) {
        this.createDateTime = value;
    }

    public String getModifyUserId() {
        return this.modifyUserId;
    }

    public void setModifyUserId(String value) {
        this.modifyUserId = value;
    }

    public Date getModifyDateTime() {
        return this.modifyDateTime;
    }

    public void setModifyDateTime(Date value) {
        this.modifyDateTime = value;
    }

    public String getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(String value) {
        this.deleteFlag = value;
    }
}

