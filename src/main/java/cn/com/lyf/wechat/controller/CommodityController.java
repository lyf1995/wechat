package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.CommodityDao;
import cn.com.lyf.wechat.dao.TypeDao;
import cn.com.lyf.wechat.entity.Commodity;
import cn.com.lyf.wechat.entity.Type;
import cn.com.lyf.wechat.service.CommodityService;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.baomidou.mybatisplus.plugins.Page;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
@Controller
//@Configuration
@RequestMapping(value = "/commodity/")
public class CommodityController {
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private CommodityService commodityService;

    /*
        查询所有分类
     */
    @RequestMapping(value = "/selectAllType")
    @ResponseBody
    public JSONObject selectAllType(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Type type = new Type();
        if (!(jsonIn.getString("id").equals(""))){
            type.setId(jsonIn.getIntValue("id"));
        }
        if(!(jsonIn.getString("typeName").equals(""))){
            type.setTypeName("%"+jsonIn.getString("typeName")+"%");
        }
        List<Type> typeList = typeDao.selectAllType(type);
        StaticOptionCode.setResult(jsonOut,0,typeList,true,"");
        return jsonOut;
    }

    /*
        添加分类
     */
    @RequestMapping(value = "/addType")
    @ResponseBody
    public JSONObject addType(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Type type2 = typeDao.selectTypeByName(jsonIn.getString("typeName"));
        if (type2 != null) {
            StaticOptionCode.setResult(jsonOut,21,"",false,"");
        }
        else{
            Type type = new Type();
            type.setTypeName(jsonIn.getString("typeName"));
            type.setTypeDescribe(jsonIn.getString("typeDescribe"));
            type.setIsDelete(0);
            typeDao.addType(type);
            StaticOptionCode.setResult(jsonOut,0,"",true,"");
        }
        return jsonOut;
    }

    /**
     * 修改分类信息
     * @return
     */
    @RequestMapping(value="/updateType")
    @ResponseBody
    public JSONObject updateType(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        Type type = new Type();
        type.setId(jsonIn.getIntValue("id"));
        type.setTypeName(jsonIn.getString("typeName"));
        type.setTypeDescribe(jsonIn.getString("typeDescribe"));
        JSONObject jsonOut = new JSONObject();
        try {
            typeDao.updateTypeById(type);
            StaticOptionCode.setResult(jsonOut,13,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }

    /**
     * 修改分类信息
     * @return
     */
    @RequestMapping(value="/deleteType")
    @ResponseBody
    public JSONObject deleteType(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Type type = new Type();
        type.setId(jsonIn.getIntValue("id"));
        type.setIsDelete(1);
        try {
            typeDao.deleteType(type);
            StaticOptionCode.setResult(jsonOut,13,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }

    /*
        分页查询所有商品
     */
    @RequestMapping(value = "/selectAllCommodity")
    @ResponseBody
    public JSONObject selectAllCommodity(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        Integer pageIndex=jsonIn.getInteger("pageIndex");
        Integer pageSize=jsonIn.getInteger("pageSize");
        Commodity commodity = new Commodity();

        if (!(jsonIn.getString("categoryName").equals(""))){
            commodity.setTypeId(jsonIn.getIntValue("categoryName"));
        }
        commodity.setName(jsonIn.getString("name").equals("")?null:"%"+jsonIn.getString("name")+"%");
        commodity.setSubtitle(jsonIn.getString("subtitle").equals("")?null:"%"+jsonIn.getString("subtitle")+"%");
        commodity.setIsDelete(jsonIn.getIntValue("status"));

        try {
            Page<Commodity> page = new Page<Commodity>(pageIndex,pageSize);
            page= commodityService.selectAllCommodity(page, commodity);
            StaticOptionCode.setResult(jsonOut,9,page.getRecords(),true,""+page.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,10,"",false,"");
        }
        return jsonOut;
    }

}

