package cn.itcast.service.impl;

import cn.itcast.mapper.MemberMapper;
import cn.itcast.pojo.Member;
import cn.itcast.service.MemberService;
import cn.itcast.utils.MD5Utils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member findByTel(String telephone) {
        return memberMapper.findByTel(telephone);
    }

    @Override
    public void add(Member member) {
        String password=member.getPassword();
        if(password!=null){
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberMapper.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
        List<Integer> counts=new ArrayList<>();
        for (String month : months) {
            String date=month+"-31";
            Integer count = memberMapper.findMemberCountBeforeDate(date);
            counts.add(count);
        }
        return counts;
    }
}