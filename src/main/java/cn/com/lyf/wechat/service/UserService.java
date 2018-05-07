package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.entity.User;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * Created by Administrator on 2018/3/20.
 */

public interface UserService extends IService<User>{
    Page<User> selectAllUser(Page<User> page, User user, String recentLoginStartTime,String recentLoginEndTime,String registStartTime,String registEndTime);
}
