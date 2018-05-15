//package cn.com.lyf.wechat.realms;
//
//import org.apache.shiro.authc.UsernamePasswordToken;
//
///**
// * 重写{@link #UsernamePasswordToken},增加{@link #loginType}属性,该属性是在登陆界面form表单中传递过来的,定义了用户使用的登陆类型
// * @author zd
// *
// */
//@SuppressWarnings("serial")
//public class UsernamePasswordLoginTypeToken extends UsernamePasswordToken{
//
//	private String loginType;
//	public UsernamePasswordLoginTypeToken(String username, String password, String loginType) {
//        super(username, password);
//        this.loginType = loginType;
//    }
//	public String getLoginType() {
//		return loginType;
//	}
//	public void setLoginType(String loginType) {
//		this.loginType = loginType;
//	}
//
//
//}
