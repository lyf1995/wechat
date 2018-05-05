package cn.com.lyf.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/15.
 */
public class Global {
//    public static HttpServletResponse initResponse(){
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletResponse response = sra.getResponse();
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        return  response;
//    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     *
     * @param success     是否成功
     * @param errorCode   错误代码
     * @param errorMsg    错误信息
     * @param value        返回值
     * @param extraInfo   额外的返回值
     * @return
     */
    public static Map<String,Object> getResultMap(boolean success, String errorCode, String errorMsg, Object value, String extraInfo){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("success", success);
        resultMap.put("errorCode", errorCode);
        resultMap.put("errorMsg", errorMsg);
        resultMap.put("value",value);
        resultMap.put("extraInfo",extraInfo);
        return  resultMap;
    }

    //表名常量
    public static String T_USER ="T_USER";
    public static String T_ROLES ="T_ROLES";
    public static String T_ROAD ="T_ROAD";
    public static String T_EQUIPMENT ="T_EQUIPMENT";
    public static String T_GROUP ="T_GROUP";
    public static String T_EQUIPMENTANDGROUP ="T_EQUIPMENTANDGROUP";
    public static String PLAYVIDEO ="PLAYVIDEO";
    public static String LOGIN ="LOGIN";
    //操作常量
    public static String INSERT ="INSERT";
    public static String UPDATE ="UPDATE";
    public static String SELECT ="SELECT";
    public static String DELETE ="DELETE";
}
