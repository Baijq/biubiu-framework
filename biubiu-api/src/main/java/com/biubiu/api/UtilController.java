package com.biubiu.api;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * UtilController
 *
 * @author wbbaijq
 */
@RestController
@RequestMapping("/api")
public class UtilController {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @GetMapping("/password")
    public String getSsoPwd(String pwd) {
        return str2md5(pwd);
    }

    public static String str2md5(String inStr) {
        return DigestUtils.md5DigestAsHex(inStr.getBytes());
    }
}
