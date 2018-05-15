package cn.com.lyf.wechat.dto;

import java.util.Date;
/**
 * Created by hsz on 2018/3/20.
 */
public class UserDto {
    private int id;
    private String phone;
    private String name;
    private String password;
    private float money;
    private Date registTime;
    private Date recentLoginTime;

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getRecentLoginTime() {
        return recentLoginTime;
    }

    public void setRecentLoginTime(Date recentLoginTime) {
        this.recentLoginTime = recentLoginTime;
    }
}
