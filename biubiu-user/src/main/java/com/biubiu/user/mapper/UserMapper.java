package com.biubiu.user.mapper;

import com.biubiu.user.domain.dto.UserDTO;
import com.biubiu.user.domain.entity.User;

import java.util.List;

/**
 * UserMapper
 *
 * @author wbbaijq
 */
public interface UserMapper {

    /**
     * 查询用户通过Id
     *
     * @param id userId
     * @return User
     */
    User findById(Integer id);

    /**
     * 模糊查询
     *
     * @param userParam 参数
     * @return user
     */
    List<User> findUser(UserDTO userParam);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 保存用户
     *
     * @param user 用户对象
     * @return int
     */
    int saveUser(User user);

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    List<User> findAll();

    /**
     * 更新用户
     *
     * @param user user
     */
    void updateUser(User user);
}
