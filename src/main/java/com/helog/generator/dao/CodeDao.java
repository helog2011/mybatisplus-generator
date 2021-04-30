package com.helog.generator.dao;

import com.helog.generator.entity.DbConfig;
import com.helog.generator.entity.TableInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Repository
public interface CodeDao {

    void saveComment(TableInfo var1, DbConfig var2);

    List<TableInfo> getAllTables(DbConfig var1);

    TableInfo getAllColumns(String var1, DbConfig var2);

    String testConnection(DbConfig var1);
}
