package com.chenzifeng.springcloud.zuul.springcloudzuul;

import com.netflix.client.http.HttpRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: springcloud-zuul
 * @Package: com.chenzifeng.springcloud.zuul.springcloudzuul
 * @ClassName: TokenFilter
 * @Author: czf
 * @Description: 定制自己的过滤器
 * Zuul的核心就是各种过滤器，基本的过滤器有四种：PRE，ROUTE，POST,ERROR
 * PRE:接收到请求需要做的操作，在这种过滤器中，我们可以做鉴权，流量分发，记录调试信息等
 * 1.鉴权：我认为是身份认证，由于zuul作为网关，将内网服务与外网请求进行隔离，因此不允许所有请求都打到内网服务上。
 * 鉴权是判断外部请求是否合法，比如是否来自白名单内的IP，或者是否携带令牌token等
 * 2.流量分发：请求打过来时，如果我们有多个服务节点可以为本次请求提供服务，那么我们需要选择一个合适的服务节点来提供服务。可以理解为负载均衡
 * 3.记录调试信息：这个大概可以记录一下请求的信息
 * ROUTE:该过滤器完成从请求到微服务的路由，路由是指根据服务名（serviceId）来找到对应的服务名的服务节点列表，然后选择一个节点。
 * 路由可以认为是解析服务名，获取服务节点的ip:port/资源信息。并且构建发送给微服务的请求，使用Apache HttpClient或者Netflix Ribbon来完成请求发送
 * POST:这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
 * Error:在其他阶段发生错误时执行该过滤器。
 * @Date: 2020/12/30 13:31
 * @Version: 1.0
 */

@Slf4j
public class TokenFilter extends ZuulFilter {
    /**
     * 成功路由的状态码
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 失败路由的状态码
     */
    public static final int FAILURE_CODE = 400;


    /**
     * 指定过滤器的类别
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }


    /**
     * 指定过滤器的优先级
     * 定义filter的顺序，数字越小表示顺序越高，越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 表示是否需要执行该filter，true表示执行，false表示不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }


    /**
     * 进行所要执行的过滤器操作
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("-> > > TokenFilter{},{}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token");
        //判断是否继续路由
        if (StringUtils.isNotBlank(token)) {
            //token不为空，允许路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(SUCCESS_CODE);
            ctx.set("isSuccess",true);
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(FAILURE_CODE);
            ctx.set("isSuccess",false);
        }
        return null;
    }
}
