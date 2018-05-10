package cn.com.lyf.wechat.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/9 0009.
 */
@TableName("t_goods_order")
public class GoodsOrder implements Serializable {
    private int id;
    private int orderId;
    private int goodsId;
    private int goodsNumber;
    private float goodsVipPrice;
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public float getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(float goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }
}
