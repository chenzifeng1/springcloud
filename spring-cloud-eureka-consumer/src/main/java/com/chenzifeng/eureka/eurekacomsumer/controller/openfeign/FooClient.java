package com.chenzifeng.eureka.eurekacomsumer.controller.openfeign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.controller.openfeign
 * @ClassName: FooClient
 * @Author: czf
 * @Description: 如果我们想配置多个Feign客户端指向同一个服务，那么我们需要让这多个Feign的name或者url相同，但是contextId必须区别开来，避免配置bean时冲突
 * @Date: 2020/12/25 19:44
 * @Version: 1.0
 */
//@FeignClient(contextId ="fooClient", name = "provider" )
public interface FooClient {

}
