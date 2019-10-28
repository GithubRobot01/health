package cn.itcast.service;

import cn.itcast.pojo.Member;

public interface MemberService {
    Member findByTel(String telephone);

    void add(Member member);
}
