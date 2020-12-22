package com.chenzifeng.eureka.provider.eurekaprovider.serviceApi;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * @ProjectName: eureka-provider
 * @Package: com.chenzifeng.eureka.provider.eurekaprovider.serviceApi
 * @ClassName: ProviderConsumeApi
 * @Author: czf
 * @Description: provider提供给调用方的接口，
 * @Date: 2020/12/20 10:57
 * @Version: 1.0
 */
public interface ProviderConsumeApi  {

    /**
     * 获取用户id
     * @param userId
     * @return
     */
     UserAccount getUserInfo(Integer userId);

    /**
     * 获取用户列表
     * @return
     */
    List<UserAccount> getUserIdList();

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    String addUser(UserAccount user);

}
