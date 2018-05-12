package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.AddressDao;
import cn.com.lyf.wechat.dao.GoodsOrderDao;
import cn.com.lyf.wechat.dao.OrderDao;
import cn.com.lyf.wechat.dao.UserDao;
import cn.com.lyf.wechat.dto.OrderDto;
import cn.com.lyf.wechat.entity.Address;
import cn.com.lyf.wechat.entity.GoodsOrder;
import cn.com.lyf.wechat.entity.Order;
import cn.com.lyf.wechat.entity.User;
import cn.com.lyf.wechat.service.OrderService;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
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
    @Autowired
    private GoodsOrderDao goodsOrderDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private OrderService orderService;


    /*
       分页查询订单信息
    */
    @RequestMapping(value = "/selectAllOrder")
    @ResponseBody
    public JSONObject selectAllOrder(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Integer pageIndex=jsonIn.getInteger("pageIndex");
        Integer pageSize=jsonIn.getInteger("pageSize");
        Order order = new Order();
        order.setNumber(jsonIn.getString("number").equals("")?null:jsonIn.getString("number"));
        if (!(jsonIn.getString("status").equals(""))){
            order.setStatus(jsonIn.getIntValue("status"));
        }
        String phone = jsonIn.getString("phone").equals("")?null:jsonIn.getString("phone");
        if(phone!=null){
            User user = userDao.selectUsername(phone);
            order.setUserId(user.getId());
        }
        String orderStartTime = jsonIn.getString("orderStartTime");
        String orderEndTime = jsonIn.getString("orderEndTime");
        try {
            Page<Order> page = new Page<Order>(pageIndex,pageSize);
            page= orderService.selectAllOrder(page,order,orderStartTime,orderEndTime);
            List<OrderDto> orderDtoList = new ArrayList<>();
            for(int i = 0; i<page.getRecords().size();i++){
                OrderDto orderDto = new OrderDto();
                orderDto.setId(page.getRecords().get(i).getId());
                orderDto.setNumber(page.getRecords().get(i).getNumber());
                orderDto.setStatus(page.getRecords().get(i).getStatus());
                orderDto.setTotalAmount(page.getRecords().get(i).getTotalAmount());
                orderDto.setRemarks(page.getRecords().get(i).getRemarks());
                orderDto.setOrderTime(page.getRecords().get(i).getOrderTime());
                orderDto.setUserId(page.getRecords().get(i).getUserId());
                orderDto.setAddressId(page.getRecords().get(i).getAddressId());

                int userId = page.getRecords().get(i).getUserId();
                User user = userDao.selectUserById(userId);
                orderDto.setPhone(user.getPhone());

                int addressId = page.getRecords().get(i).getAddressId();
                Address address = addressDao.selectAddressById(addressId);
                orderDto.setAddress(address);

                List<GoodsOrder> productsList = goodsOrderDao.selectGoodsOrderByOrderId(page.getRecords().get(i).getId());
                orderDto.setProductsList(productsList);

                orderDtoList.add(orderDto);
            }
            StaticOptionCode.setResult(jsonOut,9,orderDtoList,true,""+page.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,10,"",false,"");
        }
        return jsonOut;
    }


    /*
       根据用户id查看用户所有订单
    */
    @RequestMapping(value = "/selectOrderByUserId")
    @ResponseBody
    public JSONObject selectOrderByUserId(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int userId = jsonIn.getIntValue("userId");
        int status = jsonIn.getIntValue("status");
        try{
            List<Order> orderList = orderDao.selectOrderByUserId(userId,status);
            List<OrderDto> orderDtoList = new ArrayList<>();
            for(int i = 0;i < orderList.size();i ++){
                List<GoodsOrder> goodsOrderList = goodsOrderDao.selectGoodsOrderByOrderId(orderList.get(i).getId());
                OrderDto orderDto = new OrderDto();
                orderDto.setId(orderList.get(i).getId());
                orderDto.setNumber(orderList.get(i).getNumber());
                orderDto.setStatus(orderList.get(i).getStatus());
                orderDto.setUserId(orderList.get(i).getUserId());
                orderDto.setAddressId(orderList.get(i).getAddressId());
                orderDto.setTotalAmount(orderList.get(i).getTotalAmount());
                orderDto.setRemarks(orderList.get(i).getRemarks());
                orderDto.setOrderTime(orderList.get(i).getOrderTime());
                orderDto.setProductsList(goodsOrderList);
                orderDtoList.add(orderDto);
            }
            StaticOptionCode.setResult(jsonOut,9,orderDtoList,true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut, 10, "", false, "");
        }
        return jsonOut;
    }




    /*
       下单
    */
    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public JSONObject addAddress(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        try{
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
            orderDao.addOrder(order);
            JSONArray goodsList = jsonIn.getJSONArray("goodsList");
            for(int i =0;i<goodsList.size();i++){
                JSONObject goods = goodsList.getJSONObject(i);
                GoodsOrder goodsOrder = new GoodsOrder();
                goodsOrder.setGoodsId(goods.getIntValue("productId"));
                goodsOrder.setOrderId(order.getId());
                goodsOrder.setGoodsNumber(goods.getIntValue("amount"));
                goodsOrder.setGoodsName(goods.getString("productName"));
                goodsOrder.setGoodsVipPrice(goods.getFloatValue("vipPrice"));
                goodsOrder.setGoodsMainImage(goods.getString("mainImage"));
                goodsOrderDao.addGoodsOrder(goodsOrder);
            }
            StaticOptionCode.setResult(jsonOut,17,order.getId(),true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,18,"",false,"");
        }

        return jsonOut;
    }

    /*
      删除订单
   */
    @RequestMapping(value = "/deleteOrder")
    @ResponseBody
    public JSONObject deleteOrder(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int id = jsonIn.getIntValue("id");
        try{
            orderDao.deleteOrder(id);
            StaticOptionCode.setResult(jsonOut,18,"",true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,18,"",false,"");
        }
        return jsonOut;
    }

    /*
      修改订单状态
   */
    @RequestMapping(value = "/changeOrderStatus")
    @ResponseBody
    public JSONObject changeOrderStatus(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int id = jsonIn.getIntValue("id");
        int status = jsonIn.getIntValue("status");

        if(jsonIn.getIntValue("status")==1){
            Order order = orderDao.selectOrderById(id);
            User user = userDao.selectUserById(order.getUserId());
            user.setMoney(user.getMoney()-order.getTotalAmount());
            userDao.doOrder(user);
        }
        try{
            orderDao.changeOrderStatus(id,status);
            StaticOptionCode.setResult(jsonOut,18,"",true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,18,"",false,"");
        }
        return jsonOut;
    }

    /*
      根据id查看订单
   */
    @RequestMapping(value = "/selectOrderById")
    @ResponseBody
    public JSONObject selectOrderById(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int id = jsonIn.getIntValue("id");
        try{
            Order order = orderDao.selectOrderById(id);
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setNumber(order.getNumber());
            orderDto.setStatus(order.getStatus());
            orderDto.setUserId(order.getUserId());
            orderDto.setAddressId(order.getAddressId());
            orderDto.setTotalAmount(order.getTotalAmount());
            orderDto.setRemarks(order.getRemarks());
            orderDto.setOrderTime(order.getOrderTime());
            orderDto.setType(order.getType());
            orderDto.setIsDelete(order.getIsDelete());
            List<GoodsOrder> goodsOrderList = goodsOrderDao.selectGoodsOrderByOrderId(orderDto.getId());
            orderDto.setProductsList(goodsOrderList);
            Address address = addressDao.selectAddressById(orderDto.getAddressId());
            orderDto.setAddress(address);
            StaticOptionCode.setResult(jsonOut,18,orderDto,true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,18,"",false,"");
        }
        return jsonOut;
    }
}
