package cn.itcast.pinyougou.service.impl;

import cn.itcast.pinyougou.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
@Service
public class UserServiceImpl implements UserService {

    //有角色才能访问
    @RequiresRoles("ROLE_ADMIN")
    public String test() {
        return "test";
    }

    //有权限才能访问
    @RequiresPermissions("PER_ADMIN")
    public String test1() {
        return "test1";
    }
}
