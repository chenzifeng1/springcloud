# Openfeign

openfeign不仅可以在spring-cloud生态调用，在其他使用HttpClient的地方也可以调用，代码侵入性较低，在调用方加对应注解即可，服务提供方不需要改动。

## 底层实现

### 注解

Openfeign 有两个重要的注解`@EnableFeignClients`和`@FeignClient`。

1. `@EnableFeignClients`:这个注解是加在启动类的，告诉启动类在服务启动时进行相关配置，并去扫码所有带有`@FeignClient`的接口。  
   该注解的三个作用:
    1. 引入FeignClientRegistrar，这个是spring用来实例化FeignBean的
    2. 扫描`@FeignClient`的包信息
    3. 指定`@FeignClient`的配置类
2. `@FeignClient`:这个注解是加在进行服务调用接口上的，通过接口内方法来执行服务调用。 介绍注解的部分属性:
    1. `@AliasFor("name")String value() default ""`:服务名,openfeign会根据该属性去服务列表找对应的服务
    2. `String url() default "";`:服务的url，定义该属性之后openfeign就不会走eureka，而是直接对给定的url发送http请求
    3. `Class<?> fallback() default void.class;`: 降级的回调处理类，声明这个方法之后，如果openfeign在调用服务出现超时、异常或者是其他问题时，
       就会调用fallback这个类对应的方法来做降级处理
    4. `Class<?> fallbackFactory() default void.class;`:降级处理的回调工厂类，如果我们想知道服务到底抛了什么异常，可以定义这个属性。 但是这个属性与`fallback`
       属性只有一个能生效。

### 原理

Openfeign生效的过程如下：

1. `@EnableFeignClients`注解引入了`FeignClientRegistrar`，点进注解可以看到`@Import({FeignClientRegistrar.class})`

> 在@Import注解的参数中可以填写类名，例如@Import(Abc.class)，根据类Abc的不同类型，spring容器有以下四种处理方式:
> 1. 如果Abc类实现了ImportSelector接口，spring容器就会实例化Abc类，并且调用其selectImports方法；
> 2. DeferredImportSelector是ImportSelector的子类，如果Abc类实现了DeferredImportSelector接口，spring容器就会实例化Abc类，并且调用其selectImports方法，和ImportSelector的实例不同的是，DeferredImportSelector的实例的selectImports方法调用时机晚于ImportSelector的实例，要等到@Configuration注解中相关的业务全部都处理完了才会调用（具体逻辑在ConfigurationClassParser.processDeferredImportSelectors方法中）
> 3. 如果Abc类实现了ImportBeanDefinitionRegistrar接口，spring容器就会实例化Abc类，并且调用其registerBeanDefinitions方法；
> 4. 如果Abc没有实现ImportSelector、DeferredImportSelector、ImportBeanDefinitionRegistrar等其中的任何一个，spring容器就会实例化Abc类；

FeignClientRegistrar是第三种情况，spring在实例化时会调用FeginClientRegistrar重写的registrarBeanDefinitions这个方法。

## 实例