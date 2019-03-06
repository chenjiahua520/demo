<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${project.daoPackage}.${javaClassName}Dao">

    <sql id="${javaClassName}Columns">
		<#list columnList as c>
        ${alias}.${c.columnName} AS "${c.javaFieldName}"<#if c?has_next>,</#if>
        </#list>
    </sql>

    <select id="get" resultType="${javaClassName}">
        SELECT
        <include refid="${javaClassName}Columns"/>
        FROM ${tableName} ${alias}
        WHERE ${alias}.id = ${"#"}{id}
    </select>

    <select id="findList" resultType="${javaClassName}">
        SELECT
        <include refid="${javaClassName}Columns"/>
        FROM ${tableName} ${alias}
        <where>
			<#if table.isExistDeleteFlag>${alias}.delete_flag = 0</#if>
        </where>
        ORDER BY ${alias}.create_date DESC
    </select>

    <insert id="insert">
        INSERT INTO ${tableName}(
		<#list columnList as c>
            ${c.columnName}<#if c?has_next>,</#if>
        </#list>
        ) VALUES (
		<#list columnList as c>
            ${"#"}{${c.javaFieldName}}<#if c?has_next>,</#if>
        </#list>
        )
    </insert>

    <update id="update">
        UPDATE ${tableName} SET
		<#list columnList as c>
            ${c.columnName} = ${"#"}{${c.javaFieldName}}<#if c?has_next>,</#if>
        </#list>
        WHERE id = ${"#"}{id}
    </update>

    <!--物理删除-->
    <delete id="delete">DELETE FROM ${tableName} WHERE id = ${"#"}{id}</delete>

    <!--逻辑删除-->
    <delete id="deleteByLogic">UPDATE ${tableName} SET delete_flag = 1 WHERE id = ${"#"}{id}</delete>

</mapper>