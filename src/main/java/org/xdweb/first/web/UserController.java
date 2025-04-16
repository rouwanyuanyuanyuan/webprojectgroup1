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
}
