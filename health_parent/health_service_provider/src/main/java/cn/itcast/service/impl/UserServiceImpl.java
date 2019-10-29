package cn.itcast.service.impl;

import cn.itcast.mapper.PermissionMapper;
import cn.itcast.mapper.RoleMapper;
import cn.itcast.mapper.UserMapper;
import cn.itcast.pojo.Permission;
import cn.itcast.pojo.Role;
import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    //根据用户名查询用户信息(用户角色,权限)
    public User findByUsername(String username) {
        //查询用户基本信息
        User user=userMapper.findByUsername(username);
        if (user==null){
            return null;
        }
        Integer userId = user.getId();
        //查询用户角色信息
        Set<Role> roles=roleMapper.findRolesByUserId(userId);
        if (roles!=null&&roles.size()>0){
            for (Role role : roles) {
                Integer roleId = role.getId();
                Set<Permission> permissions=permissionMapper.findPermissionByRoleId(roleId);
                role.setPermissions(permissions);
            }
        }

        user.setRoles(roles);
        return user;
    }
}
