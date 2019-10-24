package cn.itcast.service.impl;

import cn.itcast.mapper.OrderSettingMapper;
import cn.itcast.pojo.OrderSetting;
import cn.itcast.service.OrderSettingService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void add(List<OrderSetting> orderSettingList) {
        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                //查询数据库中是否存在当前日期的数据
                long count=orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
                if (count>0){
                    //数据库存在相同日期数据,覆盖数据
                    orderSettingMapper.editNumberByOrderDate(orderSetting);
                }else {
                    //数据库不存在相同日期数据,插入数据
                    orderSettingMapper.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String begin=date+"-1";
        //每月至多31天
        String end=date+"-31";
        List<OrderSetting> list=orderSettingMapper.getOrderSettingByMonth(begin,end);
        List<Map> result=new ArrayList<>();
        if (list!=null&&list.size()>0){
            for (OrderSetting orderSetting : list) {
                Map<String,Object> map=new HashMap();
                //date: 1, number: 120, reservations: 1

                map.put("date",orderSetting.getOrderDate().getDate());
                map.put("number",orderSetting.getNumber());
                map.put("reservations",orderSetting.getReservations());

                result.add(map);
            }
        }
        return result;
    }

}
