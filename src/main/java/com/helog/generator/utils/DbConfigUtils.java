package com.helog.generator.utils;

import com.helog.generator.entity.DbConfig;
import com.helog.generator.service.impl.CodeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class DbConfigUtils {

    private static Properties properties;
    private static File file = new File("database.properties");

    public DbConfigUtils() {
        properties = new Properties();
        System.out.println("database.properties路径==================> " + file.getAbsolutePath());
    }

    public void init(File file) throws IOException {
        InputStream fis = new FileInputStream(file);
        properties.load(fis);
    }

    public boolean addDbconfig(DbConfig dbConfig) {
        try {
            this.init(file);
            boolean b = properties.containsKey(dbConfig.getDbName());
            if (b) {
                return this.modifyDbconfig(dbConfig);
            } else {
                properties.put(dbConfig.getDbName(), dbConfig.getUrl() + "|" + dbConfig.getDriver() + "|" + dbConfig.getUsername() + "|" + dbConfig.getPassword() + "|" + dbConfig.getSchema());
                properties.store(new FileOutputStream(file), (new Date()).toString());
                return true;
            }
        } catch (IOException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public boolean modifyDbconfig(DbConfig dbConfig) throws IOException {
        this.init(file);
        boolean b = properties.containsKey(dbConfig.getDbName());
        if (b) {
            properties.setProperty(dbConfig.getDbName(), dbConfig.getUrl() + "|" + dbConfig.getDriver() + "|" + dbConfig.getUsername() + "|" + dbConfig.getPassword() + "|" + dbConfig.getSchema());
            properties.store(new FileOutputStream(file), (new Date()).toString());
            return true;
        } else {
            return this.addDbconfig(dbConfig);
        }
    }

    public boolean deleteDbconfig(String key) {
        try {
            this.init(file);
            properties.remove(key);
            properties.store(new FileOutputStream(file), (new Date()).toString());
            return true;
        } catch (IOException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public DbConfig getDbconfigByKey(String key) throws IOException {
        this.init(file);
        String str = (String)properties.get(key);
        if (null != str && !"".equals(str)) {
            String[] splits = str.split("\\|");
            DbConfig dc = new DbConfig();
            dc.setDbName(key);
            dc.setUrl(splits[0]);
            dc.setDriver(splits[1]);
            dc.setUsername(splits[2]);
            dc.setPassword(splits[3]);
            dc.setSchema(splits[4]);
            return dc;
        } else {
            return null;
        }
    }

    public List<DbConfig> getAllDbconfig() {
        try {
            this.init(file);
            List<DbConfig> list = new ArrayList();
            Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            Iterator var3 = entries.iterator();

            while(var3.hasNext()) {
                Map.Entry<Object, Object> temp = (Map.Entry)var3.next();
                DbConfig dc = new DbConfig();
                dc.setDbName((String)temp.getKey());
                String value = (String)temp.getValue();
                String[] splits = value.split("\\|");
                dc.setUrl(splits[0]);
                dc.setDriver(splits[1]);
                dc.setUsername(splits[2]);
                dc.setPassword(splits[3]);
                dc.setSchema(splits[4]);
                list.add(dc);
            }

            return list;
        } catch (IOException var8) {
            var8.printStackTrace();
            return null;
        }
    }

    static {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }

    }
}
