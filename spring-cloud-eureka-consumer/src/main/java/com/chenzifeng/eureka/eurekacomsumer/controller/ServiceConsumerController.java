package com.chenzifeng.eureka.eurekacomsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller
 * @ClassName: ServiceConsumerController
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/12/8 10:47
 * @Version: 1.0
 */
@RestController
@RequestMapping("/service")
public class ServiceConsumerController {

    private static String DEFAULT_RESPONSE_STRING = "success";

    /**
     * @Qualifier 这个注解是当我们是 @Autowired 时 如果注入的接口有多个实现类，那么我们可以通过这个 @Qualifier 注解来指定使用哪个实现类
     * 该注解的参数是 @Service 指定的name
     */
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;




    @Autowired
    private LoadBalancerClient lbc;

    @Autowired
    RestTemplate restTemplate;


    /**
     * 一个最简单模式的服务调用
     * 只是用RestTemplate来完成请求调用，不涉及到负载均衡，需要硬编码服务请求地址
     * 耦合度极高
     *
     * @return
     */
    @GetMapping("/hi")
    public String getHi() {
        //根据instanceId来获取服务实例
        //List<InstanceInfo> instances = eurekaClient.getInstancesById("DESKTOP-3DF2VN4.mshome.net:chenzifeng-spring-cloud-eureka-client-1:8081");

        //根据服务名来获取服务注册中心的实例 服务名可能对应多个实例，因此获取的是一个实例的列表
        List<InstanceInfo> instanceInfos = eurekaClient.getInstancesByVipAddress("CHENZIFENG-SPRING-CLOUD-EUREKA-CLIENT-1", false);
        System.out.println("获取服务名称");
        // 使用RestTemplate来进行远程服务调用
        RestTemplate template = new RestTemplate();
        instanceInfos.forEach(instanceInfo -> {
            String address = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/user/getHi";
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                //如果服务节点是up状态则可以进行调用服务
                System.out.println(address);
                // 如果服务返回的是一个对象，我们可以在这里定义好对象来接收，但是估计要包名相同，所以可以传递json
                String obj = template.getForObject(address, String.class);
                System.out.println(obj);
            }
        });
        return DEFAULT_RESPONSE_STRING;
    }

    /**
     * 使用负载均衡来完成服务调用 LoadBalancerClient 会过滤Status为DOWN的服务实例，然后在剩余的实例中选取实例来提供服务
     * lbc实例是自动注入的，bean方法在启动类中，加了 @LoadBalanced 注解
     * @return
     */
    @GetMapping("/hi1")
    public Object getHi1() {
        ServiceInstance instance = lbc.choose("provider");
        if (instance == null) {
            return "No servers available for service: ";
        }
        String address = "http://" + instance.getHost() + ":" + instance.getPort() + "/service-help/port";
        return restTemplate.getForObject(address, String.class) + " port:" + instance.getPort();
    }


    @GetMapping("/hi2")
    public Object getHi2(@RequestParam("consumer") String consumer) {
        // http://服务名[spring.application.name]/资源名[请求url]
        String address = "http://provider/service-help/port?consumer={1}";
        return restTemplate.getForObject(address, String.class, consumer);
    }
}
