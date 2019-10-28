package cn.itcast.service;

import cn.itcast.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringSecurityUserService implements UserDetailsService {
    //模拟数据库中的用户数据
    public static Map<String, User> map=new HashMap<>();
    static {
        cn.itcast.pojo.User user1=new cn.itcast.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("admin");

        cn.itcast.pojo.User user2=new cn.itcast.pojo.User();
        user2.setUsername("wqs");
        user2.setPassword("1234");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        //根据用户名查询数据库获得用户信息(包含数据库中存储的密码信息)
        User user = map.get(username);
        if (user==null){
            return null;
        }
        //将用户信息返回给框架
        // 框架会进行密码比对(页面提交的密码和数据库中查询的密码进行对比)
        List<GrantedAuthority> list=new ArrayList<>();
        //为当前登录用户授权

        list.add(new SimpleGrantedAuthority("permission_A"));//授权
        list.add(new SimpleGrantedAuthority("permission_B"));
        if (username.equals("admin")){
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));//授予角色
        }
        org.springframework.security.core.userdetails.User securityUser=new org.springframework.security.core.userdetails.User(username,"{noop}"+user.getPassword(),list);

        return securityUser;
    }
}
