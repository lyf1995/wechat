package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.entity.Commodity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * Created by Administrator on 2018/5/7 0007.
 */
public interface CommodityService extends IService<Commodity> {
    Page<Commodity> selectAllCommodity(Page<Commodity> page, Commodity commodity);
}
