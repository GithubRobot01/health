package cn.itcast.mapper;

import cn.itcast.pojo.Setmeal;
import com.github.pagehelper.Page;

public interface SetmealMapper {
    void addMeal(Setmeal setmeal);

    void addMealAndGroup(int id, Integer checkgroupId);

    Page<Setmeal> findByCondition(String queryString);
}
