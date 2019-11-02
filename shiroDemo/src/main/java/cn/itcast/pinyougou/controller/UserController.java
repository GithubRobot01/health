package cn.itcast.pinyougou.controller;

import cn.itcast.pinyougou.pojo.User;
import cn.itcast.pinyougou.pojo.entity.Result;
import cn.itcast.pinyougou.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //点击登陆按钮访问的方法
    @RequestMapping("/login")
    public Result login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try{
            subject.login(token);//登陆操作
            return new Result(true,"登录成功",user.getName());
        }catch(Exception ex){
            ex.printStackTrace();
            return new Result(false,"登录失败",null);
        }
    }

    @RequestMapping("/name")
    public Object loginName(){
        Subject subject = SecurityUtils.getSubject();
      return subject.getPrincipal();
    }

    @RequestMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.logout();
            return new Result(true,"注销成功",null);
        }catch(Exception ex){
            //ex.printStackTrace();
            return new Result(true,"注销失败",null);
        }
    }

    @RequestMapping("/test")
    public String test(){
        return userService.test();
    }

    @RequestMapping("/test1")
    public String test1(){
        return userService.test1();
    }
}
