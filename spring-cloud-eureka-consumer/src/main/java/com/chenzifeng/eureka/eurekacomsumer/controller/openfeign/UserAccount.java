package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import lombok.Data;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: UserAccount
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/21 11:10
 * @Version: 1.0
 */
@Data
public class UserAccount {
    private int id;
    private String accountName;
    private int accountStatus;
    private String email;
    private static volatile UserAccount userAccount;

    public static String emailFormat = "testUser%1$s@qq.com";

    public UserAccount(int id, String accountName, int accountStatus, String email) {
        this.id = id;
        this.accountName = accountName;
        this.accountStatus = accountStatus;
        this.email = email;
    }

    public UserAccount() {
    }

    /**
     * 处理降级返回的信息
     *
     * @return
     */
    public static UserAccount getInstanceAccount() {
        if (userAccount == null) {
            synchronized (UserAccount.class) {
                if (userAccount == null) {
                    userAccount = new UserAccount(-1, "降级处理User", -1, "777777@gmail");
                }
            }

        }
        return userAccount;
    }
}
