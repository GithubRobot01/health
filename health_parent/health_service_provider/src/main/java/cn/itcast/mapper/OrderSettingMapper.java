package cn.itcast.mapper;

import cn.itcast.pojo.OrderSetting;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {

    @Update("update t_ordersetting set reservations = #{reservations} where orderDate = #{orderDate}")
    void editReservationsByOrderDate(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(String begin, String end);

    @Select("select * from t_ordersetting where orderDate=#{date}")
    OrderSetting findByOrderDate(Date date);
}
