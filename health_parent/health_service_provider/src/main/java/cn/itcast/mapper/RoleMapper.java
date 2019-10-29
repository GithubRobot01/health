package cn.itcast.mapper;

import cn.itcast.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface RoleMapper {
    @Select("SELECT r.* FROM t_user_role ur,t_role r WHERE r.id=ur.role_id AND ur.user_id=#{id}")
    Set<Role> findRolesByUserId(Integer id);
}
