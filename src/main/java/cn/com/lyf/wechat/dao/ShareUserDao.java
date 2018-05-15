package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.Share;
import cn.com.lyf.wechat.entity.ShareUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/13 0013.
 */
@Mapper
public interface ShareUserDao extends BaseMapper<ShareUser> {
    void addShareUser(@Param("shareUser") ShareUser shareUser);

    List<ShareUser> selectShareUserByShareId(@Param("shareId") int shareId);
}
