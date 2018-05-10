package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.OrderDao;
import cn.com.lyf.wechat.dao.UserDao;
import cn.com.lyf.wechat.entity.Order;
import cn.com.lyf.wechat.entity.User;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
@Controller
@RequestMapping(value = "/order/")
public class OrderController {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    /*
       添加地址
    */
    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public JSONObject addAddress(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Order order = new Order();
        order.setUserId(jsonIn.getIntValue("userId"));
        order.setAddressId(jsonIn.getIntValue("addressId"));
        order.setStatus(jsonIn.getIntValue("status"));
        order.setTotalAmount(jsonIn.getFloatValue("totalAmount"));
        order.setRemarks(jsonIn.getString("remarks"));
        order.setNumber("lyf"+new Date().getTime());
        order.setOrderTime(new Date());
        order.setIsDelete(0);
        order.setType(0);
        if(jsonIn.getIntValue("status")==1){
            User user = userDao.selectUserById(order.getUserId());
            user.setMoney(user.getMoney()-order.getTotalAmount());
            userDao.doOrder(user);
        }
        try{
            orderDao.addOrder(order);
            StaticOptionCode.setResult(jsonOut,17,"",true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,18,"",false,"");
        }

        return jsonOut;
    }
}
