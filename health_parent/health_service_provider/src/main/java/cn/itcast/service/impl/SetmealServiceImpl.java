package cn.itcast.service.impl;

import cn.itcast.constant.RedisConstant;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.mapper.SetmealMapper;
import cn.itcast.pojo.Setmeal;
import cn.itcast.service.SetmealService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        //添加套餐
        setmealMapper.addMeal(setmeal);
        int id=setmeal.getId();
        //向套餐中添加检查组
        if (checkgroupIds!=null&&checkgroupIds.length>0){
            for (Integer checkgroupId : checkgroupIds) {
                setmealMapper.addMealAndGroup(id,checkgroupId);
            }
        }
        //将图片名称保存到redis集合
        String fileName = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,fileName);
    }

    @Override
    public PageResult findAll(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);

        Page<Setmeal> page=setmealMapper.findByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }

    //移动端
    @Override
    public List<Setmeal> getAllSetmeal() {
        return setmealMapper.getAllSetmeal();
    }

    //移动端获取套餐信息(套餐包含的检查组信息,检查组包含的检查项信息)
    @Override
    public Setmeal findBySetmealId(int id) {
        Setmeal setmeal = setmealMapper.findBySetmealId(id);
        return setmeal;
    }
}
