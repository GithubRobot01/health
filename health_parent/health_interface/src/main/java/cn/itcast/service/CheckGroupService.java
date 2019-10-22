package cn.itcast.service;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.pojo.CheckGroup;

import java.util.List;

//服务接口
public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup findById(Integer id);

    List<Integer> findItemByGroupId(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    void delById(int id);

    List<CheckGroup> findAll();
}
