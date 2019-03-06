package com.generator.common.util;

import com.generator.common.element.ColumnElement;
import com.generator.common.element.TableElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * JDBC工具类
 *
 * @author chenjiahua
 * @version 2018-11-21 15:40
 */
public class JdbcUtil {

    private Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

    /**
     * 数据库名称
     */
    public String database;

    /**
     * 定义数据库的链接
     */
    private Connection connection;

    /**
     * 初始化
     *
     */
    public JdbcUtil() {
        try {
            // 获取jdbc连接信息
            Properties jdbcProperties = new Properties();
            jdbcProperties.load(ClassLoader.getSystemResourceAsStream("jdbc.properties"));
            String driver = jdbcProperties.getProperty("generator.jdbc.driver");
            String url = jdbcProperties.getProperty("generator.jdbc.url");
            String username = jdbcProperties.getProperty("generator.jdbc.username");
            String password = jdbcProperties.getProperty("generator.jdbc.password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            database = connection.getCatalog();
            logger.info("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据库连接失败, {}", e);
        }
    }

    /**
     * 获取数据库的表,不带字段信息
     *
     * @param tablePrefix 表名前缀
     * @return
     * @throws SQLException SQLException
     */
    public List<TableElement> getTables(String tablePrefix) throws SQLException {
        // 获取数据库表
        ResultSet result = connection.getMetaData().getTables(database, null, tablePrefix + "%", new String[]{"TABLE"});
        List<TableElement> tableList = new ArrayList<>(result.getRow());
        TableElement table;
        while (result.next()) {
            table = new TableElement();
            table.setTableRemarks(result.getString("REMARKS"));
            String tableName = result.getString("TABLE_NAME");
            String tableNameShort = tableName.replace(tablePrefix, "");
            table.setTableName(tableName);
            table.setAlias(tableNameShort.substring(0, 1));
            table.setJavaClassName(StringUtil.lineToHump(tableNameShort));
            table.setJavaClassNameAtRuntime(StringUtil.toLowerCaseFirstOne(table.getJavaClassName()));
            tableList.add(table);
        }
        close(result, null, null);
        return tableList;
    }

    /**
     * 获取数据库的表,带字段信息
     *
     * @param tablePrefix 表名前缀
     * @return
     * @throws SQLException SQLException
     */
    public List<TableElement> getTablesWithColumns(String tablePrefix) throws SQLException {
        List<TableElement> tableList = getTables(tablePrefix);
        for (TableElement table : tableList) {
            List<ColumnElement> columnList = getColumns(table.getTableName());
            table.setColumnList(columnList);
        }
        return tableList;
    }

    /**
     * 获取表的列
     *
     * @param tableName 表名
     * @return
     * @throws SQLException SQLException
     */
    public List<ColumnElement> getColumns(String tableName) throws SQLException {
        ResultSet result = connection.getMetaData().getColumns(null, null, tableName, null);
        List<ColumnElement> columnList = new ArrayList<>(result.getRow());
        ColumnElement column;
        while (result.next()) {
            column =  new ColumnElement();
            column.setColumnName(result.getString("COLUMN_NAME"));
            column.setJdbcType(result.getString("TYPE_NAME"));
            column.setColumnRemarks(result.getString("REMARKS"));
            column.setJavaType(JavaTypeResolver.getJavaType(column.getJdbcType()));
            column.setJavaFieldNameAtRuntime(StringUtil.lineToHump(column.getColumnName()));
            column.setJavaFieldName(StringUtil.toLowerCaseFirstOne(column.getJavaFieldNameAtRuntime()));
            columnList.add(column);
        }
        close(result, null, null);
        return columnList;
    }

    /**
     * 更新数据
     *
     * @param sql    sql
     * @param params 参数
     * @return int 影响行数
     * @throws SQLException SQLException
     */
    public int updateByParams(String sql, Object... params) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        // 填充sql语句中的占位符
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        int n = pstmt.executeUpdate();
        close(null, pstmt, null);
        return n;
    }

    /**
     * 查询多条记录
     *
     * @param sql    sql
     * @param params 参数
     * @return list
     * @throws SQLException SQLException
     */
    public List<Map<String, Object>> selectByParams(String sql, Object... params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        // 填充sql语句中的占位符
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int colsLen = metaData.getColumnCount();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>(colsLen);
            for (int i = 0; i < colsLen; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Object columnValue = rs.getObject(columnName);
                map.put(columnName, columnValue);
            }
            list.add(map);
        }
        close(rs, pstmt, null);
        return list;
    }

    /**
     * 关闭资源
     *
     * @param result     result
     * @param pstms      pstms
     * @param connection connection
     */
    private void close(ResultSet result, PreparedStatement pstms, Connection connection) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("结果集关闭失败");
        }
        try {
            if (pstms != null) {
                pstms.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("预编译关闭失败");
        }
        try {
            if (connection != null) {
                connection.close();
                logger.info("数据库连接关闭成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("数据库连接关闭失败");
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        close(null, null, connection);
    }

}
