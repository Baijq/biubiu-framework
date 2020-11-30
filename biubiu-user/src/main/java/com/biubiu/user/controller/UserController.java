package com.biubiu.user.controller;

import com.biubiu.common.response.RestResponse;
import com.biubiu.user.common.CommonUtil;
import com.biubiu.user.domain.constant.Option;
import com.biubiu.user.domain.constant.StatusMessage;
import com.biubiu.user.domain.constant.StringResource;
import com.biubiu.user.domain.dto.UserDTO;
import com.biubiu.user.domain.entity.User;
import com.biubiu.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * UserController
 *
 * @author wbbaijq
 */
@Api
@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户列表")
    @GetMapping
    public RestResponse findAll() {
        List<User> userList = userService.findAll();
        return RestResponse.success().setData(userList);
    }

    @ApiOperation("用户信息")
    @GetMapping("/{id}")
    public RestResponse findById(@PathVariable("id") Integer id) {
        if (StringUtils.isEmpty(id)) {
            return RestResponse.failure(StatusMessage.PARAM_ERR.message);
        }
        User user = userService.findById(id);
        if (user == null) {
            return RestResponse.failure(StatusMessage.NO_DATA.message);
        }
        List<User> users = Collections.singletonList(user);
        return RestResponse.success().setData(users);
    }

    @ApiOperation("添加用户")
    @PostMapping
    public RestResponse addUser(@RequestBody User userParam) {
        User user = CommonUtil.checkParam(userParam, Option.ADD);
        userService.saveUser(user);
        return RestResponse.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    public RestResponse delUser(Integer id) {
        User user = CommonUtil.checkParam(id, Option.UPDATE);
        userService.deleteById(user.getId());
        return RestResponse.success();
    }

    @ApiOperation("修改用户")
    @PutMapping
    public RestResponse updateUser(@RequestBody User userParam) {
        User user = CommonUtil.checkParam(userParam, Option.UPDATE);
        userService.updateUser(user);
        return RestResponse.success();
    }
}
