package com.biubiu.user.service.impl;

import com.biubiu.user.domain.entity.User;
import com.biubiu.user.mapper.UserMapper;
import com.biubiu.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * UserServiceImpl
 *
 * @author wbbaijq
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteByIds(Collections.singletonList(id));
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

}
