package cn.itcast.service.impl;

import cn.itcast.constant.RedisConstant;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.mapper.SetmealMapper;
import cn.itcast.pojo.Setmeal;
import cn.itcast.service.SetmealService;
import cn.itcast.utils.QiniuUtils;
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

    @Override
    public List<Integer> findCheckGroupById(int id) {
        return setmealMapper.findCheckGroupById(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds, String imageName, String newImageName) {

        //修改套餐信息
        setmealMapper.editSetmeal(setmeal);
        //修改套餐与检查组关联信息
        setmealMapper.delSetmealAndGroup(setmeal.getId());
        //重新建立关系
        this.add(setmeal,checkgroupIds);
        //新图片名不为空
        if (!"".equals(newImageName)){
            //将原来图片删除
            QiniuUtils.deleteFileFromQiniu(imageName);
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,imageName);
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES,imageName);
            //将新图片加入redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,newImageName);
        }
    }

    @Override
    public void deleteSetmeal(Integer id) {
        //根据套餐id查询套餐图片名称
        String imageName=setmealMapper.findImageNameById(id);
        //删除图片
        jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES,imageName);
        jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,imageName);
        QiniuUtils.deleteFileFromQiniu(imageName);
        //删除中间表
        setmealMapper.delSetmealAndGroup(id);
        //删除套餐表数据
        setmealMapper.delSetmealById(id);
    }

}
