package org.xdweb.first.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xdweb.first.mapper.UserMapper;
import org.xdweb.first.model.User;
import org.xdweb.first.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userMapper.selectByUsernameAndPasswordAndIsAdmin(user.getUsername(), user.getUserpassword(), user.getIsadmin());
    }

    /**
     * 保存用户信息
     * @param token
     * @param user
     */
    @Override
    public void saveUser(String token, User user) {
        // 设置redisTemplate对象key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // key是token，value是用户保存到redis中，超时时间1小时
        redisTemplate.opsForValue().set(token, user, 1, TimeUnit.HOURS);
    }

    /**
     * 修改密码
     * @param userid
     * @param newPassword
     */
    @Override
    public void setPassword(Integer userid, String newPassword) {
        User user = User.builder()
                .userid(userid)
                .userpassword(newPassword)
                .build();
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public Integer register(String username, String password) {
        User tmp = userMapper.selectByUsername(username);
        if(tmp != null) return 0;  //账号重复

        User user = new User();
        user.setUsername(username);
        user.setUserpassword(password);
        user.setIsadmin((byte)0);
        return userMapper.insertSelective(user);
    }

    /**
     * 删除用户信息
     * @param token
     */
    @Override
    public void removeUser(String token) {
        // 移除token
        redisTemplate.delete(token);
    }

    /**
     * 得到用户信息
     * @param token
     * @return
     */
    @Override
    public User getUser(String token) {
        return (User) redisTemplate.opsForValue().get(token);
    }

    /**
     * 获取用户数量
     * @return
     */
    @Override
    public Integer getCount() {
        return userMapper.selectCount();
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> queryUsers() {
        return userMapper.selectAll();
    }
}
