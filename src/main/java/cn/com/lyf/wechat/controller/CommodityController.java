package cn.com.lyf.wechat.controller;

import cn.com.lyf.wechat.dao.TypeDao;
import cn.com.lyf.wechat.dto.TypeDto;
import cn.com.lyf.wechat.entity.Type;
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
 * Created by Administrator on 2018/5/7.
 */
@Controller
//@Configuration
@RequestMapping(value = "/commodity/")
public class CommodityController {
    @Autowired
    private TypeDao typeDao;

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
//
        type.setTypeName("%"+jsonIn.getString("typeName")+"%");

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
}

