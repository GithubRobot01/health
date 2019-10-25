package cn.itcast.mapper;

import cn.itcast.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SetmealMapper {
    void addMeal(Setmeal setmeal);

    void addMealAndGroup(int id, Integer checkgroupId);

    Page<Setmeal> findByCondition(String queryString);

    Setmeal findById(Integer id);

    List<Setmeal> getAllSetmeal();

    @Select("select * from t_setmeal where id=#{id}")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(column="name",property="name"),
            @Result(column="code",property="code"),
            @Result(column="helpCode",property="helpCode"),
            @Result(column="sex",property="sex"),
            @Result(column="age",property="age"),
            @Result(column="price",property="price"),
            @Result(column="remark",property="remark"),
            @Result(column="attention",property="attention"),
            @Result(column="img",property="img"),
            @Result(column = "id",property = "checkGroups",
                    javaType = List.class,
                    many = @Many(select = "cn.itcast.mapper.CheckGroupMapper.findCheckGroupById"))
    })
    Setmeal findBySetmealId(int id);
}
