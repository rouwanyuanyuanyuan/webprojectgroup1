package org.xdweb.first.mapper;

import org.apache.ibatis.annotations.Param;
import org.xdweb.first.model.User;

public interface UserMapper {

    /**
     * 根据用户名、密码、是否为管理员查询用户
     * @param username
     * @param password
     * @param isAdmin
     * @return
     */
    User selectByUsernameAndPasswordAndIsAdmin(@Param("username") String username,
                                               @Param("password") String password,
                                               @Param("isAdmin") Byte isAdmin);
}
