package cn.com.lyf.wechat.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
public class StaticOptionCode {
    public static String AppID = "GiR0gJ8R909f2H6YMUgsaA";
    public static String AppSecret = "Zpv6lPWsvN8efrgYs1zvB";
    public static String AppKey = "AJGCgTpdhT6UlaNoCowxQ5";
    public static String MasterSecret = "ompjSQzDwu7Uh2L401UAM3";


    public static final int OPTION_RECORD 			= 0;
    public static final int OPTION_ADD 				= 1;
    public static final int OPTION_FORCEADD 		= 2;
    public static final int OPTION_REMOVE 			= 3;
    public static final int OPTION_CHANGE_PWD		= 4;
    public static final int OPTION_FORGET_PWD		= 5;

    public static String mainacc = "mainacc";
    public static String lockJL001 = "lockJL001";
    public static String gateJL001 = "gateJL001";
    public static String ringYK001 = "ringYK001";
    public static String camHK001 = "camHK001";

    //令人窒息的借鉴
    public static final int err_login_true        	   	= 0;
    public static final int err_login_false        	   	= 1;
    public static final int err_register_false        	   	= 2;
    public static final int err_register_true        	   	= 3;
    public static final int err_updatePassword_true        	   	= 4;
    public static final int err_updatePassword_false       	   	= 5;
    public static final int err_newUser_true     	   	= 6;
    public static final int err_newUser_false       	   	= 7;
    public static final int err_loginOut_true        	   	= 8;
    public static final int err_select_true        	   	= 9;
    public static final int err_select_false       	   	= 10;
    public static final int err_insertEqui_true        	   	= 11;
    public static final int err_insertEqui_false       	   	= 12;
    public static final int err_update_true      	   	= 13;
    public static final int err_update_false       	   	= 14;
    public static final int err_delete_true      	   	= 15;
    public static final int err_delete_false       	   	= 16;
    public static final int err_insert_true      	   	= 17;
    public static final int err_insert_false       	   	= 18;

    public static final String[] errStrArray={
            "登录成功",//0
            "账号或密码错误",
            "该账号已被注册",
            "该账号可以注册",
            "密码成功修改",
            "原密码错误密码修改失败",//5
            "创建用户成功",
            "创建用户失败",
            "用户登出",
            "查询成功",
            "查询失败",//10
            "设备添加成功",
            "设备添加失败",
            "修改成功",
            "修改失败",
            "删除成功",//15
            "删除失败",
            "新增成功",
            "新增失败",
            "还原成功",
            "还原失败"
    };


    public static JSONObject setResult(JSONObject jsonOut, int code, Object value, boolean success, String extraInfo)
    {
        if(jsonOut == null){
            jsonOut = new JSONObject();
        }
        jsonOut.fluentPut("errCode", code);
        jsonOut.fluentPut("errMsg", StaticOptionCode.errStrArray[code]);
        jsonOut.fluentPut("value",value);
        jsonOut.fluentPut("success",success);
        jsonOut.fluentPut("extraInfo",extraInfo);
        //   jsonOut.accumulate("errCode", code);
        //    jsonOut.accumulate("errMsg", StaticOptionCode.errStrArray[code]);
        return jsonOut;
    }

    public static String getBrief(String str){
        JSONObject json = JSONObject.parseObject(str);
        return json.getString("brief");
    }
}
