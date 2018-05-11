package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.entity.Order;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * Created by Administrator on 2018/5/10.
 */
public interface OrderService extends IService<Order> {
    Page<Order> selectAllOrder(Page<Order> page, int number, int status, String orderStartTime, String orderEndTime);
}
