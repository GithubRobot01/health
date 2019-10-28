package cn.itcast.mapper;

import cn.itcast.pojo.Member;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberMapper {
    List<Member> findAll();
    Page<Member> selectByCondition(String queryString);
    @Insert("insert into t_member(fileNumber,name,sex,idCard,phoneNumber,regTime,password,email,birthday,remark)" +
            "values (#{fileNumber},#{name},#{sex},#{idCard},#{phoneNumber},#{regTime},#{password},#{email},#{birthday},#{remark})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void add(Member member);
    void deleteById(Integer id);
    Member findById(Integer id);
    @Select("select * from t_member where phoneNumber=#{telephone}")
    Member findByTel(String telephone);
    void edit(Member member);
    Integer findMemberCountBeforeDate(String date);
    Integer findMemberCountByDate(String date);
    Integer findMemberCountAfterDate(String date);
    Integer findMemberTotalCount();
}
