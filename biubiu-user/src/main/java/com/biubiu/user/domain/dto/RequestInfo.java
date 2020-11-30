package com.biubiu.user.domain.dto;

import lombok.Data;

/**
 * 请求日志信息
 *
 * @author wbbaijq
 */
@Data
public class RequestInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private Object result;
    private Long timeCost;

    private RuntimeException exception;
}
