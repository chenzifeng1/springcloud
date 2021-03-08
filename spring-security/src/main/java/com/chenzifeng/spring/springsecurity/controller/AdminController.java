package com.chenzifeng.spring.springsecurity.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import com.chenzifeng.spring.springsecurity.service.MyUserService;
import com.chenzifeng.spring.springsecurity.service.impl.MyUserServiceImpl;
import com.chenzifeng.spring.springsecurity.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author czf
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    /**
     * 这个注解只能实现 或 的关系，表示有其中的一个角色即可。不能实现 并 的关系
     */
    @GetMapping("/hi")
    @Secured("{ROLE_admin，ROLE_user}")
    public JSONObject hi() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "hi");
        jsonObject.put("kind", "admin||user");
        return jsonObject;
    }


    @GetMapping("/info")
    @PreAuthorize("hasRole('ROLE_admin')")
    public JSONObject info() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "info");
        jsonObject.put("kind", "admin");
        return jsonObject;
    }


    @GetMapping("/info2")
    @PreAuthorize("hasAnyRole('ROLE_admin','ROLE_user')")
    public JSONObject info2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "info2");
        jsonObject.put("kind", "admin");
        return jsonObject;
    }

    /**
     * and 关系
     *
     * @return
     */
    @GetMapping("/info3")
    @PreAuthorize("hasRole('ROLE_admin') AND hasRole('ROLE_user')")
    public JSONObject info3() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "info3");
        jsonObject.put("kind", "admin");
        return jsonObject;
    }

    @GetMapping("/info4")
    /**
     * 使用于跨系统，或者无状态的访问
     */
    @PostAuthorize("returnObject==1")
    public int info4() {
        System.out.println("条件访问");
        return new Random().nextInt(2);
    }


}
