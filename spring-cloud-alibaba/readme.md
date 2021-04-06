# Spring Cloud Alibaba
## 重要组件

### 服务发现 Nacos


### 负载均衡 Ribbon

### 声明式Http客户端 Feign

### 服务容错 Sentinel(哨兵)

### 消息驱动 RocketMQ

### API网关 GateWay

### 用户授权与认证 JWT/Oauth2.0

### 配置管理 Nacos

### 调用链监控 Sleuth


## 笔记
1. 启动Nacos服务，我们在官网[下载](https://github.com/alibaba/nacos/releases) 。 解压之后进入`/bin`目录，运行`startup.cmd`。这里有个问题，nacos默认是集群模式，如果
是自己想学习使用的话，需要更改一下`startup.cmd`的启动配置:将启动模式改为单机 `set MODE="standalone"`

