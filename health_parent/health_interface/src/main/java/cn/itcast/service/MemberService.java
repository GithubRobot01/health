package cn.itcast.service;

import cn.itcast.pojo.Member;

import java.util.List;

public interface MemberService {
    Member findByTel(String telephone);

    void add(Member member);

    List<Integer> findMemberCountByMonth(List<String> months);
}
