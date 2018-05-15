package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.entity.Share;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
public interface ShareService extends IService<Share> {
    Page<Share> selectAllShare(Page<Share> page, Share share);
}
