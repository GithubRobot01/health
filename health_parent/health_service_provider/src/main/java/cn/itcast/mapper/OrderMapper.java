package cn.itcast.mapper;

import cn.itcast.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    List<Order> findByCondition(Order order);

    @Insert("insert into t_order(member_id,orderDate,orderType,orderStatus,setmeal_id) values (#{memberId},#{orderDate},#{orderType},#{orderStatus},#{setmealId})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void add(Order order);


    @Select("select * from t_order where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderDate",column = "orderDate"),
            @Result(property = "orderType",column = "orderType"),
            @Result(property = "memberId",column = "member_id"),
            @Result(property = "setmealId",column = "setmeal_id")
    })
    Order findOrderById(int id);

    @Select("select name from t_member where id=#{id}")
    String findOrderMemberById(Integer memberId);

    @Select("select name from t_setmeal where id=#{id}")
    String findSetmealById(Integer setmealId);

    @Select("select count(*) from t_order where orderDate = #{today}")
    Integer findOrderCountByDate(String today);

    @Select("select count(*) from t_order where orderDate = #{today} and orderStatus ='已到诊'")
    Integer findVisitCountByDate(String today);

    @Select("select count(*) from t_order where orderDate >= #{today} and orderStatus ='已到诊'")
    Integer findVisitCountAfterDate(String monday);

    @Select("select count(*) from t_order where orderDate >= #{today}")
    Integer findOrderCountAfterDate(String monday);

    @Select("SELECT s.name,COUNT(o.id) setmeal_count,COUNT(o.id)/(SELECT COUNT(id) proportion FROM t_order) proportion FROM t_order o,t_setmeal s WHERE o.setmeal_id=s.id GROUP BY s.name ORDER BY setmeal_count DESC LIMIT 0,4")
    List<Map<String, Object>> findHotSetmeal();
}
