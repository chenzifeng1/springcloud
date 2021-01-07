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