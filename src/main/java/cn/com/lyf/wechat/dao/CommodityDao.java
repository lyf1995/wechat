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

    Commodity selectCommodityById(@Param("id")int id);

    void updateCommodity(@Param("commodity") Commodity commodity);

    void deleteCommodity(@Param("id") int id);

    void reductionCommodity(@Param("id") int id);

    void addCommodity(@Param("commodity") Commodity commodity);

    List<Commodity> selectCommodityByTypeId(@Param("typeId")int typeId);

    List<Commodity> selectCommodityByName(@Param("name")String name);

    //插入主图
    void updateMainImage(@Param("path") String path,@Param("id") int id);

    void updateCommodityStockById(@Param("goodsId") int goodsId,@Param("stock") int stock);
}
