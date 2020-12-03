package com.czf.program.programpractice.mapper;

import com.czf.program.programpractice.entity.Role;
import com.czf.program.programpractice.entity.User;

import java.util.List;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.mapper
 * @ClassName: UserMapper
 * @Author: czf
 * @Description: 用户角色
 * @Date: 2020/11/16 19:16
 * @Version: 1.0
 */
public interface RoleMapper {
    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入Role
     *
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 属性有选择的插入
     *
     * @param record
     * @return
     */
    int insertSelective(Role record);

    /**
     * 根据主键id选择
     *
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 根据role的属性进行更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 根据id进行更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);

    /**
     * 查询用户的角色
     *
     * @param user
     * @return
     */
    List<Role> selectRolesByUser(User user);
}