package com.czf.program.programpractice.mapper;

import com.czf.program.programpractice.entity.RolePermission;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.mapper
 * @ClassName: UserMapper
 * @Author: czf
 * @Description: 角色-权限关联
 * @Date: 2020/11/16 19:16
 * @Version: 1.0
 */
public interface RolePermissionMapper {
    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入关联
     * @param record
     * @return
     */
    int insert(RolePermission record);

    /**
     * 选择性插入
     * @param record
     * @return
     */
    int insertSelective(RolePermission record);

    /**
     * 根据主键查找
     * @param id
     * @return
     */
    RolePermission selectByPrimaryKey(Integer id);

    /**
     * 根据条件查询
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RolePermission record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(RolePermission record);
}