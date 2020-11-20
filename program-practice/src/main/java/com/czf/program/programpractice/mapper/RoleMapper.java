package com.czf.program.programpractice.mapper;

import com.czf.program.programpractice.entity.Role;

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
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}