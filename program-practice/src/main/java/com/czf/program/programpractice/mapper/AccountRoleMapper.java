package com.czf.program.programpractice.mapper;

import com.czf.program.programpractice.entity.AccountRole;

/**
 * @ProjectName: program-practice
 * @Package: com.czf.program.programpractice.mapper
 * @ClassName: UserMapper
 * @Author: czf
 * @Description: 账户-角色关联
 * @Date: 2020/11/16 19:16
 * @Version: 1.0
 */
public interface AccountRoleMapper {
    /**
     * 根据Id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(AccountRole record);

    /**
     * 有选择插入，可以插入不完整的对象
     * @param record
     * @return
     */
    int insertSelective(AccountRole record);

    /**
     * 根据Id查找
     * @param id
     * @return
     */
    AccountRole selectById(Integer id);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AccountRole record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int updateById(AccountRole record);
}