# Spring Security 

Spring Security作为一个权限认证的安全框架，为我们提供了两个主要功能：
1. 身份认证：登录时进行校验用户的身份信息
2. 访问控制：检查用户是否有权限访问url


## 身份认证
Spring security关于身份认证就一个主要的接口：

```java

```

### 关于configure的重载方法

- 参数是HttpSecurity
- 参数是WebSecurity
- 参数是AuthenticationManageBuilder

1. configure(HttpSecurity http);
    这里是负责配置对http请求的操作，比如说对哪些请求需要授权，那些请求可以放开，比如定义用户登录页面以及在登录时的一些配置。
```java
public class MyConfig extends WebSecurityConfigurerAdapter{
    @Override    
    public void configure(HttpSecurity http) throws Exception{
        //禁止csrf(跨站点伪造攻击)    
        http.csrf().disable()
        //自定义 登录界面
        .formLogin()
        //这里有个小坑 我们要把login.html放到resources/static下面才能找到对应的页面，不然会报404
        .loginPage("/login.html")
        //defaultSuccessUrl 与 successForwardUrl只要配置一个即可，效果在下面会介绍
        .defaultSuccessUrl("/success")
        .successForwardUrl("/success")
        .failureForwardUrl("/fail")
         //处理登录请求的接口 这里是表单提交后的处理的接口地址
        .loginProcessingUrl("/authentication/form")
        .and()
        .logout()
        .logoutSuccessHandler(new MyLogoutSuccessHandler())
        .and()
        .rememberMe()
        .and()
        // rememberMe与设置登录碰撞 冲突了。 rememberMe是通过设置一个rememberMe的token来让用户在多个子系统内实现一次登录，处处使用。
        // 而登录碰撞则是通过一个map来记录用户登录情况，当同一个用户在线次数超过阈值之后会使之前有效的session失效
        .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true)
        .and()
        .and()
         // 这里对放开static的页面和身份认证接口的访问 避免无限循环授权认证的重定向
        .authorizeRequests()
        .antMatchers("/login.html","/static/**.html", "/authentication/**")
        .permitAll()
        .anyRequest()
        .authenticated();
        /*
            勾选remember me之后，登录会生成一个remember me的token(JWT TOKEN是来维持无状态的会话的) 原因：
            1. 集群式的会话 如果用session共享来实现，太消耗资源
            2. jwt 可以通过token令牌来进行用户的授权认证
         */

    }
}
```