package cn.zxhysy.booksmall.pojo.vo;

import java.util.Date;

/**
 * @className: AdminVO
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:01:46
 */
public class AdminVO {

    private Integer id;

    private String userToken;
    /**
     * 名字
     */
    private String name;

    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 常用地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取常用地址
     *
     * @return address - 常用地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置常用地址
     *
     * @param address 常用地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String usertoken) {
        this.userToken = usertoken;
    }

    @Override
    public String toString() {
        return "AdminVO{" +
                "id=" + id +
                ", userToken='" + userToken + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}