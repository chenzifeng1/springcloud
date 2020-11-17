package com.czf.program.programpractice.mapper;

import com.czf.program.programpractice.entity.Permission;

public interface PermissionMapper {
    /**
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @param record
     * @return
     */
    int insert(Permission record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(Permission record);

    /**
     *
     * @param id
     * @return
     */
    Permission selectByPrimaryKey(Integer id);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Permission record);
}