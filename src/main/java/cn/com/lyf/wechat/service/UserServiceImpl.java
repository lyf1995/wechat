package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.dao.UserDao;
import cn.com.lyf.wechat.entity.User;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public Page<User> selectAllUser(Page<User> page , User user) {
        List<User> liste = userDao.selectAllUser(page,user);
        page.setRecords(liste);
        return page;
    }
}
