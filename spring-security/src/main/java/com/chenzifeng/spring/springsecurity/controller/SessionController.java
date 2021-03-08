package com.chenzifeng.spring.springsecurity.controller;


import com.sun.javafx.collections.MappingChange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.controller
 * @ClassName: SessionController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/3/5 10:02
 * @Version: 1.0
 */
@RestController
public class SessionController {

    @GetMapping("/list")
    public String list() {
        return "redis list";
    }

    @GetMapping("/get_all")
    public HashMap<String, Object> getSessionAttributes(HttpSession httpSession) {
        HashMap<String, Object> hashMap = new HashMap<>();

        Enumeration<String> attributeNames = httpSession.getAttributeNames();

        for (; attributeNames.hasMoreElements(); ) {
            String name = attributeNames.nextElement();
            hashMap.put(name, httpSession.getAttribute(name));
        }

        return hashMap;
    }
}
