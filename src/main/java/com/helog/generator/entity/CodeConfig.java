package com.helog.generator.entity;

import java.io.Serializable;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class CodeConfig implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    private String namespace = "pages";
    private String tableRemovePrefixes = "tb,sys,cm,pf,sa,tf,";
    private String basepackage;
    private String basepackage_controller;
    private String path_model;
    private String path_mybatis;
    private String path_admin;
    private String path_front;
    private String path_html5;
    private String outRoot;
    private String templateName;

    public CodeConfig() {
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getTableRemovePrefixes() {
        return this.tableRemovePrefixes;
    }

    public void setTableRemovePrefixes(String tableRemovePrefixes) {
        this.tableRemovePrefixes = tableRemovePrefixes;
    }

    public String getBasepackage() {
        return this.basepackage;
    }

    public void setBasepackage(String basepackage) {
        this.basepackage = basepackage;
    }

    public String getPath_model() {
        return this.path_model;
    }

    public void setPath_model(String path_model) {
        this.path_model = path_model;
    }

    public String getPath_mybatis() {
        return this.path_mybatis;
    }

    public void setPath_mybatis(String path_mybatis) {
        this.path_mybatis = path_mybatis;
    }

    public String getPath_admin() {
        return this.path_admin;
    }

    public void setPath_admin(String path_admin) {
        this.path_admin = path_admin;
    }

    public String getPath_front() {
        return this.path_front;
    }

    public void setPath_front(String path_front) {
        this.path_front = path_front;
    }

    public String getPath_html5() {
        return this.path_html5;
    }

    public void setPath_html5(String path_html5) {
        this.path_html5 = path_html5;
    }

    public String getOutRoot() {
        return this.outRoot;
    }

    public void setOutRoot(String outRoot) {
        this.outRoot = outRoot;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getBasepackage_controller() {
        return this.basepackage_controller;
    }

    public void setBasepackage_controller(String basepackage_controller) {
        this.basepackage_controller = basepackage_controller;
    }
}
