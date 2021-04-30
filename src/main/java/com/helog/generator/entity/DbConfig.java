package com.helog.generator.entity;

import java.io.Serializable;

/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class DbConfig implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    private String dbName;
    private String url = "";
    private String driver = "";
    private String username = "";
    private String password = "";
    private String schema;
    private String catalog;
    private String dbType;

    public DbConfig() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDbType() {
        return this.dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
