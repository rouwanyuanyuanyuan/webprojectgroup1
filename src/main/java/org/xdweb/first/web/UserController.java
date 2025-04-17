package org.xdweb.first.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdweb.first.model.User;
import org.xdweb.first.service.UserService;
import org.xdweb.first.utils.MyResult;
import org.xdweb.first.utils.TokenProcessor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value = "/login")
    public Map<String, Object> login(@RequestBody User user) {
        log.info("用户登录，{}", user);
        // 登录
        User userObj = userService.login(user);
        if(userObj == null) {   // 账号或密码错误
            // 返回结果对象
            return MyResult.getResultMap(420, "账号或密码错误");
        } else {    // 账号密码正确
            // 创建token
            String token = TokenProcessor.getInstance().makeToken();
            // 保存到Redis
            userService.saveUser(token, userObj);
            // 返回结果对象
            return MyResult.getResultMap(200, "登录成功",
                    new HashMap<String, String>(){{ put("token", token); }});
        }
    }

    /**
     * 查看用户信息
     * @param token
     * @return
     */
    @RequestMapping(value = "/info")
    public Map<String, Object> info(String token) {
        log.info("从redis中获取用户,{}", token);
        User user = userService.getUser(token);
        if(user == null) {  // 获取失败
            return MyResult.getResultMap(420, "获取用户信息失败");
        } else {    // 获取成功
            return MyResult.getResultMap(200, "获取用户信息成功", user);
        }
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @RequestMapping(value = "/logout")
    public Map<String, Object> logout(String token) {
        log.info("用户退出登录,{}", token);
        // 从redis中移除用户
        userService.removeUser(token);
        return MyResult.getResultMap(200, "退出登录成功" );
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/register")
    public Integer register(String username, String password){
        log.info("用户注册,{}, {}", username, password);
        return userService.register(username, password);
    }

}
