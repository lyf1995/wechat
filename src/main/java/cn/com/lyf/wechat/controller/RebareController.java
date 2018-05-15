package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.RebateDao;
import cn.com.lyf.wechat.entity.Rebate;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
@Controller
@RequestMapping(value = "/rebate/")
public class RebareController {
    @Autowired
    private RebateDao rebateDao;

    /*
        获取返利设置
     */
    @RequestMapping(value = "/getRebateInfo")
    @ResponseBody
    public JSONObject getRebateInfo(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Rebate rebate = rebateDao.getRebateInfo();
        StaticOptionCode.setResult(jsonOut,9,rebate,true,"");
        return jsonOut;
    }

    /*
        修改返利设置
     */
    @RequestMapping(value = "/updateRebate")
    @ResponseBody
    public JSONObject updateRebate(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Rebate rebate = new Rebate();
        rebate.setRegistRebate(jsonIn.getFloatValue("registRebate"));
        rebate.setBuyRebate(jsonIn.getFloatValue("buyRebate"));
        rebateDao.updateRebate(rebate);
        StaticOptionCode.setResult(jsonOut,9,"",true,"");
        return jsonOut;
    }
}
