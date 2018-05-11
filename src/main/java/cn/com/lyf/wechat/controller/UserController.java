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
import com.alibaba.fastjson.JSONArray;
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
        String phone = jsonIn.getString("phone");
        String password = jsonIn.getString("password");
        int type = jsonIn.getInteger("type");
        JSONObject jsonOut = new JSONObject();
        //设置response.addHeader
        //Global.initResponse();
        UserDto userDto = new UserDto();//不够再加
        try {

            User user = userDao.selectUserByName(phone,password,type);
            System.out.println(user);
            if (user != null){
                userDao.updateLastLoginById(user.getId(), new Date());
                userDto.setId(user.getId());
                userDto.setPhone(user.getPhone());
                userDto.setName(user.getName());
                userDto.setMoney(user.getMoney());
                userDto.setPassword(user.getPassword());
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
     * 注册
     */
    @RequestMapping(value = "/regist")
    @ResponseBody
    public JSONObject regist(HttpServletRequest request, @RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        User user2 = userDao.selectUsername(jsonIn.getString("phone"));
        if (user2 != null) {
            StaticOptionCode.setResult(jsonOut,2,"",false,"");
        } else {
            User user = new User();
            user.setPhone(jsonIn.getString("phone"));
            user.setName(jsonIn.getString("phone"));
            user.setPassword(jsonIn.getString("password"));
            user.setType(1);
            user.setIsDelete(0);
            user.setRegistTime(new Date());
            try {
                userDao.newUser(user);
                StaticOptionCode.setResult(jsonOut,6,"",true,"");
            } catch (Exception e) {
                e.printStackTrace();
                StaticOptionCode.setResult(jsonOut,7,"",false,"");
            }
            StaticOptionCode.setResult(jsonOut,3,"",true,"");
        }
        return jsonOut;
    }

    /**
     * 账号重复判断
     * lyf 2018年3月13日17:31:51
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
     * lyf 2018年3月15日14:14:04
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
//                    user = userDao.selectUserByName(username, Md5Utils.MD5Encode(password, "utf-8", false));
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
     * 根据userId查询用户
     * lyf 2018年3月13日16:15:28
     */
    @RequestMapping(value="/selectUserById",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject selectUserById(HttpServletRequest request,@RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        int userId = jsonIn.getIntValue("userId");
        try{
            User user = userDao.selectUserById(userId);
            StaticOptionCode.setResult(jsonOut,7,user,true,"");
        }catch (Exception e) {
            e.printStackTrace();
            StaticOptionCode.setResult(jsonOut,7,"",false,"");
        }
        return jsonOut;
    }

    /**
     * 新建用户
     * lyf 2018年3月13日16:15:28
     */
    @RequestMapping(value="/newUser",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject newUser(HttpServletRequest request,@RequestBody String json) {
        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONObject jsonOut = new JSONObject();
        User user2 = userDao.selectUsername(jsonIn.getString("phone"));
        if (user2 != null) {
            StaticOptionCode.setResult(jsonOut,2,"",false,"");
        } else {
            User user = new User();
            user.setPhone(jsonIn.getString("phone"));
            user.setName(jsonIn.getString("name"));
            user.setPassword(jsonIn.getString("password"));
            user.setMoney(1000);
            user.setType(1);
            user.setIsDelete(0);
            user.setRegistTime(new Date());
            try {
                userDao.newUser(user);
                StaticOptionCode.setResult(jsonOut,6,"",true,"");
            } catch (Exception e) {
                e.printStackTrace();
                StaticOptionCode.setResult(jsonOut,7,"",false,"");
            }
            StaticOptionCode.setResult(jsonOut,3,"",true,"");
        }

        return jsonOut;
    }


    /**
     * 批量创建用户
     * lyf 2018年3月13日16:15:28
     */
    @RequestMapping(value="/batchAddUser",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject batchAddUser(HttpServletRequest request,@RequestBody String json) {
//        JSONObject jsonIn = JSONObject.parseObject(json);
        JSONArray jsonIn = JSONArray.parseArray(json);
        JSONObject jsonOut = new JSONObject();
        for(int i = 0;i<jsonIn.size();i++){
            JSONObject obj = jsonIn.getJSONObject(i);
            User user2 = userDao.selectUsername(obj.getString("phone"));
            if (user2 != null) {
                StaticOptionCode.setResult(jsonOut,2,"",false,obj.getString("phone"));
                break;
            } else {
                User user = new User();
                user.setPhone(obj.getString("phone"));
                user.setName(obj.getString("name"));
                user.setPassword(obj.getString("password"));
                user.setMoney(1000);
                user.setType(1);
                user.setIsDelete(0);
                user.setRegistTime(new Date());
                try {
                    userDao.newUser(user);
                    StaticOptionCode.setResult(jsonOut,6,"",true,"");
                } catch (Exception e) {
                    e.printStackTrace();
                    StaticOptionCode.setResult(jsonOut,7,"",false,"");
                }
            }
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
        user.setPhone(jsonIn.getString("phone"));//
        user.setName(jsonIn.getString("name"));
        user.setPassword(jsonIn.getString("password"));
        JSONObject jsonOut = new JSONObject();
        try {
            userDao.updateUserById(user);
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
        Integer pageIndex=jsonIn.getInteger("pageIndex");
        Integer pageSize=jsonIn.getInteger("pageSize");
        User user = new User();
        user.setPhone(jsonIn.getString("phone").equals("")?null:jsonIn.getString("phone"));
        user.setName(jsonIn.getString("name").equals("")?null:"%"+jsonIn.getString("name")+"%");
        String recentLoginStartTime = jsonIn.getString("recentLoginStartTime");
        String recentLoginEndTime = jsonIn.getString("recentLoginEndTime");
        String registStartTime = jsonIn.getString("registStartTime");
        String registEndTime = jsonIn.getString("registEndTime");

        JSONObject jsonOut = new JSONObject();
        try {
            Page<User> page = new Page<User>(pageIndex,pageSize);
            page= userService.selectAllUser(page, user,recentLoginStartTime,recentLoginEndTime,registStartTime,registEndTime);
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
        user.setId(jsonIn.getIntValue("id"));
        user.setIsDelete(1);
        JSONObject jsonOut = new JSONObject();
        try {
            userDao.delectUser(user);
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
