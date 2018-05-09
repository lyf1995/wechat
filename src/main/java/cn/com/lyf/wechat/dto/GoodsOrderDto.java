package cn.com.lyf.wechat.dto;


/**
 * Created by Administrator on 2018/5/9 0009.
 */
public class GoodsOrderDto {
    private int id;
    private int goodsId;
    private int goodsNumber;
    private float goodsVipPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
