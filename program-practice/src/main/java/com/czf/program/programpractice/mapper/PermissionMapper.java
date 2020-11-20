package com.czf.program.programpractice.mapper;

import com.czf.program.programpractice.entity.Permission;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.mapper
 * @ClassName: UserMapper
 * @Author: czf
 * @Description: 用户权限
 * @Date: 2020/11/16 19:16
 * @Version: 1.0
 */
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