package com.biubiu.blog.config;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * LogWebAspect
 *
 * @author wbbaijq
 */
@Aspect
@Component
public class LogWebAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogWebAspect.class);

    @Pointcut("execution(public * com.biubiu.blog.controller..*.*(..))")
    public void logAspect() {

    }

    @Around("logAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();

        LOG.info("===========AOP LOGGER START============");
        LOG.info("IP             : {}", request.getRemoteAddr());
        LOG.info("URL            : {}", request.getRequestURL().toString());
        LOG.info("HttpMethod     : {}", request.getMethod());
        LOG.info("ClassMethod    : {}", String.format("%s.%s", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName()));
        LOG.info("Request Param  : {}", JSONUtil.toJsonStr(getRequestParamsByProceedingJoinPoint(pjp)));
        LOG.info("Elapsed        : {}", System.currentTimeMillis() - start);
        LOG.info("===========AOP LOGGER END============");

        return result;
    }

    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint pjp) {
        //参数名
        String[] paramNames = ((MethodSignature) pjp.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = pjp.getArgs();

        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                //获取文件名
                value = file.getOriginalFilename();
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

}
