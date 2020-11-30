package com.biubiu.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * SysLog
 *
 * @author wbbaijq
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLog {

    /** 主键ID 自增 **/
    private Integer id;
    /** 是否有效 **/
    private Boolean valid;
    /** 请求方式 **/
    private String requestMethod;
    /** 请求IP **/
    private String remoteAddr;
    /** 操作用户 **/
    private String username;
    /** 请求Url **/
    private String requestUrl;
    /** 请求接口 **/
    private String method;
    /** 请求参数 **/
    private String params;
    /** 响应 **/
    private String response;
    /** message **/
    private String message;
    /** 方法执行时间 **/
    private Long useTime;
    /** Http referer **/
    private String referer;
    /** 创建时间 **/
    private Date createTime;
    /** 修改时间 **/
    private Date updateTime;
}
