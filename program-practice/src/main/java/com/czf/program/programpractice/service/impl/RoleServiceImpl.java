package com.czf.program.programpractice.service.impl;

import com.czf.program.programpractice.entity.Role;
import com.czf.program.programpractice.entity.User;
import com.czf.program.programpractice.mapper.RoleMapper;
import com.czf.program.programpractice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.service.impl
 * @ClassName: RoleServiceImpl
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/23 16:22
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Override
    public Role findRoleById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("RoleId请求异常");
        }
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> findRolesByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("用户信息异常");
        }
        return roleMapper.selectRolesByUser(user);
    }

    @Override
    public boolean addRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("添加角色失败，检查角色信息是否完整");
        }
        return roleMapper.insert(role)==1;
    }
}
