package com.czf.program.programpractice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.utils
 * @ClassName: SystemConfig
 * @Author: czf
 * @Description: 读取配置文件的内容 加了{@Component}注解 使用的时候可以直接通过{@AutoWired}注解来获取其中的属性
 * @Date: 2020/11/18 20:10
 * @Version: 1.0
 */
@Component
public class SystemConfig {

    @Value(value = "${config.systemName}")
    private String systemName;


    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
