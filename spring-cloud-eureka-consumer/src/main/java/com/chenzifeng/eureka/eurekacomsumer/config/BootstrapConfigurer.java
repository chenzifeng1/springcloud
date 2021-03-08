package com.chenzifeng.eureka.eurekacomsumer.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: eureka-comsumer
 * @Package: com.chenzifeng.eureka.eurekacomsumer.config
 * @ClassName: BootstrapConfigurer
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/1/8 19:43
 * @Version: 1.0
 */

public class BootstrapConfigurer implements PropertySourceLocator {
    public static final String SPRING_CLOUD_CONFIG_DISCOVERY_ENABLED = "spring.cloud.config.discovery.enabled";
    public static final String SPRING_CLOUD_CONFIG_DISCOVERY_service_id = "spring.cloud.config.discovery.service-id";
    public static final String SPRING_CLOUD_CONFIG_PROFILE = "spring.cloud.config.profile";
    public static final String SPRING_CLOUD_CONFIG_LABEL = "spring.cloud.config.label";

    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String, Object> property = setConfig(environment);
        if (property != null && property.size() > 0) {
            return new MapPropertySource("customProperty", property);
        } else {
            return null;
        }
    }

    /**
     * @param environment
     * @return
     */

    private Map<String, Object> setConfig(Environment environment) {
        Map<String, Object> property = new HashMap<>();
        property.put(SPRING_CLOUD_CONFIG_DISCOVERY_ENABLED, true);
        property.put(SPRING_CLOUD_CONFIG_DISCOVERY_service_id, "config-center");
        property.put(SPRING_CLOUD_CONFIG_PROFILE, "test");
        property.put(SPRING_CLOUD_CONFIG_LABEL, "master");
        return property;
    }
}
