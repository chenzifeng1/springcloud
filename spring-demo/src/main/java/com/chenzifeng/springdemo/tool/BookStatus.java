package com.chenzifeng.springdemo.tool;

/**
 * LEND:图书借出状态，存在于借出者
 * ON:图书尚有剩余，可以继续借出
 * OFF:图书已无剩余，无法继续借出
 * TIMEOUT:换书超时
 * EXCEPTION:该书处于异常状态
 */
public enum BookStatus {
    LEND,ON,OFF,TIMEOUT,EXCEPTION
}
