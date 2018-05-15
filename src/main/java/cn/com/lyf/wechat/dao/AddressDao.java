package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.Address;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
@Mapper
public interface AddressDao extends BaseMapper<Address> {
    List<Address> selectAllAddress(@Param("userId") int userId);

    void addAddress(@Param("address") Address address);

    void deleteAddress(@Param("id") int id);

    Address selectAddressById(@Param("addressId")int addressId);

    void updateAddress(@Param("address") Address address);

    void updateDefault(@Param("addressId")int addressId);

    Address selectDefaultAddressByUserId(@Param("userId") int userId);
}
