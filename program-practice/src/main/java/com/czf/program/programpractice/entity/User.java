package com.czf.program.programpractice.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.entity
 * @ClassName: User
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/16 18:22
 * @Version: 1.0
 */
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 用户状态
     */
    private int status;
    /**
     * 用户拥有的角色列表
     */
    private List<Role> roleList;

    /**
     * 用户拥有的权限列表
     */
    private List<Permission> permissionList;

    public User(String username, String password, String email, String headImg) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.headImg = headImg;
    }

    public User(String username, String password, String email, String telephone, String headImg) {
        this(username, password, email, headImg);
        this.telephone = telephone;
    }

    public User(String username, String password, String email, String telephone, String headImg, int status) {
        this(username, password, email, telephone, headImg);
        this.status = status;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

}
