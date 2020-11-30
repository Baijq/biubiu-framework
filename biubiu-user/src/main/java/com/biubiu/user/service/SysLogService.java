package com.biubiu.user.service;

import com.biubiu.user.domain.entity.SysLog;

import java.util.Date;
import java.util.List;

/**
 * SysLogService
 *
 * @author wbbaijq
 */
public interface SysLogService {
    /**
     * saveSysLog
     *
     * @param log log
     */
    void saveSysLog(SysLog log);

    /**
     * deleteLogs
     */
    void deleteLogs(Date time);

    /**
     * 查询日志列表
     *
     * @return List<SysLog>
     */
    List<SysLog> findAll();

}
