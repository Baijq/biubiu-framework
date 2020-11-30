package com.biubiu.user.mapper;


import com.biubiu.user.domain.entity.SysLog;

import java.util.Date;
import java.util.List;

/**
 * LogDao
 *
 * @author biubiu
 */
public interface SysLogMapper {
    /**
     * 查询日志列表
     *
     * @return List<SysLog>
     */
    List<SysLog> findAll();

    /**
     * 记录日志
     *
     * @param sysLog sysLog
     * @return int
     */
    int save(SysLog sysLog);

    /**
     * 删除日志 time之前的日志
     *
     * @param time time之前
     * @return int
     */
    int delete(Date time);

}
