package com.biubiu.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BiubiuBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiubiuBlogApplication.class, args);
        System.out.println("请访问：http://localhost:8888/index.html");
    }

}
