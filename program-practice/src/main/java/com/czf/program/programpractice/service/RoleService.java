package com.czf.program.programpractice.service;

import com.czf.program.programpractice.entity.Role;
import com.czf.program.programpractice.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.service
 * @ClassName: RoleService
 * @Author: czf
 * @Description: 用户角色
 * @Date: 2020/11/23 16:19
 * @Version: 1.0
 */
public interface RoleService {

    /**
     * 根据id查找role
     * @param id
     * @return
     */
    Role findRoleById(@Param(value="id") int id);

    /**
     * 查找用户所拥有的角色
     * @param user
     * @return
     */
    List<Role> findRolesByUser(User user);


}
