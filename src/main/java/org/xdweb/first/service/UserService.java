package org.xdweb.first.service;

import org.xdweb.first.model.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取用户数量
     * @return
     */
    Integer getCount();

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryUsers();

    /**
     * 得到搜索用户数量
     * @param params
     * @return
     */
    int getSearchCount(Map<String, Object> params);

    /**
     * 分页查询用户
     * @param params
     * @return
     */
    List<User> searchUsersByPage(Map<String, Object> params);

    /**
     * 添加用户
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    Integer deleteUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 批量删除用户
     * @param users
     * @return
     */
    Integer deleteUsers(List<User> users);
}
