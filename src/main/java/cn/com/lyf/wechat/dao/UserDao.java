package cn.com.lyf.wechat.dao;



import cn.com.lyf.wechat.entity.User;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Date;
import java.util.List;


@Mapper
public interface UserDao extends BaseMapper<User>{
	 User selectUserByName(@Param("phone")String phone,@Param("password")String password,@Param("type")int type);

	 User selectUsername(@Param("phone")String phone);

	 void newUser(@Param("user")User user);

	 void updatePasswordById(@Param("id")int id,@Param("newPassword")String newPassword);

	 void updateLastLoginById(@Param("id")int id,@Param("recentLoginTime")Date recentLoginTime);

	 void updateUserById(@Param("user")User user);

	 void delectUser(@Param("user")User user);

	 List<User> selectAllUser(Page<User> page,@Param("user") User user);
}
