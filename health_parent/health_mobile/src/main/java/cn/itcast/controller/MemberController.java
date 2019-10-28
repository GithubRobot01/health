package cn.itcast.controller;

import cn.itcast.constant.MessageConstant;
import cn.itcast.constant.RedisMessageConstant;
import cn.itcast.entity.Result;
import cn.itcast.pojo.Member;
import cn.itcast.service.MemberService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @RequestMapping("/login")
    public Result login(HttpServletResponse response, @RequestBody Map map){
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        //从redis中取出验证码
        String code = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        if(code!=null&&validateCode!=null&&validateCode.equals(code)){
            Member member=memberService.findByTel(telephone);
            if (member==null){

                member=new Member();
                member.setRegTime(new Date());
                member.setPhoneNumber(telephone);
                memberService.add(member);

            }
            //向客户端浏览器写入cookie,内容为手机号
            Cookie cookie=new Cookie("login_member_telephone",telephone);
            cookie.setPath("/"); //路径
            cookie.setMaxAge(30*60*60*24); //设置时间
            response.addCookie(cookie);
            //将会员信息保存到redis
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(telephone,60*30,json);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        }else{
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

    }
}
