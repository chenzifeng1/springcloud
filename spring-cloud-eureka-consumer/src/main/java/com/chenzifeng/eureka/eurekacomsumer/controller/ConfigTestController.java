package com.chenzifeng.eureka.eurekacomsumer.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller
 * @ClassName: ConfigTestController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/1/7 18:50
 * @Version: 1.0
 */
@RefreshScope
@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigTestController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String REFRESH_URL = "/actuator/refresh";

    @Value("${myconfig.version_no}")
    private String version;

    @Value("${myconfig.label}")
    private String label;
    @Value("${spring.application.name}")
    private String serverName;
    @Value("${server.port}")
    private int port;

    @GetMapping("/test")
    public String test(){
        String str = String.format("从配置中心的获取的配置信息,label:%s,version:%s",label,version);
        return str;
    }

    @GetMapping("/refresh")
    public JSONObject refresh() {
        JSONObject result = new JSONObject();
        String url = "http://127.0.0.1:"+port+REFRESH_URL;
        String urlServe = "http://"+serverName+":"+port+REFRESH_URL;

        log.info("请求地址："+url);
        HttpHeaders headers = new HttpHeaders();
        //设置请求头
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(result, headers);
        String string =  restTemplate.postForObject(urlServe,requestEntity,String.class);
        result.put("result",string);
        return result;
    }

}
