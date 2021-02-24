package com.chenzifeng.spring.springsecurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenzifeng.spring.springsecurity.entity.MyUser;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: spring-security
 * @Package: com.chenzifeng.spring.springsecurity.dao
 * @ClassName: UserDao
 * @Author: czf
 * @Description: ${description}
 * @Date: 2021/2/24 16:15
 * @Version: 1.0
 */
@Repository
public interface UserDao extends BaseMapper<MyUser> {

}
