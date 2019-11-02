package cn.itcast.pinyougou.realm;

import cn.itcast.pinyougou.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;


//自定义Realm,实现安全数据连接
@Service("realm")
public class Realm extends AuthorizingRealm {

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        //保存用户的权限和角色信息的
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //实际工作是查询数据库
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();


        //查询数据库角色，添加进入
        authorizationInfo.addRole(user.getRole());

        //查询数据库权限，添加进入
        authorizationInfo.addStringPermission(user.getPermission());

        return authorizationInfo;
    }

    // 认证  登陆
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        System.out.println("执行realm拦截操作");

        // 转化token
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //实际可以查询数据库
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("123");

        if (user == null) {
            return null;
        } else {
            //判断用户输入的密码和这里设置的密码是否一致
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
    }

}
