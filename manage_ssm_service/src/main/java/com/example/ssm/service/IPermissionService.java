package com.example.ssm.service;

import com.example.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll() throws Exception;
}
