package com.biubiu.blog.util;

import com.biubiu.blog.controller.request.AddUserRequest;
import com.biubiu.blog.controller.request.EditUserRequest;
import com.biubiu.blog.controller.request.QueryUserRequest;
import com.biubiu.blog.entity.User;

/**
 * ParamHelper
 *
 * @author wbbaijq
 */
public class ParamHelper {

    public static User converter(QueryUserRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User()
                .setId(request.getId())
                .setMail(request.getMail())
                .setNickname(request.getNickname())
                .setState(request.getState());
        return user;
    }

    public static User converter(AddUserRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User()
                .setNickname(request.getNickname())
                .setAvatar(request.getAvatar())
                .setPassword(request.getPassword())
                .setReward(request.getReward())
                .setUsername(request.getUsername())
                .setMail(request.getMail());
    return user;
    }

    public static User converter(EditUserRequest request, Integer id) {
        if (request == null || id == null) {
            return null;
        }
        User user = new User()
                .setId(id)
                .setNickname(request.getNickname())
                .setAvatar(request.getAvatar())
                .setPassword(request.getPassword())
                .setReward(request.getReward())
                .setUsername(request.getUsername())
                .setMail(request.getMail());
        return user;
    }

}
