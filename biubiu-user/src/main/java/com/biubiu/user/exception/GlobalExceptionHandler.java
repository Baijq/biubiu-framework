package com.biubiu.user.exception;

import com.biubiu.common.response.RestResponse;
import com.biubiu.user.domain.dto.RequestInfo;
import com.biubiu.user.domain.entity.SysLog;
import com.biubiu.user.service.SysLogService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * GlobalExceptionHandler 全局异常捕获
 *
 * @author wbbaijq
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private SysLogService sysLogService;

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public RestResponse exceptionHandler(Exception e) {
        //sysLogService.saveSysLog(errorLog(e));
        String errMsg = "系統错误:" + e.getMessage();
        Integer errCode = 500;
        return RestResponse.failure(errMsg).setCode(errCode);
    }

    private SysLog errorLog(Exception e) {
        return SysLog.builder()
                .message(e.getMessage())
                .build();
    }
}
