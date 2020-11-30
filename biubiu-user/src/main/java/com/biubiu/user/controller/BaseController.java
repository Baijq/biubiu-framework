package com.biubiu.user.controller;

import com.alibaba.fastjson.JSON;
import com.biubiu.user.domain.request.CityRequest;
import com.biubiu.user.domain.request.PublicRequest;
import com.biubiu.util.core.HttpUtil;
import com.biubiu.util.core.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * BaseController
 *
 * @author wbbaijq
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api")
public class BaseController {

    @ApiOperation(value = "天气接口")
    @GetMapping("/weather")
    public String weather(CityRequest request) {
        log.debug(request.toString());

        String url = "http://www.tianqiapi.com/api?version=v9&appid=23035354&appsecret=8YvlPNrz";
        if (!StringUtils.isEmpty(request.getCity())) {
            url = url + "&city=" + request.getCity();
        }
        String rs = HttpUtil.doGet(url);
        return StringUtil.unicodeDecode(rs);
    }

    @ApiOperation(value = "公共Get接口")
    @GetMapping("/get-public")
    public String doGet(PublicRequest request) {
        return HttpUtil.doGet(request.getUrl());
    }

    @ApiOperation(value = "公共Get接口")
    @GetMapping("/get-public/{count}")
    public String doGet(PublicRequest request, @PathVariable Integer count) throws InterruptedException {
        if (count == null || count <= 1) {
            return HttpUtil.doGet(request.getUrl());
        }
        final CountDownLatch latch = new CountDownLatch(count);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                long s = System.currentTimeMillis();
                String rs = HttpUtil.doGet(request.getUrl());
                map.put(String.valueOf((System.currentTimeMillis() - s + "====" + Thread.currentThread().getName())), rs.substring(1,100));
                latch.countDown();
            }).start();
        }
        latch.await();
        return JSON.toJSONString(map);
    }

    @ApiOperation(value = "公共Post接口")
    @PostMapping("/post-public")
    public String doPost(@RequestBody PublicRequest request) {
        return HttpUtil.doPost(request.getUrl(), request.getParam());
    }

    @ApiOperation(value = "测试参数 Get接口")
    @GetMapping
    public User get(User user) {
        return user;
    }

    @ApiOperation(value = "测试参数 Post接口")
    @PostMapping
    public User post(@RequestBody User user) {
        return user;
    }

}
