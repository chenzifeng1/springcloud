package com.chenzifeng.springcloud.configcenter.bus;

import org.springframework.context.ApplicationEvent;

/**
 * @ProjectName: config-center
 * @Package: com.chenzifeng.springcloud.configcenter.bus
 * @ClassName: RemoteApplicationEvent
 * @Author: czf
 * @Description: <p>
 * 事件的定义
 * 事件：都继承自ApplicationEvent，
 * spring bus中的事件类，都继承自RemoteApplicationEvent。
 * </p>
 * <p>
 * AckRemoteApplicationEvent：对特定事件确认的事件。确认远端事件。<br>
 * EnvironmentChangeRemoteApplicationEvent：环境变更事件。<br>
 * RefreshRemoteApplicationEvent：刷新事件。刷新远端应用配置的事件。<br>
 * UnknownRemoteApplicationEvent：未知事件。<br>
 * </p>
 * @Date: 2021/1/22 15:52
 * @Version: 1.0
 */
public class RemoteApplicationEvent extends ApplicationEvent {

    /**
     *
     */
    private static final Object TRANSIENT_SOURCE = new Object();


    public RemoteApplicationEvent(Object source) {
        super(source);
    }
}
