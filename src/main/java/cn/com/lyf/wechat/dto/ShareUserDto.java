package cn.com.lyf.wechat.dto;

/**
 * Created by Administrator on 2018/5/13 0013.
 */
public class ShareUserDto {
    private int id;
    private int shareId;
    private int clickUserId;
    private int status;
    private float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getClickUserId() {
        return clickUserId;
    }

    public void setClickUserId(int clickUserId) {
        this.clickUserId = clickUserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
