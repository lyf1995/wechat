package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.dto.TypeDto;
import cn.com.lyf.wechat.entity.Type;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
@Mapper
public interface TypeDao extends BaseMapper<Type> {
    List<Type> selectAllType(@Param("type") Type type);

    void addType(@Param("type")Type type);

    void updateTypeById(@Param("type")Type type);

    Type selectTypeByName(@Param("typeName")String typeName);

    void deleteType(@Param("type")Type type);
}
