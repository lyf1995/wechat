package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.ShoppingCar;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
@Mapper
public interface ShoppingCarDao extends BaseMapper<ShoppingCar> {
    List<ShoppingCar> selectAllShoppingCar(@Param("userId") int userId);

    void addShoppingCar(@Param("shoppingCar") ShoppingCar shoppingCar);

    void updateShoppingCar(@Param("shoppingCar") ShoppingCar shoppingCar);

    void deleteShoppingCar(@Param("id") int id);

}
