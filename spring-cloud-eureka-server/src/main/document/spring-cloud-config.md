# spring cloud config(配置中心)
应用场景：假设我们现在有一个微服务集群，里面有多个服务集群。如果我们改了某个服务的配置文件，
要想使其生效，需要手动把每个服务节点上的配置文件替换，重新启动。服务集群越多，节点越多工作量越大。
所以我们可以配置一个服务来做`配置中心`。
## 配置中心
配置中心其实就是自动帮我们完成替换配置文件，然后进行服务热加载的过程。  
![配置中心](../img/配置中心.PNG)
配置中心可以视作一个微服务，只不过这个微服务是为其他微服务提供公用服务的。
所以我们会把配置中心当作一个服务向eureka注册一下。其他的服务如果想通过配置中心来做到配置更新的话，那么必须
要知道去哪里找这个配置中心，也就是说配置中心必须要提供一个地址来供其他服务方法。
而其他服务也要配置上这个地址，当然既然配置中心已经注册为微服务了，我们也可以通过配置让eureka来帮我们定位，
这样在配置中心的地址即使发生了改变，只要他在eureka上注册了，其他服务还是可以访问到的。
## 实战
### 准备：  
1.创建一个项目作为配置中心  
2.创建一个git仓库作为配置文件的远程仓库。  

### 依赖
我们需要引入`spring-cloud-config-server`和`spring-cloud-starter-netflix-erueka`的两个依赖
依赖如下：
```xml
  <dependencies>
  
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        
  </dependencies>
```
### 配置
我们需要在启动类上加上注解`@EnableConfigServer`，来告诉spring容器初始化一些配置。
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }

}
```

另外我们需要在配置文件中配置一些东西，比如说其他服务向哪个地址来请求配置文件等。
```yaml
server:
  port: 9999
eureka:
  client:
    service-url:
      defaultZone: http://erk2.com:7002/eureka
spring:
  application:
    name: config-center
  cloud:
    config:
      enabled: true
      server:
        git:
          # 仓库地址 https://github.com/chenzifeng1/config-center.git
          uri: https://github.com/chenzifeng1/config-center
          username: chengzifeng1
          password: "**********"
          # 默认时间单位 s 访问git的速度比较慢
          timeout: 15
  
```

### 手动配置热更新
1. 开启服务actuator的refresh端点，自己学习时可以全打开
```yaml
management:
  endpoints:
    web:
      exposure:
      # include: *
        include: ["refresh","health"]       
```
2. 在需要使用热更新的类上加上`@RefreshScope`
3. 使用`POST`请求访问对应服务的refresh接口,不能直接用GET请求发送，测试的话可以暴露一个get请求的接口做请求转发

### 配置按照服务更新
上面的是针对某个服务节点进行，我们在某个服务的实例节点上请求`/actuator/refresh`可以实现该节点的服务热更新。
但是，如果一个比较大的、有较多的服务节点服务，那我们一个个的去刷新节点的明显不合适。所以需要一种手段来帮我们按照服务
统一把所有节点的配置文件进行热加载。

我们可以使用`Bus`来完成这种服务更新的策略。下面简单介绍一下`Bus`:


