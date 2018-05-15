package cn.com.lyf.wechat.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * Created by Administrator on 2018/5/13 0013.
 */
@TableName("t_share")
public class Share {
    private int id;
    private int shareUserId;
    private int goodsId;
    private String goodsName;
    private String goodsMainImage;
    private float goodsVipPrice;
    private String shareUrl;
    private float amounts;
    private Date shareTime;
    private int isDelete;

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(int shareUserId) {
        this.shareUserId = shareUserId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsMainImage() {
        return goodsMainImage;
    }

    public void setGoodsMainImage(String goodsMainImage) {
        this.goodsMainImage = goodsMainImage;
    }

    public float getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(float goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public float getAmounts() {
        return amounts;
    }

    public void setAmounts(float amounts) {
        this.amounts = amounts;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

}
