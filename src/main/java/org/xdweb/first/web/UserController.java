package org.xdweb.first.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xdweb.first.model.User;
import org.xdweb.first.service.UserService;
import org.xdweb.first.utils.MyResult;
import org.xdweb.first.utils.MyUtils;
import org.xdweb.first.utils.TokenProcessor;

import java.util.HashMap;
import java.util.List;
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

    /**
     * 修改密码
     * @param userid
     * @param username
     * @param isadmin
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = {"/alterPassword", "reader/alterPassword"})
    public Integer alterPassword(Integer userid, String username, Byte isadmin, String oldPassword, String newPassword){
        log.info("修改密码，{}, {}, {}, {}, {}", userid, username, isadmin, oldPassword, newPassword);
        //检查旧密码是否正确
        User userObj = User.builder()
                .isadmin(isadmin)
                .userid(userid)
                .username(username)
                .userpassword(oldPassword)
                .build();

        User user = userService.login(userObj);
        if(user == null) {  //旧密码不正确
            return 0;
        } else {    //旧密码正确，设置新密码
            userService.setPassword(userObj.getUserid(), newPassword);
            return 1;
        }
    }

    /**
     * 获得数量
     * @return
     */
    @GetMapping(value = "/getCount")
    public Integer getCount(){
        log.info("获取用户数量");
        return userService.getCount();
    }

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping(value = "/queryUsers")
    public List<User> queryUsers(){
        log.info("查询所有用户");
        return userService.queryUsers();
    }

    /**
     * 分页查询用户
     * @param params
     * @return
     */
    @GetMapping(value = "/queryUsersByPage")
    public Map<String, Object> queryUsersByPage(@RequestParam Map<String, Object> params){
        log.info("分页查询用户,{}", params);
        MyUtils.parsePageParams(params);
        int count = userService.getSearchCount(params);
        List<User> users = userService.searchUsersByPage(params);
        return MyResult.getListResultMap(0, "success", count, users);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping(value = "/addUser")
    public Integer addUser(@RequestBody User user){
        log.info("添加用户,{}", user);
        return userService.addUser(user);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @DeleteMapping(value = "/deleteUser")
    public Integer deleteUser(@RequestBody User user){
        log.info("删除用户,{}", user);
        return userService.deleteUser(user);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser")
    public Integer updateUser(@RequestBody User user){
        log.info("更新用户,{}", user);
        userService.updateUser(user);
        return 1;
    }
}
