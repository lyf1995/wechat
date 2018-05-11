package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * Created by Administrator on 2018/5/9 0009.
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {
    void addOrder(@Param("order") Order order);

    List<Order> selectOrderByUserId(@Param("userId") int userId, @Param("status") int status);

    void deleteOrder(@Param("id") int id);

    void changeOrderStatus(@Param("id") int id, @Param("status") int status);

    Order selectOrderById(@Param("id") int id);
}
