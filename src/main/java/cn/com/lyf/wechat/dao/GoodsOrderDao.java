package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.GoodsOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
@Mapper
public interface GoodsOrderDao extends BaseMapper<GoodsOrder> {
    void addGoodsOrder(@Param("goodsOrder") GoodsOrder goodsOrder);
}
