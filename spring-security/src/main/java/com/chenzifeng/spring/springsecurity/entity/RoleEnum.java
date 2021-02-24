package com.chenzifeng.spring.springsecurity.entity;

/**
 * 角色名的枚举
 * @author czf
 */
public enum RoleEnum {
    /**
     * 用户
     */
    USER("user"),
    ADMIN("admin"),
    GUEST("guest");

    /**
     * 角色名
     *
     */
    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
