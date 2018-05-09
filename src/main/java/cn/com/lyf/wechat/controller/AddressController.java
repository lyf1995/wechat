package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.AddressDao;
import cn.com.lyf.wechat.entity.Address;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
@Controller
@RequestMapping(value = "/address/")
public class AddressController {
    @Autowired
    private AddressDao addressDao;
    /*
        查询所有地址
     */
    @RequestMapping(value = "/selectAllAddress")
    @ResponseBody
    public JSONObject selectAllType(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int userId = jsonIn.getIntValue("userId");
        Address address = new Address();
        List<Address> addressList = addressDao.selectAllAddress(userId);
        StaticOptionCode.setResult(jsonOut,9,addressList,true,"");
        return jsonOut;
    }

    /**
     * 修改地址
     * @return
     */
    @RequestMapping(value="/updateAddress")
    @ResponseBody
    public JSONObject updateAddress(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        Address address = new Address();
        address.setId(jsonIn.getIntValue("id"));
        address.setContacts(jsonIn.getString("contacts"));
        address.setPhone(jsonIn.getString("phone"));
        address.setProvince(jsonIn.getString("province"));
        address.setCity(jsonIn.getString("city"));
        address.setArea(jsonIn.getString("area"));
        address.setDetailAddress(jsonIn.getString("detailAddress"));
        JSONObject jsonOut = new JSONObject();
        try {
            addressDao.updateAddress(address);
            StaticOptionCode.setResult(jsonOut,13,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }

    /*
       添加地址
    */
    @RequestMapping(value = "/addAddress")
    @ResponseBody
    public JSONObject addAddress(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Address address = new Address();
        address.setContacts(jsonIn.getString("contacts"));
        address.setPhone(jsonIn.getString("phone"));
        address.setProvince(jsonIn.getString("province"));
        address.setCity(jsonIn.getString("city"));
        address.setArea(jsonIn.getString("area"));
        address.setDetailAddress(jsonIn.getString("detailAddress"));
        if(jsonIn.getString("isDefault").equals("false")){
            address.setIsDefault(0);
        }
        else{
            address.setIsDefault(1);
        }
        address.setUserId(jsonIn.getIntValue("userId"));
        address.setIsDelete(0);
        try{
            addressDao.addAddress(address);
            StaticOptionCode.setResult(jsonOut,17,"",true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,18,"",false,"");
        }

        return jsonOut;
    }

    /**
     * 删除地址
     * @return
     */
    @RequestMapping(value="/deleteAddress")
    @ResponseBody
    public JSONObject deleteAddress(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int id = jsonIn.getIntValue("id");
        try {
            addressDao.deleteAddress(id);
            StaticOptionCode.setResult(jsonOut,13,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }

     /**
     * 通过地址id查询地址信息
     * @return
     */
    @RequestMapping(value="/selectAddressById")
    @ResponseBody
    public JSONObject selectAddressById(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int addressId = jsonIn.getIntValue("addressId");
        try {
            Address address = addressDao.selectAddressById(addressId);
            StaticOptionCode.setResult(jsonOut,13,address,true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }

    /**
     * 通过id将地址设为默认地址
     * @return
     */
     @RequestMapping(value="/updateDefault")
     @ResponseBody
     public JSONObject updateDefault(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int addressId = jsonIn.getIntValue("addressId");
        try {
            addressDao.updateDefault(addressId);
            StaticOptionCode.setResult(jsonOut,13,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
     }

    /**
     * 通过用户id查询默认收货地址信息
     * @return
     */
    @RequestMapping(value="/selectDefaultAddressByUserId")
    @ResponseBody
    public JSONObject selectDefaultAddressByUserId(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int userId = jsonIn.getIntValue("userId");
        try {
            Address address = addressDao.selectDefaultAddressByUserId(userId);
            StaticOptionCode.setResult(jsonOut,13,address,true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }
}
