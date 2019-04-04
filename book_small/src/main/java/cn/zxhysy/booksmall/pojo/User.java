package cn.zxhysy.booksmall.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @className: User
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:04:08
 */
@Table(name = "tb_user")
public class User {
    @Id
    private Integer id;

    /**
     * 名字
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 常用地址
     */
    private String address;

    /**
     * 微信openid
     */
    private String openid;

    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
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
     * @return nick_name - 名字
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置名字
     *
     * @param nickName 名字
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
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
     * 获取微信openid
     *
     * @return openid - 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openid
     *
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return avatar_url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
}