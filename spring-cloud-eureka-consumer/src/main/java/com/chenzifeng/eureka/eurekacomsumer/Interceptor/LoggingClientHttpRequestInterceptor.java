package com.chenzifeng.eureka.eurekacomsumer.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.Interceptor
 * @ClassName: LoggingClientHttpRequestInterceptor
 * @Author: czf
 * @Description: 拦截器 使用的话需要把该类的实例加到RestTemplate实例的Interceptor中
 * 该拦截器拦截的是打向controller请求，而非服务之间的调用请求
 * @Date: 2020/12/17 16:43
 * @Version: 1.0
 */
@Slf4j
@Component
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        log.info("拦截到的请求："+httpRequest.getURI().toString());
        //在这里发起请求调用，获取调用之后的响应信息
        ClientHttpResponse clientHttpResponse = clientHttpRequestExecution.execute(httpRequest,bytes);
        return clientHttpResponse;
    }
}
