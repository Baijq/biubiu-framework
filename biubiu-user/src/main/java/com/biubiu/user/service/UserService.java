package com.biubiu.user.service;

import com.biubiu.user.domain.entity.User;

import java.util.List;

/**
 * UserService
 *
 * @author wbbaijq
 */
public interface UserService {
    /**
     * 查询用户列表
     *
     * @return List<User>
     */
    public List<User> findAll();

    /**
     * 查询用户信息
     *
     * @param id
     * @return User
     */
    public User findById(Integer id);

    /**
     * 添加用户
     *
     * @param user user
     */
    void saveUser(User user);

    /**
     * 通过id删除用户
     *
     * @param id id
     */
    void deleteById(Integer id);

    /**
     * 修改用户
     *
     * @param user user
     */
    void updateUser(User user);
}

