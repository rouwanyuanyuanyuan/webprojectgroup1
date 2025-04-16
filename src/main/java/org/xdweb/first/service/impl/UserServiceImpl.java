package org.xdweb.first.service.impl;

import org.springframework.stereotype.Service;
import org.xdweb.first.mapper.UserMapper;
import org.xdweb.first.model.User;
import org.xdweb.first.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
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
     * 得到用户信息
     * @param token
     * @return
     */
    @Override
    public User getUser(String token) {
        return (User) redisTemplate.opsForValue().get(token);
    }
}
