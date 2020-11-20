package com.czf.program.programpractice.mapper;

import com.czf.program.programpractice.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.mapper
 * @ClassName: UserMapper
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/11/16 19:16
 * @Version: 1.0
 */
@Repository
public interface UserMapper {
    /**
     * 获取所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User findOneById(@Param("id") String id);

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User findOneByName(@Param("username") String name);


}
