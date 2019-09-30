package com.example.ssm.dao;

import com.example.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据用户id查询所有对应的角色
     * SQL:"insert into role values('1111', 'ADMIN', 'vip')"
     * SQL:"insert into role values('2222', 'USER', 'vip')"
     * SQL:"insert into users_role values('111-222', '1111')"
     * SQL:"insert into users_role values('111-222', '2222')"
     *
     * @param userId 用户id
     * @return 返回role的list集合
     * @throws Exception 抛出所有异常
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.example.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc})")
    void save(Role role) throws Exception;
}
