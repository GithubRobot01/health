package cn.itcast.service.impl;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.mapper.CheckItemMapper;
import cn.itcast.pojo.CheckItem;
import cn.itcast.service.CheckItemService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;

    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
        /* List<CheckItem> page = checkItemMapper.selectByCondition(queryString);
        PageInfo<CheckItem> info=new PageInfo<>();

        return new PageResult(page.getTotal(),page);*/
    }

    @Override
    public void deleteById(int id) {
        //判断检查项是否关联到检查组
        long count = checkItemMapper.findCountByCheckItemId(id);
        if (count>0){
            //当前检查项已经被关联到检查组
            new RuntimeException();
        }
        checkItemMapper.deleteById(id);
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemMapper.findById(id);
    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemMapper.update(checkItem);
    }
}
