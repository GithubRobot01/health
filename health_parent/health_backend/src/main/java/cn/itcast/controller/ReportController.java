package cn.itcast.controller;

import cn.itcast.constant.MessageConstant;
import cn.itcast.entity.Result;
import cn.itcast.service.MemberService;
import cn.itcast.service.SetmealService;
import cn.itcast.utils.DateUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/report")
public class ReportController {

    @Reference
    private MemberService memberService;
    @Reference
    private SetmealService setmealService;
    //会员数量折线图
    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
        try {
            Map<String,Object> map=new HashMap<>();
            List<String> months=new ArrayList<String>();

            Calendar calendar=Calendar.getInstance();
            //获得当前时间往前推12个月
            calendar.add(Calendar.MONTH,-12);
            for (int i = 0; i < 12; i++) {
                calendar.add(Calendar.MONTH,1);
                Date date = calendar.getTime();
                months.add(new SimpleDateFormat("yyyy-MM").format(date));
            }
            map.put("months",months);

            List<Integer> memberCounts=memberService.findMemberCountByMonth(months);
            map.put("memberCount",memberCounts);
            return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }
    }

    //套餐预约占比饼状图
    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        try {
            Map<String,Object> map=new HashMap<>();
            List<String> setmealNames=new ArrayList<>();
            List<Map<String,Object>> setmealCount=setmealService.findSetmealCount();

            for (Map<String, Object> map1 : setmealCount) {
                String name = (String) map1.get("name");
                setmealNames.add(name);
            }

            map.put("setmealNames",setmealNames);
            map.put("setmealCount",setmealCount);
            return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_SETMEAL_COUNT_REPORT_FAIL);
        }
    }
}
