package com.generator.common.base;

import java.util.List;

/**
 * @author chenjiahua
 * @version 2018-11-02 13:49
 */
public interface BaseDao<T> {

    /**
     * 获取单条数据
     *
     * @param id id
     * @return T
     */
    T get(String id);

    /**
     * 获取单条数据
     *
     * @param entity element
     * @return T
     */
    T get(T entity);

    /**
     * 查询数据列表
     *
     * @param entity element
     * @return T
     */
    List<T> findList(T entity);

    /**
     * 插入数据
     *
     * @param entity element
     * @return int
     */
    int insert(T entity);

    /**
     * 更新数据
     *
     * @param entity element
     * @return int
     */
    int update(T entity);

    /**
     * 删除数据（物理删除，从数据库中彻底删除）
     *
     * @param id id
     * @return int
     */
    int delete(String id);

    /**
     * 删除数据（逻辑删除，将数据隐藏）
     *
     * @param id id
     * @return int
     */
    int deleteByLogic(String id);

    /**
     * 删除数据（物理删除，从数据库中彻底删除）
     *
     * @param entity element
     * @return int
     */
    int delete(T entity);

    /**
     * 删除数据（逻辑删除，将数据隐藏）
     *
     * @param entity element
     * @return int
     */
    int deleteByLogic(T entity);

}
