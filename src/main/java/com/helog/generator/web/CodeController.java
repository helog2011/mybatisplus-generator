package com.helog.generator.web;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.helog.generator.entity.CodeConfig;
import com.helog.generator.entity.ColumnInfo;
import com.helog.generator.entity.DbConfig;
import com.helog.generator.entity.TableInfo;
import com.helog.generator.service.CodeService;
import com.helog.generator.utils.DbConfigUtils;
import com.helog.generator.utils.FileUtils;
import com.helog.generator.utils.ZipFileUtils;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Controller
@RequestMapping
public class CodeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    private CodeService codeService;

    public CodeController() {
    }

    @RequestMapping({"/index"})
    public String init(Model model) {
        DbConfigUtils dbConfigUtils = new DbConfigUtils();
        List<DbConfig> dbConfigList = dbConfigUtils.getAllDbconfig();
        model.addAttribute("dbConfigList", dbConfigList);
        return "index";
    }

    @RequestMapping(value = {"/tablelist"},method = {RequestMethod.GET})
    public String tablelist(Model model, DbConfig dbConfig) {
        List<TableInfo> tableList = this.codeService.getAllTables(dbConfig);
        model.addAttribute("dbConfig", dbConfig);
        model.addAttribute("tableList", tableList);
        return "table_list";
    }

    @RequestMapping(value = {"/edit"},method = {RequestMethod.GET})
    public String edit(Model model, DbConfig dbConfig) {
        model.addAttribute("dbConfig", dbConfig);
        return "edit";
    }

    @ResponseBody
    @RequestMapping(value = {"/test"},method = {RequestMethod.POST})
    public String test(Model model, DbConfig dbConfig) {
        String result = this.codeService.testConnection(dbConfig);
        return result;
    }

    @RequestMapping(value = {"/save"},method = {RequestMethod.POST})
    public String save(Model model, DbConfig dbConfig) {
        DbConfigUtils dbConfigUtils = new DbConfigUtils();
        dbConfigUtils.addDbconfig(dbConfig);
        return "redirect:/index";
    }

    @RequestMapping(value = {"/delete"},method = {RequestMethod.GET})
    public String delete(Model model, DbConfig dbConfig) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        DbConfigUtils dbConfigUtils = new DbConfigUtils();
        dbConfigUtils.deleteDbconfig(dbConfig.getDbName());
        return "redirect:/index";
    }

    @RequestMapping(value = {"/column/{tableName}"},method = {RequestMethod.GET})
    public String itemList(Model model, DbConfig dbConfig, @PathVariable String tableName) {
        TableInfo tableInfo = this.codeService.getAllColumns(tableName, dbConfig);
        model.addAttribute("tableInfo", tableInfo);
        model.addAttribute("dbConfig", dbConfig);
        return "column_list";
    }

    @RequestMapping(value = {"/columnsave"},method = {RequestMethod.POST})
    public String save(Model model, DbConfig dbConfig, TableInfo tableInfo) {
        String[] arrRemark = this.request.getParameterValues("remarks");
        List<ColumnInfo> listItem = new ArrayList();
        String[] var6 = arrRemark;
        int var7 = arrRemark.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String remark = var6[var8];
            System.out.println(remark);
            String[] mark = remark.split("@");
            ColumnInfo item = new ColumnInfo();
            item.setColName(mark[0]);
            item.setColType(mark[1]);
            item.setComments(mark[2]);
            listItem.add(item);
        }

        tableInfo.setListColumn(listItem);
        this.codeService.saveComment(tableInfo, dbConfig);
        return "redirect:/column/" + tableInfo.getTableName() + "?url=" + dbConfig.getUrl() + "&driver=" + dbConfig.getDriver() + "&username=" + dbConfig.getUsername() + "&password=" + dbConfig.getPassword() + "&schema=" + dbConfig.getSchema();
    }

    @RequestMapping(value = {"/generate"},method = {RequestMethod.POST})
    public String generate(DbConfig dbConfig, TableInfo tableInfo, CodeConfig codeConfig, boolean flag, HttpServletResponse response) {
        String model = tableInfo.getComments().substring(tableInfo.getComments().indexOf("#") + 1);
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(this.request.getSession().getServletContext().getRealPath("/") + "\\WEB-INF\\upload\\" + this.request.getSession().getId());
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor("helog");
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(dbConfig.getDriver().indexOf("mysql") > -1 ? DbType.MYSQL : DbType.ORACLE);
        dsc.setDriverName(dbConfig.getDriver());
        dsc.setUsername(dbConfig.getUsername());
        dsc.setPassword(dbConfig.getPassword());
        dsc.setUrl(dbConfig.getUrl());
        mpg.setDataSource(dsc);
        StrategyConfig strategy = new StrategyConfig();
        if (flag && tableInfo.getTableName().indexOf("_") > 0 && tableInfo.getTableName().lastIndexOf("_") != tableInfo.getTableName().length() - 1) {
            String prefix = tableInfo.getTableName().substring(0, tableInfo.getTableName().indexOf("_") + 1);
            strategy.setTablePrefix(prefix);
            strategy.setNaming(NamingStrategy.nochange);
        } else {
            strategy.setNaming(NamingStrategy.underline_to_camel);
        }

        strategy.setInclude(new String[]{tableInfo.getTableName()});
        mpg.setStrategy(strategy);
        PackageConfig pc = new PackageConfig();
        pc.setParent("com");
        pc.setModuleName(model);
        mpg.setPackageInfo(pc);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);
        mpg.execute();
        System.err.println(mpg.getCfg().getMap().get("abc"));
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=src.zip");
        System.out.println("in BatchDownload................");

        try {
            ZipFileUtils zip = new ZipFileUtils();
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            String fileName = this.request.getSession().getServletContext().getRealPath("/") + "\\WEB-INF\\upload\\" + this.request.getSession().getId();
            File ff = new File(fileName);
            if (!ff.exists()) {
                ff.mkdirs();
            }

            zip.zip(ff, zos, "");
            zos.flush();
            zos.close();
            FileUtils.DeleteFolder(this.request.getSession().getServletContext().getRealPath("/") + "\\WEB-INF\\upload\\" + this.request.getSession().getId());
        } catch (IOException var17) {
            var17.printStackTrace();
        }

        return null;
    }
}

