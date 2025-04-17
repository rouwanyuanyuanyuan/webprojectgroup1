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

    /**
     * 删除用户信息
     * @param token
     */
    void removeUser(String token);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    Integer register(String username, String password);

    /**
     * 修改密码
     * @param userid
     * @param newPassword
     */
    void setPassword(Integer userid, String newPassword);
}
