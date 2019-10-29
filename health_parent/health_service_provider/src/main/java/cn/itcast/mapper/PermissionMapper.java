package cn.itcast.mapper;

import cn.itcast.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface PermissionMapper {

    @Select("SELECT p.* FROM t_role_permission rp,t_permission p WHERE p.id=rp.permission_id AND rp.role_id=#{id}")
    Set<Permission> findPermissionByRoleId(Integer roleId);
}
