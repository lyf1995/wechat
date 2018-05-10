package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {
    void addOrder(@Param("order") Order order);
}
