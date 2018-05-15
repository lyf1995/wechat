package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.dao.ShareDao;
import cn.com.lyf.wechat.entity.Share;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareDao, Share> implements ShareService {
    @Autowired
    private ShareDao shareDao;
    @Override
    public Page<Share> selectAllShare(Page<Share> page , Share share) {
        List<Share> liste = shareDao.selectAllShare(page,share);
        page.setRecords(liste);
        return page;
    }
}
