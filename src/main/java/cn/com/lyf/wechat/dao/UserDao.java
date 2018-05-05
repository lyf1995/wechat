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
	 User selectUserByName(@Param("username")String username,@Param("password")String password);

	 User selectUsername(@Param("username")String username);

	 void newUser(@Param("user")User user);

	 void updatePasswordById(@Param("id")int id,@Param("newPassword")String newPassword);

	 void updateLastLoginById(@Param("id")int id,@Param("lastLogin")Date lastLogin);

	 List<User> selectAllUser(Page<User> page,@Param("user") User user);
}
