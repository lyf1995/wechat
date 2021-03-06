package cn.com.lyf.wechat.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
@TableName("t_rebate")
public class Rebate implements Serializable {
    private int id;
    private float registRebate;
    private float buyRebate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRegistRebate() {
        return registRebate;
    }

    public void setRegistRebate(float registRebate) {
        this.registRebate = registRebate;
    }

    public float getBuyRebate() {
        return buyRebate;
    }

    public void setBuyRebate(float buyRebate) {
        this.buyRebate = buyRebate;
    }
}
