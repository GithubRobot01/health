package cn.itcast.service;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.pojo.CheckGroup;
import cn.itcast.pojo.Setmeal;

import java.util.List;

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

    /**
     * 根据套餐id查询套餐信息
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 移动端获取所有套餐信息
     * @return
     */
    List<Setmeal> getAllSetmeal();

    /**
     * 移动端获取套餐信息(套餐包含的检查组信息,检查组包含的检查项信息)
     * @param id
     * @return
     */
    Setmeal findBySetmealId(int id);

    /**
     * 查询套餐关联的检查组
     * @param id
     * @return
     */
    List<Integer> findCheckGroupById(int id);

    /**
     * 修改套餐信息
     * @param setmeal 套餐信息
     * @param checkgroupIds 套餐新关联的检查组id
     * @param imageName 套餐图片名
     * @param newImageName 套餐新图片名称
     */
    void edit(Setmeal setmeal, Integer[] checkgroupIds, String imageName, String newImageName);

    /**
     * 删除套餐相关信息
     * @param id
     */
    void deleteSetmeal(Integer id);
}
