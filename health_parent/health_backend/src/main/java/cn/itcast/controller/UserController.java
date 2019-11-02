package cn.itcast.controller;

import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUsername")
    public Result getUsername(){
        //当spring security完成认证后,会将当前用户信息保存到框架提供的上下文对象
        /*User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        if (user!=null){
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
        }*/
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name!=null&&(!"".equals(name))){
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,name);
        }
        return new Result(false,MessageConstant.GET_USERNAME_FAIL);
    }
}
