package com.helog.generator.dao.impl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.helog.generator.MybatisplusGeneratorApplication;
import com.helog.generator.dao.CodeDao;
import com.helog.generator.entity.ColumnInfo;
import com.helog.generator.entity.DbConfig;
import com.helog.generator.entity.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
@Repository
public class CodeDaoImpl implements CodeDao {
    private static final Logger logger = LoggerFactory.getLogger(CodeDaoImpl.class);

    public CodeDaoImpl() {
    }

    @Override
    public void saveComment(TableInfo tableInfo, DbConfig dbConfig) {
        Connection conn = this.getConnection(dbConfig);
        try {
            Statement stmt = conn.createStatement();
            String strSql = "";
            Iterator var6;
            ColumnInfo item;
            //mysql
            if (dbConfig.getUrl().indexOf(DbType.MYSQL.getDb()) > 0) {
                strSql = "ALTER TABLE " + tableInfo.getTableName() + " COMMENT '#" + tableInfo.getComments() + "';";
                System.out.println(">>>>>>>>>>>" + strSql);
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();

                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "ALTER TABLE " + tableInfo.getTableName() + " MODIFY " + item.getColName() + " " + item.getColType() + " COMMENT '" + item.getComments() + "'; ";
                    System.out.println(">>>>>>>>>>>" + strSql);
                    stmt.executeUpdate(strSql);
                }
            }
            //oracle
            else if (dbConfig.getUrl().indexOf(DbType.ORACLE.getDb()) > 0) {
                strSql = "COMMENT ON TABLE " + tableInfo.getTableName() + " IS '#" + tableInfo.getComments() + "'";
                System.out.println(">>>>>>>>>>>" + strSql);
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();

                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "COMMENT ON COLUMN " + tableInfo.getTableName() + "." + item.getColName() + " IS '" + item.getComments() + "'";
                    System.out.println(">>>>>>>>>>>" + strSql);
                    stmt.executeUpdate(strSql);
                }
            }
            //db2
            else if (dbConfig.getUrl().indexOf(DbType.DB2.getDb()) > 0) {
                strSql = "comment on table "+tableInfo.getTableName()+"  is '" + tableInfo.getComments() + "'";
                System.out.println(">>>>>>>>>>>" + strSql);
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();
                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "COMMENT ON COLUMN " + tableInfo.getTableName() + "." + item.getColName() + " IS '" + item.getComments() + "'";
                    System.out.println(">>>>>>>>>>>" + strSql);
                    stmt.executeUpdate(strSql);
                }

            }
            //sqlserver
            else if (dbConfig.getUrl().indexOf(DbType.SQL_SERVER.getDb()) > 0) {
                strSql = "";
                System.out.println(">>>>>>>>>>>" + strSql);
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();
                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "";
                    System.out.println(">>>>>>>>>>>" + strSql);
                    stmt.executeUpdate(strSql);
                }
            }
            //mariadb
            else if (dbConfig.getUrl().indexOf(DbType.MARIADB.getDb()) > 0) {
                strSql = "";
                System.out.println(">>>>>>>>>>>" + strSql);
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();
                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "";
                    System.out.println(">>>>>>>>>>>" + strSql);
                    stmt.executeUpdate(strSql);
                }
            }
            //h2
            else if (dbConfig.getUrl().indexOf(DbType.H2.getDb()) > 0) {
                strSql = "";
                System.out.println(">>>>>>>>>>>" + strSql);
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();

                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "";
                    System.out.println(">>>>>>>>>>>" + strSql);
                    stmt.executeUpdate(strSql);
                }
            }
            //hsql
            else if (dbConfig.getUrl().indexOf(DbType.HSQL.getDb()) > 0) {
                strSql = "";
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();

                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "";
                    stmt.executeUpdate(strSql);
                }
            }
            //postgresql
            else if (dbConfig.getUrl().indexOf(DbType.POSTGRE_SQL.getDb()) > 0) {
                strSql = "";
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();

                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "";
                    stmt.executeUpdate(strSql);
                }
            }
            //dm
            else if (dbConfig.getUrl().indexOf(DbType.DM.getDb()) > 0) {
                strSql = "";
                stmt.executeUpdate(strSql);
                var6 = tableInfo.getListColumn().iterator();

                while (var6.hasNext()) {
                    item = (ColumnInfo) var6.next();
                    strSql = "";
                    stmt.executeUpdate(strSql);
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                }
            }
        } catch (SQLException var17) {
            throw new RuntimeException("execute sql occer error", var17);
        } finally {
            try {
                conn.close();
            } catch (Exception var15) {
                throw new RuntimeException(var15);
            }
        }

    }

    @Override
    public List<TableInfo> getAllTables(DbConfig dbConfig) {
        List<TableInfo> tableList = new ArrayList();
        Connection conn = this.getConnection(dbConfig);
        try {
            Statement stmt = conn.createStatement();
            String strSql = "";
            //mysql
            if (dbConfig.getUrl().indexOf(DbType.MYSQL.getDb()) > 0) {
                strSql = "select table_name,table_comment from information_schema.tables where table_schema='" + dbConfig.getSchema() + "'";
            }
            //oracle
            else if (dbConfig.getUrl().indexOf(DbType.ORACLE.getDb()) > 0) {
                strSql = "select table_name,comments from user_tab_comments where table_type='TABLE' order by table_name";
            }
            //db2
            else if (dbConfig.getUrl().indexOf(DbType.DB2.getDb()) > 0) {
                strSql = "select TABNAME,REMARKS from syscat.tables where OWNER='"+dbConfig.getUsername().toUpperCase()+"' and TABSCHEMA='" + dbConfig.getSchema() + "'";
            }
            //sqlserver
            else if (dbConfig.getUrl().indexOf(DbType.SQL_SERVER.getDb()) > 0) {
                strSql = "";
            }
            //mariadb
            else if (dbConfig.getUrl().indexOf(DbType.MARIADB.getDb()) > 0) {
                strSql = "";
            }
            //h2
            else if (dbConfig.getUrl().indexOf(DbType.H2.getDb()) > 0) {
                strSql = "";
            }
            //hsql
            else if (dbConfig.getUrl().indexOf(DbType.HSQL.getDb()) > 0) {
                strSql = "";
            }
            //postgresql
            else if (dbConfig.getUrl().indexOf(DbType.POSTGRE_SQL.getDb()) > 0) {
                strSql = "";
            }
            //dm
            else if (dbConfig.getUrl().indexOf(DbType.DM.getDb()) > 0) {
                strSql = "";
            }

            System.out.println(">>>>>>>>>>>>" + strSql);
            ResultSet rs = stmt.executeQuery(strSql);

            while (rs.next()) {
                TableInfo table = new TableInfo();
                table.setTableName(rs.getString(1));
                table.setComments(rs.getString(2));
                tableList.add(table);
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException var16) {
                    var16.printStackTrace();
                }
            }
        } catch (SQLException var17) {
            throw new RuntimeException("execute sql occer error", var17);
        } finally {
            try {
                conn.close();
            } catch (Exception var15) {
                throw new RuntimeException(var15);
            }
        }

        return tableList;
    }

    @Override
    public TableInfo getAllColumns(String tableName, DbConfig dbConfig) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        Connection conn = this.getConnection(dbConfig);

        try {
            Statement stmt = conn.createStatement();
            String strSql = "";
            //mysql
            if (dbConfig.getUrl().indexOf(DbType.MYSQL.getDb()) > 0) {
                strSql = "select TABLE_COMMENT from information_schema.tables where table_name='" + tableName + "' and table_schema='" + dbConfig.getSchema() + "'";
            }
            //oracle
            else if (dbConfig.getUrl().indexOf(DbType.ORACLE.getDb()) > 0) {
                strSql = "select comments from user_tab_comments where table_name='" + tableName + "'";
            }
            //db2
            else if (dbConfig.getUrl().indexOf(DbType.DB2.getDb()) > 0) {
                strSql = "select REMARKS from syscat.tables where TABNAME='" + tableName + "'  and  TABSCHEMA='"+dbConfig.getSchema()+"'";
            }
            //sqlserver
            else if (dbConfig.getUrl().indexOf(DbType.SQL_SERVER.getDb()) > 0) {
                strSql = "";
            }
            //mariadb
            else if (dbConfig.getUrl().indexOf(DbType.MARIADB.getDb()) > 0) {
                strSql = "";
            }
            //h2
            else if (dbConfig.getUrl().indexOf(DbType.H2.getDb()) > 0) {
                strSql = "";
            }
            //hsql
            else if (dbConfig.getUrl().indexOf(DbType.HSQL.getDb()) > 0) {
                strSql = "";
            }
            //postgresql
            else if (dbConfig.getUrl().indexOf(DbType.POSTGRE_SQL.getDb()) > 0) {
                strSql = "";
            }
            //dm
            else if (dbConfig.getUrl().indexOf(DbType.DM.getDb()) > 0) {
                strSql = "";
            }

            ResultSet rs = stmt.executeQuery(strSql);

            while (rs.next()) {
                tableInfo.setComments(rs.getString(1));
            }
            //mysql
            if (dbConfig.getUrl().indexOf(DbType.MYSQL.getDb()) > 0) {
                strSql = "select column_name,column_comment,data_type,CHARACTER_MAXIMUM_LENGTH from Information_schema.columns where table_Name = '" + tableName + "' and table_schema='" + dbConfig.getSchema() + "'";
            }
            //oracle
            else if (dbConfig.getUrl().indexOf(DbType.ORACLE.getDb()) > 0) {
                strSql = "select z.COLUMN_NAME,c.comments,z.data_type from user_tab_columns z,user_col_comments c where z.TABLE_NAME=c.table_name and z.COLUMN_NAME=c.column_name and z.Table_Name='" + tableName + "'";
            }
            //db2
            else if (dbConfig.getUrl().indexOf(DbType.DB2.getDb()) > 0) {
                strSql = "select name,REMARKS,COLTYPE,LENGTH from sysibm.syscolumns where tbname = '" + tableName + "'";
            }
            //sqlserver
            else if (dbConfig.getUrl().indexOf(DbType.SQL_SERVER.getDb()) > 0) {
                strSql = "";
            }
            //mariadb
            else if (dbConfig.getUrl().indexOf(DbType.MARIADB.getDb()) > 0) {
                strSql = "";
            }
            //h2
            else if (dbConfig.getUrl().indexOf(DbType.H2.getDb()) > 0) {
                strSql = "";
            }
            //hsql
            else if (dbConfig.getUrl().indexOf(DbType.HSQL.getDb()) > 0) {
                strSql = "";
            }
            //postgresql
            else if (dbConfig.getUrl().indexOf(DbType.POSTGRE_SQL.getDb()) > 0) {
                strSql = "";
            }
            //dm
            else if (dbConfig.getUrl().indexOf(DbType.DM.getDb()) > 0) {
                strSql = "";
            }

            List<ColumnInfo> colList = new ArrayList();

            ColumnInfo colInfo;
            for (rs = stmt.executeQuery(strSql); rs.next(); colList.add(colInfo)) {
                colInfo = new ColumnInfo();
                colInfo.setColName(rs.getString(1));
                colInfo.setComments(rs.getString(2));
                if (dbConfig.getUrl().indexOf("mysql") > 0 && "varchar".equalsIgnoreCase(rs.getString(3))) {
                    colInfo.setColType(rs.getString(3) + "(" + rs.getString(4) + ")");
                } else {
                    colInfo.setColType(rs.getString(3));
                }
            }

            tableInfo.setListColumn(colList);
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException var18) {
                    var18.printStackTrace();
                }
            }
        } catch (SQLException var19) {
            throw new RuntimeException("execute sql occer error", var19);
        } finally {
            try {
                conn.close();
            } catch (Exception var17) {
                throw new RuntimeException(var17);
            }
        }

        return tableInfo;
    }

    private Connection getConnection(DbConfig dbConfig) {
        Connection con = null;

        try {
            Class.forName(dbConfig.getDriver());
            con = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
        } catch (Exception var4) {
            System.out.println("数据库连接失败" + var4.getMessage());
        }

        return con;
    }

    @Override
    public String testConnection(DbConfig dbConfig) {
        Connection con = null;

        try {
            Class.forName(dbConfig.getDriver());
            con = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
            return con == null ? "数据库连接失败" : null;
        } catch (Exception var4) {
            System.out.println("数据库连接失败：" + var4.getLocalizedMessage());
            return var4.getLocalizedMessage() + "error";
        }
    }
}
