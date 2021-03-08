package com.chenzifeng.spring.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.entity
 * @ClassName: MyUser
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/21 13:49
 * @Version: 1.0
 */
@TableName("user")
public class MyUser extends User {

    private String email;
    private String headImg;
    private String phone;

    private String password;

    private String username;

    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
    }

    public MyUser() {
        super("user", "password", Collections.singleton(new SimpleGrantedAuthority("guest")));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
