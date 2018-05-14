package cn.com.lyf.wechat.controller;

import java.util.*;

import cn.com.lyf.wechat.dao.*;
import cn.com.lyf.wechat.dto.ShareDto;
import cn.com.lyf.wechat.dto.ShareUserDto;
import cn.com.lyf.wechat.entity.Commodity;
import cn.com.lyf.wechat.entity.Share;
import cn.com.lyf.wechat.entity.ShareUser;
import cn.com.lyf.wechat.entity.User;
import cn.com.lyf.wechat.service.ShareService;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
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
    @Autowired
    private UserDao userDao;
    @Autowired
    private ShareService shareService;
    @Autowired
    private RebateDao rebateDao;

    /*
       后台管理查询所有分享
     */
    @RequestMapping(value = "/selectAllShare")
    @ResponseBody
    public JSONObject selectAllType(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Integer pageIndex=jsonIn.getInteger("pageIndex");
        Integer pageSize=jsonIn.getInteger("pageSize");
        Share share = new Share();
        String shareUserPhone = jsonIn.getString("shareUser").equals("")?null:jsonIn.getString("shareUser");
        if(shareUserPhone!=null){
            User user = userDao.selectUsername(shareUserPhone);
            if(user!=null) {
                share.setShareUserId(user.getId());
            }
            else{
                share.setShareUserId(-1);
            }
        }
        share.setGoodsName("%"+jsonIn.getString("goodsName")+"%");
        try {
            Page<Share> page = new Page<>(pageIndex,pageSize);
            page= shareService.selectAllShare(page,share);
            List<ShareDto> shareDtoList = new ArrayList<>();
            for(int i = 0; i<page.getRecords().size();i++){
                ShareDto shareDto = new ShareDto();
                shareDto.setId(page.getRecords().get(i).getId());
                shareDto.setShareUserId(page.getRecords().get(i).getShareUserId());
                shareDto.setGoodsId(page.getRecords().get(i).getGoodsId());
                shareDto.setGoodsName(page.getRecords().get(i).getGoodsName());
                shareDto.setGoodsMainImage(page.getRecords().get(i).getGoodsMainImage());
                shareDto.setGoodsVipPrice(page.getRecords().get(i).getGoodsVipPrice());
                shareDto.setShareUrl(page.getRecords().get(i).getShareUrl());
                shareDto.setAmounts(page.getRecords().get(i).getAmounts());
                shareDto.setShareTime(page.getRecords().get(i).getShareTime());
                User user2 = userDao.selectUserById(shareDto.getShareUserId());
                shareDto.setShareUserName(user2.getPhone());
                shareDtoList.add(shareDto);
            }
            StaticOptionCode.setResult(jsonOut,9,shareDtoList,true,""+page.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,10,"",false,"");
        }
        return jsonOut;
    }

    /*
       后台管理查询所有分享
     */
    @RequestMapping(value = "/selectAllSharePengyouquan")
    @ResponseBody
    public JSONObject selectAllSharePengyouquan(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        try {
            List<Share> shareList = shareDao.selectAllSharePengyouquan();
            List<ShareDto> shareDtoList = new ArrayList<>();
            for(int i = 0; i<shareList.size();i++){
                ShareDto shareDto = new ShareDto();
                shareDto.setId(shareList.get(i).getId());
                shareDto.setShareUserId(shareList.get(i).getShareUserId());
                shareDto.setGoodsId(shareList.get(i).getGoodsId());
                shareDto.setGoodsName(shareList.get(i).getGoodsName());
                shareDto.setGoodsMainImage(shareList.get(i).getGoodsMainImage());
                shareDto.setGoodsVipPrice(shareList.get(i).getGoodsVipPrice());
                shareDto.setShareUrl(shareList.get(i).getShareUrl());
                shareDto.setAmounts(shareList.get(i).getAmounts());
                shareDto.setShareTime(shareList.get(i).getShareTime());
                User user2 = userDao.selectUserById(shareDto.getShareUserId());
                shareDto.setShareUserName(user2.getPhone());
                shareDtoList.add(shareDto);
            }
            StaticOptionCode.setResult(jsonOut,9,shareDtoList,true,""+"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,10,"",false,"");
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
            float registRebate = rebateDao.getRebateInfo().getRegistRebate();
            shareUser.setAmount(registRebate);
        }
        else{
            //根据shareId获取share信息
            Share share = shareDao.selectShareById(shareUser.getShareId());
            //通过购买商品返利比x商品价格算出返利金额
            float buyRebate = rebateDao.getRebateInfo().getBuyRebate();
            shareUser.setAmount(buyRebate*share.getGoodsVipPrice()/100);
        }
        try{
            shareUserDao.addShareUser(shareUser);
            //根据分享id获取分享信息
            Share share = shareDao.selectShareById(shareUser.getShareId());
            //更新分享总金额
            float amounts = share.getAmounts()+shareUser.getAmount();
            shareDao.UpdateShareAmountsById(share.getId(),amounts);
            //更新分享用户积分
            User user = userDao.selectUserById(share.getShareUserId());
            System.out.println("before:"+user.getMoney());
            user.setMoney(user.getMoney() + shareUser.getAmount());
            System.out.println("after:"+user.getMoney());
            System.out.println(user.getId());
            userDao.doOrder(user);
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",false,"");
        }
        return jsonOut;
    }

    /*
       根据分享id查询所有分享点击信息
    */
    @RequestMapping(value = "/selectShareUserByShareId")
    @ResponseBody
    public JSONObject selectShareUserByShareId(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int shareId = jsonIn.getIntValue("shareId");
        try{
            List<ShareUser> shareUserList = shareUserDao.selectShareUserByShareId(shareId);
            List<ShareUserDto> shareUserDtoList = new ArrayList<>();
            for(int i = 0;i<shareUserList.size();i++){
                ShareUserDto shareUserDto = new ShareUserDto();
                User user = userDao.selectUserById(shareUserList.get(i).getClickUserId());
                shareUserDto.setId(shareUserList.get(i).getId());
                shareUserDto.setClickUserId(shareUserList.get(i).getClickUserId());
                shareUserDto.setShareId(shareUserList.get(i).getShareId());
                shareUserDto.setAmount(shareUserList.get(i).getAmount());
                shareUserDto.setClickUser(user);
                shareUserDto.setStatus(shareUserList.get(i).getStatus());
                shareUserDtoList.add(shareUserDto);
            }
            StaticOptionCode.setResult(jsonOut,14,shareUserDtoList,true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",false,"");
        }
        return jsonOut;
    }
}
