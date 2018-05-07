package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.Commodity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */
@Mapper
public interface CommodityDao extends BaseMapper<Commodity> {
    List<Commodity> selectAllCommodity(Page<Commodity> page, @Param("commodity") Commodity commodity);
}
