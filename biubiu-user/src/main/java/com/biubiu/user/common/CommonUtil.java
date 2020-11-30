package com.biubiu.user.common;

import com.biubiu.user.domain.constant.Option;
import com.biubiu.user.domain.constant.StatusMessage;
import com.biubiu.user.domain.entity.User;
import com.biubiu.user.exception.ParamException;

import java.util.Date;

/**
 * CommonUtil
 *
 * @author wbbaijq
 */
public class CommonUtil {

    private CommonUtil() {
    }

    public static User checkParam(Integer userId, String option) {
        User user = new User();
        user.setId(userId);
        return checkParam(user, option);
    }

    public static User checkParam(User userParam, String option) {
        if (userParam == null) {
            throw new ParamException(StatusMessage.PARAM_ERR.message);
        }
        switch (option) {
            case Option.ADD:
                userParam.setCreateTime(new Date());
                userParam.setValid("1");
                break;
            case Option.UPDATE:
                if (userParam.getId() == null) {
                    throw new ParamException(StatusMessage.PARAM_ERR.message);
                }
                userParam.setUpdateTime(new Date());
                break;
            case Option.DELETE:
                if (userParam.getId() == null) {
                    throw new ParamException(StatusMessage.PARAM_ERR.message);
                }
                break;
            default:
                break;
        }
        return userParam;
    }


}
