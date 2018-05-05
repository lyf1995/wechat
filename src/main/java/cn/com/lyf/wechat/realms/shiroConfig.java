package cn.com.lyf.wechat.realms;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {

    /**
     * 负责shiroBean的生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    /**
     *这是个自定义的认证类，继承子AuthorizingRealm，负责用户的认证和权限处理
     */
    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
    public MyShiroRealm shiroRealm(){
        MyShiroRealm realm = new MyShiroRealm();
        //realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /** 安全管理器
     * 将realm加入securityManager
     * @Qualifier("dataSource") DataSource dataSource
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        //注意是DefaultWebSecurityManager！！！
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        JdbcRealm jdbcRealm = new JdbcRealm();
//        jdbcRealm.setAuthenticationQuery("select password from t_user where username = ?");
//        jdbcRealm.setDataSource(dataSource);
//        securityManager.setRealm(jdbcRealm);
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
//    }

    /** shiro filter 工厂类
     * 1.定义ShiroFilterFactoryBean
     * 2.设置SecurityManager
     * 3.配置拦截器
     * 4.返回定义ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注册securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/#/login/**","anon");
        filterChainDefinitionMap.put("/#/*", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/#/**", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/#/*.*", "authc");
//        filterChainDefinitionMap.put("/equipment/**","roles[7]");
        //设置登录界面，如果不设置为寻找web根目录下的文件
        shiroFilterFactoryBean.setLoginUrl("/#/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("shiro拦截工厂注入类成功");
        return shiroFilterFactoryBean;
    }

}