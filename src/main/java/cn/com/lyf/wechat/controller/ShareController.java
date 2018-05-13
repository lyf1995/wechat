package cn.com.lyf.wechat.controller;

import java.util.*;
import cn.com.lyf.wechat.dao.AddressDao;
import cn.com.lyf.wechat.dao.CommodityDao;
import cn.com.lyf.wechat.dao.ShareDao;
import cn.com.lyf.wechat.dao.ShareUserDao;
import cn.com.lyf.wechat.entity.Commodity;
import cn.com.lyf.wechat.entity.Share;
import cn.com.lyf.wechat.entity.ShareUser;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/13 0013.
 */
@Controller
@RequestMapping(value = "/share/")
public class ShareController {
    @Autowired
    private ShareDao shareDao;
    @Autowired
    private ShareUserDao shareUserDao;
    @Autowired
    private CommodityDao commodityDao;

    /*
        查询所有分享
     */
    @RequestMapping(value = "/selectAllShare")
    @ResponseBody
    public JSONObject selectAllType(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        try{
            List<Share> shareList = shareDao.selectAllShare();
            StaticOptionCode.setResult(jsonOut,14,shareList,true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",false,"");
        }
        return jsonOut;
    }

    /*
        根据用户id查询所有分享
     */
    @RequestMapping(value = "/selectShareByUserId")
    @ResponseBody
    public JSONObject selectShareByUserId(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int userId = jsonIn.getIntValue("userId");
        try{
            List<Share> shareList = shareDao.selectShareByUserId(userId);
            StaticOptionCode.setResult(jsonOut,14,shareList,true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",false,"");
        }
        return jsonOut;
    }


    /*
        添加分享
     */
    @RequestMapping(value = "/addShare")
    @ResponseBody
    public JSONObject addShare(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Share share = new Share();
        Commodity commodity = commodityDao.selectCommodityById(jsonIn.getIntValue("goodsId"));
        share.setGoodsId(jsonIn.getIntValue("goodsId"));
        share.setShareUserId(jsonIn.getIntValue("shareUserId"));
        share.setGoodsName(commodity.getName());
        share.setGoodsMainImage(commodity.getMainImage());
        share.setGoodsVipPrice(commodity.getVipPrice());
        share.setShareTime(new Date());
        share.setAmounts(0);
        share.setIsDelete(0);
        try{
            shareDao.addShare(share);
            String shareUrl = jsonIn.getString("shareUrl")+share.getId();
            shareDao.upDateUrlById(share.getId(),shareUrl);
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }

    /*
        用户点击分享并注册或者购买商品
     */
    @RequestMapping(value = "/addShareUser")
    @ResponseBody
    public JSONObject addShareUser(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        ShareUser shareUser = new ShareUser();
        shareUser.setShareId(jsonIn.getIntValue("shareId"));
        shareUser.setClickUserId(jsonIn.getIntValue("clickUserId"));
        shareUser.setStatus(jsonIn.getIntValue("status"));
        if(shareUser.getStatus()==0){
            shareUser.setAmount(10);
        }
        else{
            shareUser.setAmount(20);
        }
        try{
            shareUserDao.addShareUser(shareUser);
            //根据分享id获取分享信息
            Share share = shareDao.selectShareById(shareUser.getShareId());
            //更新分享总金额
            float amounts = share.getAmounts()+shareUser.getAmount();
            shareDao.UpdateShareAmountsById(share.getId(),amounts);
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",false,"");
        }
        return jsonOut;
    }
}
