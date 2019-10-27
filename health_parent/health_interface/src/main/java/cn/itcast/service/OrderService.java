package cn.itcast.service;

import cn.itcast.entity.Result;
import cn.itcast.pojo.Order;

import java.util.Map;

public interface OrderService {
    Result order(Map map) throws Exception;

    Map findById(int id);
}
