package cn.com.lyf.wechat.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.com.lyf.wechat.dao.UserDao;
import cn.com.lyf.wechat.dto.UserDto;
import cn.com.lyf.wechat.entity.User;
import cn.com.lyf.wechat.service.UserService;
import cn.com.lyf.wechat.util.Global;
import cn.com.lyf.wechat.util.Md5Utils;
import cn.com.lyf.wechat.util.StaticOptionCode;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
//@Configuration
@RequestMapping(value = "/user/")
public class UserController {
    private static final Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public JSONObject submitLogin(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        String username = jsonIn.getString("username");
        String password = jsonIn.getString("password");
        JSONObject jsonOut = new JSONObject();
        //设置response.addHeader
        //Global.initResponse();
        UserDto userDto = new UserDto();//不够再加
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, Md5Utils.MD5Encode(password, "utf-8", false));
            SecurityUtils.getSubject().login(token);
            Session session = SecurityUtils.getSubject().getSession();
            User user = userDao.selectUserByName(username, Md5Utils.MD5Encode(password, "utf-8", false));
            if (user != null){
                userDao.updateLastLoginById(user.getId(), new Date());
                userDto.setId(user.getId());
                userDto.setUserName(user.getUserName());
                userDto.setRealName(user.getRealName());
                userDto.setRoleId(user.getRoleId());
                StaticOptionCode.setResult(jsonOut,0,userDto,true,"");
            }else {
                StaticOptionCode.setResult(jsonOut,1,"",false,"");
            }
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,1,"",false,"");
        }
        return jsonOut;
    }

    /**
     * 账号重复判断
     * hsz 2018年3月13日17:31:51
     */
    @RequestMapping(value = "/selectUsername")
    @ResponseBody
    public JSONObject selectUsername(@RequestBody String json) {
        JSONObject jsString = JSONObject.parseObject(json);
        String username = jsString.getString("username");
        //设置response.addHeader
//        Global.initResponse();

        JSONObject jsonOut = new JSONObject();
        User user = userDao.selectUsername(username);
        if (user != null) {
            StaticOptionCode.setResult(jsonOut,2,"",false,"");
        } else {
            StaticOptionCode.setResult(jsonOut,3,"",true,"");
        }
        return jsonOut;
    }

    /**
     * 修改密码
     * 1.有原密码修改新密码
     * 2.超级管理员帮助修改密码
     * hsz 2018年3月15日14:14:04
     */
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public JSONObject updatePassword(HttpServletRequest request,@RequestBody String json) {
        JSONObject jsString = JSONObject.parseObject(json);
        int type = jsString.getInteger("type");
        String username = jsString.getString("username");
        String password = jsString.getString("password");
        String newPassword = jsString.getString("newPassword");
        String man = jsString.getString("man");
        //设置response.addHeader
//        Global.initResponse();
        String ip = Global.getIpAddr(request);
        JSONObject jsonOut = new JSONObject();
        User user = new User();
        boolean status = true;
        try {
            switch (type) {
                case 1: {
                    user = userDao.selectUserByName(username, Md5Utils.MD5Encode(password, "utf-8", false));
                    if (user != null) {
                        userDao.updatePasswordById(user.getId(), Md5Utils.MD5Encode(newPassword, "utf-8", false));
                        StaticOptionCode.setResult(jsonOut,4,"",true,"");
                    } else {
                        status = false;
                        StaticOptionCode.setResult(jsonOut,5,"",false,"");
                    }
                    break;
                }
                case 2: {
                    if (jsString.getString("roleId") == "7"){
                        user = userDao.selectUsername(username);
                        if (user != null) {
                            userDao.updatePasswordById(user.getId(), Md5Utils.MD5Encode(newPassword, "utf-8", false));
                            StaticOptionCode.setResult(jsonOut,4,"",true,"");
                        }
                        break;
                    }else{
                        StaticOptionCode.setResult(jsonOut,19,"",false,"");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonOut;
    }

    /**
     * 新建用户
     * hsz 2018年3月13日16:15:28
     */
    @RequestMapping(value="/newUser",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject newUser(HttpServletRequest request,@RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        User user2 = userDao.selectUsername(jsonIn.getString("userName"));
        if (user2 != null) {
            StaticOptionCode.setResult(jsonOut,2,"",false,"");
        } else {
            StaticOptionCode.setResult(jsonOut,3,"",true,"");
        }
        User user = new User();
        user.setRoleId(jsonIn.getString("roleId"));
        user.setUserName(jsonIn.getString("userName"));
        user.setPassword(jsonIn.getString("password"));
        user.setRealName(jsonIn.getString("realName"));
        user.setMobile(jsonIn.getIntValue("mobile"));
        user.setEmail(jsonIn.getString("email"));
        user.setPassword(Md5Utils.MD5Encode(user.getPassword(),"utf-8",false));
        user.setStatus("0");
        user.setCreateTime(new Date());
        user.setCreateBy(jsonIn.getString("man"));
        if (user.getRoleId().equals("7")) {
            user.setRoleName("管理员");
        }else{
            user.setRoleName("用户");
        }
        //设置response.addHeader
        //        Global.initResponse();
        String ip=Global.getIpAddr(request);
        try {
            userDao.newUser(user);
            StaticOptionCode.setResult(jsonOut,6,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,7,"",false,"");
        }
        return jsonOut;
    }

    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping(value="/updateUser")
    @ResponseBody
    public JSONObject updateUser(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        User user = new User();
        user.setId(jsonIn.getIntValue("id"));
        user.setRoleId(jsonIn.getString("roleId"));//
        user.setUserName(jsonIn.getString("username"));
//        user.setPassword(jsonIn.getString("password"));
        user.setRealName(jsonIn.getString("realName"));
        user.setMobile(jsonIn.getIntValue("mobile"));
        user.setEmail(jsonIn.getString("email"));
//        user.setDepartment(jsonIn.getString("department"));
//        user.setBirthday(jsonIn.getDate("birthday"));
//        user.setCity(jsonIn.getString("city"));
//        user.setQq(jsonIn.getString("qq"));
//        user.setWeixin(jsonIn.getString("weixin"));
//        user.setSex(jsonIn.getString("sex"));
        user.setUpdateBy(jsonIn.getString("man"));
        user.setUpdateTime(new Date());
        String ip=Global.getIpAddr(request);
        JSONObject jsonOut = new JSONObject();
        try {
            userDao.updateById(user);
            StaticOptionCode.setResult(jsonOut,13,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,14,"",true,"");
        }
        return jsonOut;
    }

    /**
     * 分页查询用户信息
     * @return
     */
    @RequestMapping(value="/selectAllUser")
    @ResponseBody
    public JSONObject selectAllUser(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        Integer current=jsonIn.getInteger("current");
        Integer size=jsonIn.getInteger("size");
        User user = new User();
        //工号
        user.setId(jsonIn.getString("id").equals("")?null:jsonIn.getIntValue("id"));
        user.setUserName(jsonIn.getString("userName").equals("")?null:"%"+jsonIn.getString("userName")+"%");
        user.setRealName(jsonIn.getString("realName").equals("")?null:"%"+jsonIn.getString("realName")+"%");
        user.setRoleId(jsonIn.getString("roleId"));

        JSONObject jsonOut = new JSONObject();
        try {
            Page<User> page = new Page<User>(current,size);
            page= userService.selectAllUser(page, user);
            StaticOptionCode.setResult(jsonOut,9,page.getRecords(),true,""+page.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,10,"",false,"");
        }
        return jsonOut;
    }


    /**
     * 删除用户信息
     * @return
     */
    @RequestMapping(value="/delectUser")
    @ResponseBody
    public JSONObject delectUser(HttpServletRequest request,@RequestBody String json){
        JSONObject jsonIn = JSONObject.parseObject(json);
        User user = new User();
        //工号
        user.setId(jsonIn.getIntValue("id"));
        user.setIsDelete("1");
        String man = jsonIn.getString("man");
        String ip=Global.getIpAddr(request);
        JSONObject jsonOut = new JSONObject();
        try {
            userDao.updateById(user);
            StaticOptionCode.setResult(jsonOut,15,"",true,"");
        } catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,16,"",false,"");
        }
        return jsonOut;
    }


    /**
     * 注销
     * @return
     */
    @RequestMapping(value="/logout")
    public JSONObject logout(){
        JSONObject jsonOut = new JSONObject();
    	try {
    	  //使用权限管理工具进行用户的退出，跳出登陆，给出提示信息
           SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
        StaticOptionCode.setResult(jsonOut,8,"",true,"");
		return jsonOut;
    }
}
