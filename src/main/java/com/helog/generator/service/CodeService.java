package com.helog.generator.service;

import com.helog.generator.entity.DbConfig;
import com.helog.generator.entity.TableInfo;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Service
public interface CodeService {

    List<TableInfo> getAllTables(DbConfig var1);

    TableInfo getAllColumns(String var1, DbConfig var2);

    void saveComment(TableInfo var1, DbConfig var2);

    String testConnection(DbConfig var1);
}