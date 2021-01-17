package com.chenzifeng.eureka.eurekacomsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.service
 * @ClassName: RestTemplateServiceImpl
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/1/17 11:38
 * @Version: 1.0
 */
@Service
public class   RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    public <T> T sendPost(String url, Class<T> responseType){
        return null;
    }
}
