package com.chenzifeng.spring.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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
    private String iphone;

    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
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

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }
}
