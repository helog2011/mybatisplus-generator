package com.helog.generator.service.impl;

import com.helog.generator.dao.CodeDao;
import com.helog.generator.dao.impl.CodeDaoImpl;
import com.helog.generator.entity.DbConfig;
import com.helog.generator.entity.TableInfo;
import com.helog.generator.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Service
public class CodeServiceImpl implements CodeService {
    private static final Logger logger = LoggerFactory.getLogger(CodeServiceImpl.class);
    @Autowired
    private CodeDao codeDao;

    public CodeServiceImpl() {
    }

    @Override
    public List<TableInfo> getAllTables(DbConfig dbConfig) {
        return this.codeDao.getAllTables(dbConfig);
    }

    @Override
    public TableInfo getAllColumns(String tableName, DbConfig dbConfig) {
        return this.codeDao.getAllColumns(tableName, dbConfig);
    }

    @Override
    public void saveComment(TableInfo tableInfo, DbConfig dbConfig) {
        this.codeDao.saveComment(tableInfo, dbConfig);
    }

    @Override
    public String testConnection(DbConfig dbConfig) {
        return this.codeDao.testConnection(dbConfig);
    }
}
