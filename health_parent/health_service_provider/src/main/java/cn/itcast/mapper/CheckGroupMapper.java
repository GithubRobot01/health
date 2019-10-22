package cn.itcast.mapper;

import cn.itcast.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckGroupMapper {
    /**
     * 添加检查组
     * @param checkGroup
     */
    void addCheckGruop(CheckGroup checkGroup);

    /**
     * 向中间表添加数据
     * @param id
     * @param checkitemId
     */
    void addItemToGroup(int id, Integer checkitemId);

    /**
     * 根据输入条件查询结果
     * @param queryString
     * @return
     */
    Page<CheckGroup> selectByCondition(String queryString);

    /**
     * 根据id查找项目组信息
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 根据检查组id查询包含的检查项
     * @param id
     * @return 检查组包含的所有检查项集合
     */
    List<Integer> findItemByGroupId(Integer id);

    void updateCheckGroup(CheckGroup checkGroup);

    void delItemAndGroup(Integer id);

    void delCheckGroup(int id);

    List<CheckGroup> findAll();
}
