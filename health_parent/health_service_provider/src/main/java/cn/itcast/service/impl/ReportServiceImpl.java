package cn.itcast.service.impl;

import cn.itcast.mapper.MemberMapper;
import cn.itcast.mapper.OrderMapper;
import cn.itcast.service.ReportService;
import cn.itcast.utils.DateUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 运营统计数据
 */
@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Map<String, Object> getBusinessReportData() throws Exception {
        Map<String, Object> map=new HashMap<>();
        String today=DateUtils.parseDate2String(new Date());
        map.put("reportDate",today);
        //当天新增会员日
        Integer todayNewMember = memberMapper.findMemberCountByDate(today);
        map.put("todayNewMember",todayNewMember);
        //总会员数
        Integer totalMember = memberMapper.findMemberTotalCount();
        map.put("totalMember",totalMember);
        //本周新增会员数
        String monday=DateUtils.parseDate2String(DateUtils.getThisWeekMonday()); //本周周一日期
        Integer thisWeekNewMember = memberMapper.findMemberCountAfterDate(monday);
        map.put("thisWeekNewMember",thisWeekNewMember);
        //本月新增会员数
        String firstDay=DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        Integer thisMonthNewMember = memberMapper.findMemberCountAfterDate(firstDay);
        map.put("thisMonthNewMember",thisMonthNewMember);
        //当天预约人数
        Integer todayOrderNumber = orderMapper.findOrderCountByDate(today);
        map.put("todayOrderNumber",todayOrderNumber);
        //当天到访人数
        Integer todayVisitsNumber = orderMapper.findVisitCountByDate(today);
        map.put("todayVisitsNumber",todayVisitsNumber);
        //本周预约人数
        Integer thisWeekOrderNumber = orderMapper.findOrderCountAfterDate(monday);
        map.put("thisWeekOrderNumber",thisWeekOrderNumber);
        //本周到访人数
        Integer thisWeekVisitsNumber = orderMapper.findVisitCountAfterDate(monday);
        map.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        //本月预约人数
        Integer thisMonthOrderNumber = orderMapper.findOrderCountAfterDate(firstDay);
        map.put("thisMonthOrderNumber",thisMonthOrderNumber);
        //本月到访人数
        Integer thisMonthVisitsNumber = orderMapper.findVisitCountAfterDate(firstDay);
        map.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        //热门套餐信息查询
        List<Map<String,Object>> list= orderMapper.findHotSetmeal();
        map.put("hotSetmeal",list);
        return map;

    }
}
