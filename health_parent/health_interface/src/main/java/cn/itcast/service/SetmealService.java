package cn.itcast.service;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.pojo.Setmeal;

public interface SetmealService {

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal,Integer[] checkgroupIds);

    /**
     * 分页查询套餐信息
     * @param queryPageBean
     * @return
     */
    PageResult findAll(QueryPageBean queryPageBean);
}
