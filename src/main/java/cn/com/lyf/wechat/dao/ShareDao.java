package cn.com.lyf.wechat.dao;

import cn.com.lyf.wechat.entity.Address;
import cn.com.lyf.wechat.entity.Share;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/13 0013.
 */
@Mapper
public interface ShareDao extends BaseMapper<Share> {
    void addShare(@Param("share") Share share);

    void upDateUrlById(@Param("id") int id,@Param("shareUrl") String shareUrl);

    List<Share> selectShareByUserId(@Param("userId") int userId);

    List<Share> selectAllShare(Page<Share> page, @Param("share") Share share);

    Share selectShareById(@Param("shareId") int shareId);

    void UpdateShareAmountsById(@Param("id") int id, @Param("amounts") float amounts);

    List<Share> selectAllSharePengyouquan();
}
