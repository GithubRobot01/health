package cn.itcast.service;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckItemService {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     * @param id 检查项id
     */
    void deleteById(int id);

    /**
     * 根据id查找检查项
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 更新检查项信息
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 查询所有检查项
     * @return
     */
    List<CheckItem> findAll();
}
