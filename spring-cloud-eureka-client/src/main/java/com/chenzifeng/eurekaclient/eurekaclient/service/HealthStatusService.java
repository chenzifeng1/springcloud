package com.chenzifeng.eurekaclient.eurekaclient.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: eureka-client
 * @Package: com.chenzifeng.eurekaclient.eurekaclient.service
 * @ClassName: HealthStatusService
 * @Author: czf
 * @Description: 更改服务的健康状态
 * @Date: 2020/12/14 10:05
 * @Version: 1.0
 */
@Service
public class HealthStatusService implements HealthIndicator {
    /**
     * 服务是否处于健康状态
     */
    private Boolean isHealthStatus = false;


    @Override
    public Health health() {
        if(isHealthStatus){
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();
    }

    public void setHealthStatus(Boolean healthStatus) {
        isHealthStatus = healthStatus;
    }

    public String getHealthStatus(){
        return this.isHealthStatus.toString();
    }
}
