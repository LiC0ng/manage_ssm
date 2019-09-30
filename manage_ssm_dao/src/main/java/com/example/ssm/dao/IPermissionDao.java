package com.example.ssm.dao;

import com.example.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findPermissionByRoleId(String id) throws Exception;
}
