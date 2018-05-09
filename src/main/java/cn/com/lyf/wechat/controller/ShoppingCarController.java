package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.CommodityDao;
import cn.com.lyf.wechat.dao.ShoppingCarDao;
import cn.com.lyf.wechat.dto.ShoppingCarDto;
import cn.com.lyf.wechat.entity.Commodity;
import cn.com.lyf.wechat.entity.ShoppingCar;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
@Controller
@RequestMapping(value = "/shoppingCar/")
public class ShoppingCarController {
    @Autowired
    private ShoppingCarDao shoppingCarDao;
    @Autowired
    private CommodityDao commodityDao;
    /*
       查询所有地址
    */
    @RequestMapping(value = "/selectAllshoppingCar")
    @ResponseBody
    public JSONObject selectAllType(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int userId = jsonIn.getIntValue("userId");
        List<ShoppingCar> shoppingCarList =shoppingCarDao.selectAllShoppingCar(userId);
        List<ShoppingCarDto> shoppingCarDtoList = new ArrayList<>();
        for(int i = 0;i<shoppingCarList.size();i++){
            Commodity commodity = commodityDao.selectCommodityById(shoppingCarList.get(i).getGoodsId());
            ShoppingCarDto shoppingCarDto = new ShoppingCarDto();
            shoppingCarDto.setGoodsId(shoppingCarList.get(i).getGoodsId());
            shoppingCarDto.setUserId(shoppingCarList.get(i).getUserId());
            shoppingCarDto.setGoodsNumber(shoppingCarList.get(i).getGoodsNumber());
            shoppingCarDto.setGoodsName(commodity.getName());
            shoppingCarDto.setGoodsVipPrice(commodity.getVipPrice());
            shoppingCarDto.setGoodsMainImage(commodity.getMainImage());
            shoppingCarDtoList.add(shoppingCarDto);
        }
        StaticOptionCode.setResult(jsonOut,9,shoppingCarDtoList,true,"");
        return jsonOut;
    }

    /*
    添加购物车
    */
    @RequestMapping(value = "/addShoppingCar")
    @ResponseBody
    public JSONObject addShoppingCar(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        ShoppingCar shoppingCar = new ShoppingCar();
        shoppingCar.setGoodsId(jsonIn.getIntValue("goodsId"));
        shoppingCar.setUserId(jsonIn.getIntValue("userId"));
        shoppingCar.setGoodsNumber(jsonIn.getIntValue("goodsNumber"));
        shoppingCar.setIsDelete(0);
        try{
            shoppingCarDao.addShoppingCar(shoppingCar);
            StaticOptionCode.setResult(jsonOut,17,"",true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,18,"",false,"");
        }

        return jsonOut;
    }
}
