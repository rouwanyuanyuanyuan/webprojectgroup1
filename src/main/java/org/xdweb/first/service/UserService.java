package org.xdweb.first.service;

import org.xdweb.first.model.User;

public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 保存用户信息
     * @param token
     * @param userObj
     */
    void saveUser(String token, User userObj);

    /**
     * 得到用户信息
     * @param token
     * @return
     */
    User getUser(String token);
}
