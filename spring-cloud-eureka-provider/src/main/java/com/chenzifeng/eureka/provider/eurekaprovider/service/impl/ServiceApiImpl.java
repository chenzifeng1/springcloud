package com.chenzifeng.eureka.provider.eurekaprovider.service.impl;

import com.chenzifeng.eureka.provider.eurekaprovider.serviceApi.ProviderConsumeApi;
import com.chenzifeng.eureka.provider.eurekaprovider.serviceApi.UserAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: eureka-provider
 * @Package: com.chenzifeng.eureka.provider.eurekaprovider.service
 * @ClassName: ServiceApiImpl
 * @Author: czf
 * @Description: Service Api的实现类
 * @Date: 2020/12/20 11:14
 * @Version: 1.0
 */
@Service
public class ServiceApiImpl implements ProviderConsumeApi {


    private static final Map<Integer, UserAccount> userMap = new ConcurrentHashMap<>();

    /**
     * 初始化user列表，代替mysql查询或者缓存查询
     */
    static {
        for (int i = 0; i < 5; i++) {
            userMap.put(i, new UserAccount(i, "test" + i, i % 3, String.format(UserAccount.emailFormat, i)));
        }
    }

    @Override
    public UserAccount getUserInfo(Integer userId) {
        return userMap.get(userId);
    }

    @Override
    public List<UserAccount> getUserIdList() {
        List<UserAccount> users = new ArrayList<>();
        userMap.forEach((userId, user) -> {
            users.add(user);
        });
        return users;
    }

    @Override
    public String addUser(UserAccount user) {
        if (userMap.containsKey(user.getId())) {
            return "用户信息已存在";
        } else {
            userMap.put(user.getId(), user);
            return "添加成功";
        }
    }

    @Override
    public String exceptionRequestTest() {
        int i = 1 / 0;
        return "出现异常";
    }
}
