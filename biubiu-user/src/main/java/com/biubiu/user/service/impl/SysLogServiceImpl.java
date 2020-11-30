package com.biubiu.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.biubiu.user.domain.entity.SysLog;
import com.biubiu.user.mapper.SysLogMapper;
import com.biubiu.user.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * SysLogService
 *
 * @author wbbaijq
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public void saveSysLog(SysLog log) {
        SysLog sysLog = new SysLog();
        BeanUtil.copyProperties(log, sysLog);
        sysLog.setCreateTime(new Date());
        sysLog.setValid(true);
        sysLogMapper.save(sysLog);
    }

    @Override
    public void deleteLogs(Date time) {
        sysLogMapper.delete(time);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogMapper.findAll();
    }
}
