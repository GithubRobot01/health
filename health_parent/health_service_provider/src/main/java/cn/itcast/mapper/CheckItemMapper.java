package cn.itcast.mapper;

import cn.itcast.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckItemMapper {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 根据条件查询数据
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(String queryString);

    /**
     * 查询检查项关联的检查组数量
     * @param id 检查项id
     * @return 关联数量
     */
    long findCountByCheckItemId(Integer id);

    /**
     * 根据id删除检查项
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询检查项
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 更新检查项
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 查询所有检查项信息
     * @return
     */
    List<CheckItem> findAll();
}
