package com.example.ssm.dao;

import com.example.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据用户id查询所有对应的角色
     * SQL:"insert into role values('1111', 'ADMIN', 'vip')"
     * SQL:"insert into role values('2222', 'USER', 'vip')"
     * SQL:"insert into users_role values('111-222', '1111')"
     * SQL:"insert into users_role values('111-222', '2222')"
     * @param userId 用户id
     * @return 返回role的list集合
     * @throws Exception 抛出所有异常
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    public List<Role> findRoleByUserId(String userId) throws Exception;
}
