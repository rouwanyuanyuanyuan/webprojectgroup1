package org.xdweb.first.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.xdweb.first.model.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE userName = #{username}")
    User selectByUsername(String username);

    /**
     * 插入用户
     * @param user
     * @return
     */
    Integer insertSelective(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void updateByPrimaryKeySelective(User user);

    /**
     * 查询用户数量
     * @return
     */
    @Select("SELECT COUNT(*) FROM user")
    Integer selectCount();

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();

    /**
     * 根据主键查询
     * @param params
     * @return
     */
    int selectCountBySearch(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    List<User> selectBySearch(Map<String, Object> params);
}
