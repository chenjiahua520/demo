package com.generator.common.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjiahua
 * @version 2019-02-11 11:30
 */
public class JavaTypeResolver {
    
    private static Map<String, String> typeMap = new HashMap<>();
    
    static {
        // Integer
        typeMap.put("TINYINT", Boolean.class.getSimpleName());
        typeMap.put("SMALLINT", Short.class.getSimpleName());
        typeMap.put("MEDIUMINT", Short.class.getSimpleName());
        typeMap.put("INT", Integer.class.getSimpleName());
        typeMap.put("BIGINT", Long.class.getSimpleName());
        typeMap.put("BIT", Long.class.getSimpleName());
        // Real实数
        typeMap.put("FLOAT", Double.class.getSimpleName());
        typeMap.put("DOUBLE", Double.class.getSimpleName());
        typeMap.put("DECIMAL", Double.class.getSimpleName());
        typeMap.put("NUMERIC", Double.class.getSimpleName());
        // Text
        typeMap.put("CHAR", String.class.getSimpleName());
        typeMap.put("VARCHAR", String.class.getSimpleName());
        typeMap.put("TINYTEXT", String.class.getSimpleName());
        typeMap.put("TEXT", String.class.getSimpleName());
        typeMap.put("MEDIUMTEXT", String.class.getSimpleName());
        typeMap.put("LONGTEXT", String.class.getSimpleName());
        typeMap.put("JSON", String.class.getSimpleName());
        // 二进制文件
        typeMap.put("BINARY", "byte[]");
        typeMap.put("BLOB", "byte[]");
        typeMap.put("VARBINARY", "byte[]");
        typeMap.put("TINYBLOB", "byte[]");
        typeMap.put("MEDIUMBLOB", "byte[]");
        typeMap.put("LONGBLOB", "byte[]");
        // Time
        typeMap.put("DATE", Date.class.getSimpleName());
        typeMap.put("DATETIME", Date.class.getSimpleName());
        typeMap.put("TIME", Date.class.getSimpleName());
        typeMap.put("TIMESTAMP", Date.class.getSimpleName());
        typeMap.put("YEAR", Date.class.getSimpleName());
        // Other
        typeMap.put("ARRAY", Object.class.getSimpleName());
        typeMap.put("BOOLEAN", Boolean.class.getSimpleName());
        typeMap.put("CLOB", String.class.getSimpleName());
        typeMap.put("DATALINK", Object.class.getSimpleName());
        typeMap.put("DISTINCT", Object.class.getSimpleName());
        typeMap.put("JAVA_OBJECT", Object.class.getSimpleName());
        typeMap.put("LONGNVARCHAR", String.class.getSimpleName());
        typeMap.put("LONGVARBINARY", "byte[]");
        typeMap.put("LONGVARCHAR", String.class.getSimpleName());
        typeMap.put("NCHAR", String.class.getSimpleName());
        typeMap.put("NCLOB", String.class.getSimpleName());
        typeMap.put("NVARCHAR", String.class.getSimpleName());
        typeMap.put("NULL", Object.class.getSimpleName());
        typeMap.put("OTHER", Object.class.getSimpleName());
        typeMap.put("REAL", Float.class.getSimpleName());
        typeMap.put("REF", Object.class.getSimpleName());
        typeMap.put("STRUCT", Object.class.getSimpleName());
    }

    public static String getJavaType(String jdbcType) {
        return typeMap.getOrDefault(jdbcType, String.class.getSimpleName());
    }
    
}
