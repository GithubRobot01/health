package cn.itcast.mapper;

import cn.itcast.pojo.OrderSetting;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {

    void add(OrderSetting orderSetting);

    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(String begin, String end);
}
