package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.dao.OrderDao;
import cn.com.lyf.wechat.entity.Order;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public Page<Order> selectAllOrder(Page<Order> page ,Order order, String orderStartTime, String orderEndTime) {
        List<Order> liste = orderDao.selectAllOrder(page,order,orderStartTime,orderEndTime);
        page.setRecords(liste);
        return page;
    }
}

