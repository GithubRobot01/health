package cn.itcast.service.impl;

import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.Result;
import cn.itcast.mapper.MemberMapper;
import cn.itcast.mapper.OrderMapper;
import cn.itcast.mapper.OrderSettingMapper;
import cn.itcast.pojo.Member;
import cn.itcast.pojo.Order;
import cn.itcast.pojo.OrderSetting;
import cn.itcast.service.OrderService;
import cn.itcast.utils.DateUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 体检预约服务
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Result order(Map map) throws Exception {
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        String orderDate = (String) map.get("orderDate");
        OrderSetting orderSetting=orderSettingMapper.findByOrderDate(DateUtils.parseString2Date(orderDate));
        if (orderSetting==null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        if (orderSetting.getReservations()>=orderSetting.getNumber()){
            return new Result(false,MessageConstant.ORDER_FULL);
        }
        //3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐）,如果是重复预约则无法完成再次预约
        String telephone = (String) map.get("telephone");
        //根据手机号查询会员信息
        Member member = memberMapper.findByTelephone(telephone);
        if (member!=null){
            //判断是否是重复预约
            Integer id = member.getId();
            //获取预约日期
            Date date = DateUtils.parseString2Date(orderDate);
            //获取套餐id
            Integer setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order=new Order(id,date,setmealId);
            List<Order> orderList=orderMapper.findByCondition(order);
            if (orderList!=null&&orderList.size()>0){
                //用户已经进行了预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }else {
            //4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
            member=new Member();
            member.setName((String)map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberMapper.add(member);

        }
        //5、预约成功，更新当日的已预约人数
        Order order=new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(DateUtils.parseString2Date(orderDate));
        order.setOrderType((String) map.get("orderType"));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
        orderMapper.add(order);

        orderSetting.setReservations(orderSetting.getReservations()+1);

        orderSettingMapper.editReservationsByOrderDate(orderSetting);
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS,order.getId());
    }

    @Override
    public Map findById(int id) {
        Map<String,Object> map=new HashMap<>();
        /*
        体检人：{{orderInfo.member}}
        体检套餐：{{orderInfo.setmeal}}
        体检日期：{{orderInfo.orderDate}}
        预约类型：{{orderInfo.orderType}}
        */
        Order order = orderMapper.findOrderById(id);
        map.put("orderType",order.getOrderType());
        if (order.getOrderDate()!=null){
            String date = null;
            try {
                date = DateUtils.parseDate2String(order.getOrderDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("orderDate",date);
        }

        String memberName=orderMapper.findOrderMemberById(order.getMemberId());
        map.put("member",memberName);

        String setmeal=orderMapper.findSetmealById(order.getSetmealId());
        map.put("setmeal",setmeal);
        return map;
    }
}
