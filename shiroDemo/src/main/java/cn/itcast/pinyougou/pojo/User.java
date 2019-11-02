package cn.itcast.pinyougou.pojo;

/**
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class User {

    private String name;
    private String password;
    private String role="ROLE_ADMIN1";
    private String permission="PER_ADMIN1";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
