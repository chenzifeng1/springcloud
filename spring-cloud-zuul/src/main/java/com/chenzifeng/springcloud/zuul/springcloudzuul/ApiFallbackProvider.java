package com.chenzifeng.springcloud.zuul.springcloudzuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ProjectName: springcloud-zuul
 * @Package: com.chenzifeng.springcloud.zuul.springcloudzuul
 * @ClassName: ApiFallbackProvider
 * @Author: czf
 * @Description: 在路由这一层做熔断
 * @Date: 2020/12/29 16:35
 * @Version: 1.0
 */
@Slf4j
@Component
public class ApiFallbackProvider  implements FallbackProvider {
    private static final String OK_RESPONSE = "OK";
    private static final String ROUTE = "provider";

    /**
     * 指定该方法做哪个route的熔断
     * @return
     */
    @Override
    public String getRoute() {
        return ROUTE;
    }

    /**
     * 给客户端返回降级的信息
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if(cause!=null&&cause.getCause()!=null){
            String msg = cause.getMessage();
            log.error("the service {} throw a exception {}",route,msg);
        }
        return getFallbackResponse();
    }

    /**
     * 降级消息
     * @return
     */
    public ClientHttpResponse getFallbackResponse(){
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            /**
             * 原始状态码
             * @return
             * @throws IOException
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return OK_RESPONSE;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("The service is not availale".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return null;
            }
        };
    }
}
