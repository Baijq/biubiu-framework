package com.biubiu.user.domain.dto;

import com.biubiu.user.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * UserDTO
 *
 * @author wbbaijq
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends User {

    /** 根据时间查询的查询条件 */
    private Date startTime;
    private Date endTime;

}
