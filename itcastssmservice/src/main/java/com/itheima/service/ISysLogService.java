package com.itheima.service;

import com.itcast.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog sysLog);

    List<SysLog> findAll();

}
