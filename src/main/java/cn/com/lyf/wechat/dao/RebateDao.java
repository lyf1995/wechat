package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.Rebate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
@Mapper
public interface RebateDao extends BaseMapper<Rebate> {

    Rebate getRebateInfo();

    void updateRebate(@Param("rebate") Rebate rebate);
}
