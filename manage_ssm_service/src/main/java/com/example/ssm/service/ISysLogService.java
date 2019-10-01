package com.example.ssm.service;

import com.example.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
