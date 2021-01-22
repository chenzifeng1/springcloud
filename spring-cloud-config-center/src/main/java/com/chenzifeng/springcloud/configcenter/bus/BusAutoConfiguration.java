package com.chenzifeng.springcloud.configcenter.bus;

/**
 * @ProjectName: config-center
 * @Package: com.chenzifeng.springcloud.configcenter
 * @ClassName: BusAutoConfiguration
 * @Author: czf
 * @Description:
 * 1. 提交代码触发post给Server端发送bus/refresh
 * 2. Server端接收到请求并发送给Spring Cloud Bus
 * 3. Spring Cloud bus接到消息并通知给其它客户端
 * 4. 其它客户端接收到通知，请求Server端获取最新配置
 * 5. 全部客户端均获取到最新的配置
 * @Date: 2021/1/22 15:51
 * @Version: 1.0
 */
public class BusAutoConfiguration {
}
