package cn.itcast.mapper;

import cn.itcast.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from t_checkitem where id in(select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "code",column = "code"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "age",column = "age"),
            @Result(property = "price",column = "price"),
            @Result(property = "type",column = "type"),
            @Result(property = "remark",column = "remark"),
            @Result(property = "attention",column = "attention"),
    })
    List<CheckItem> findCheckItemById(int id);
}
