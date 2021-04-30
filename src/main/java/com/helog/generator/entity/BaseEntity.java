package com.helog.generator.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class BaseEntity implements Serializable {
    @TableId(
            type = IdType.ID_WORKER
    )
    private Long id;
    private Date createdTime;
    private String creater;
    private Date modifiedTime;
    private String modifier;
    private Boolean isDelete;

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean isDelete() {
        return this.isDelete;
    }

    public void setDelete(Boolean delete) {
        this.isDelete = delete;
    }

    public void update() {
        this.setModifiedTime(new Date());
    }

    public void add() {
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreater() {
        return this.creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
