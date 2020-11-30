package com.biubiu.api;

import com.biubiu.util.core.HttpUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//@SpringBootApplication
//@ComponentScan("com.biubiu")
public class ApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(ApiApplication.class, args);
//        LOGGER.info("http://localhost/api/password?pwd=biubiu");

        System.out.println(str2md5("wbyuhai0522."));
    }
    public static String str2md5(String inStr) {
        return DigestUtils.md5DigestAsHex(inStr.getBytes());
    }
}