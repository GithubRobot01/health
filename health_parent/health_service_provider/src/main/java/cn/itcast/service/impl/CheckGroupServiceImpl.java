package cn.itcast.service.impl;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.mapper.CheckGroupMapper;
import cn.itcast.pojo.CheckGroup;
import cn.itcast.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //添加检查组信息
        checkGroupMapper.addCheckGruop(checkGroup);
        int id=checkGroup.getId();
        //向中间表插入数据
        if (checkitemIds!=null){
            for (Integer checkitemId : checkitemIds) {
                checkGroupMapper.addItemToGroup(id,checkitemId);
            }
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);

        Page<CheckGroup> page=checkGroupMapper.selectByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.findById(id);
    }

    @Override
    public List<Integer> findItemByGroupId(Integer id) {
        return checkGroupMapper.findItemByGroupId(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //更新检查组信息
        checkGroupMapper.updateCheckGroup(checkGroup);
        //删除中间表相关数据
        checkGroupMapper.delItemAndGroup(checkGroup.getId());
        //添加中间表相关数据
        int id=checkGroup.getId();
        if (checkitemIds!=null){
            for (Integer checkitemId : checkitemIds) {
                checkGroupMapper.addItemToGroup(id,checkitemId);
            }
        }
    }

    @Override
    public void delById(int id) {
        //删除中间表数据
        checkGroupMapper.delItemAndGroup(id);
        //删除checkgroup表数据
        checkGroupMapper.delCheckGroup(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.findAll();
    }
}
