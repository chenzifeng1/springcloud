package com.chenzifeng.serviceapi.serviceapi.model;

import lombok.Data;

/**
 * @ProjectName: service-api
 * @Package: com.chenzifeng.serviceapi.serviceapi.model
 * @ClassName: UserAccount
 * @Author: czf
 * @Description: 基类 暂时不使用
 * @Date: 2020/12/21 16:12
 * @Version: 1.0
 */
@Data
public class UserAccount {

    private int id;
    private String accountName;
    private int accountStatus;
    private String email;

    public static String emailFormat = "testUser%1$s@qq.com";

    public UserAccount(int id, String accountName, int accountStatus, String email) {
        this.id = id;
        this.accountName = accountName;
        this.accountStatus = accountStatus;
        this.email = email;
    }

    public UserAccount() {
    }

}
