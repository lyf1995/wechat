package cn.com.lyf.wechat.dto;

/**
 * Created by Administrator on 2018/5/9.
 */
public class AddressDto {
    private int id;
    private String contacts;
    private String phone;
    private String province;
    private String city;
    private String area;
    private String detailAddess;
    private int isDefault;
    private int isDelete;
    private int userId;

    public String getDetailAddess() {
        return detailAddess;
    }

    public void setDetailAddess(String detailAddess) {
        this.detailAddess = detailAddess;
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String province_city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
