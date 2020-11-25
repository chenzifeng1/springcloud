package com.czf.program.programpractice.controller;

import com.alibaba.fastjson.JSONObject;
import com.czf.program.programpractice.entity.Role;
import com.czf.program.programpractice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.controller
 * @ClassName: RoleController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/23 16:18
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    public JSONObject addRole(JSONObject jsonObject) {

        roleService.addRole(new Role());
        return new JSONObject();
    }
}
