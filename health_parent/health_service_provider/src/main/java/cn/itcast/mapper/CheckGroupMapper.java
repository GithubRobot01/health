package cn.itcast.mapper;

import cn.itcast.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 更新检查组信息
     * @param checkGroup
     */
    void updateCheckGroup(CheckGroup checkGroup);

    /**
     * 根据检查组id删除中间表关联数据
     * @param id
     */
    void delItemAndGroup(Integer id);

    /**
     * 根据检查组id删除检查组信息
     * @param id
     */
    void delCheckGroup(int id);

    /**
     * 查询所有检查组信息
     * @return
     */
    List<CheckGroup> findAll();

    /**
     * 根据id查询检查组与套餐的关联个数
     * @param id 检查组id
     * @return 该检查组被添加到套餐的个数
     */
    long findCountByGroupId(int id);

    @Select("select * from t_checkgroup where id in(select checkgroup_id from t_setmeal_checkgroup where setmeal_id=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "code",column = "code"),
            @Result(property = "name",column = "name"),
            @Result(property = "helpCode",column = "helpCode"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "remark",column = "remark"),
            @Result(property = "attention",column = "attention"),
            @Result(property = "checkItems",column = "id",
                    javaType = List.class,
                    many = @Many(select = "cn.itcast.mapper.CheckItemMapper.findCheckItemById"))
    })
    List<CheckGroup> findCheckGroupById(int id);
}
