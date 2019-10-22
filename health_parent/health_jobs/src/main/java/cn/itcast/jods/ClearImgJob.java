package cn.itcast.jods;

import cn.itcast.constant.RedisConstant;
import cn.itcast.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //计算两个集合的差值,获得垃圾图片名称集合
        System.out.println("llll");
        System.out.println("哈哈");
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set!=null){
            for (String s : set) {
                //删除垃圾图片
                QiniuUtils.deleteFileFromQiniu(s);
                //从redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);

                System.out.println("删除一张图片,图片名为:"+s);
            }
        }
    }
}
