package cn.com.lyf.wechat.service;

import cn.com.lyf.wechat.dao.CommodityDao;
import cn.com.lyf.wechat.entity.Commodity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by Administrator on 2018/5/7 0007.
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityDao, Commodity> implements CommodityService{
    @Autowired
    private CommodityDao commodityDao;

    @Override
    public Page<Commodity> selectAllCommodity(Page<Commodity> page , Commodity commodity) {
        List<Commodity> liste = commodityDao.selectAllCommodity(page,commodity);
        page.setRecords(liste);
        return page;
    }
}