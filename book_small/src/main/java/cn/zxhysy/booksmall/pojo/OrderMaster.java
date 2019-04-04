package cn.zxhysy.booksmall.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @className: OrderMaster
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:03:57
 */
@Table(name = "tb_order_master")
public class OrderMaster {
    @Id
    private String id;

    /**
     * 买家名字
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 买家电话
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 买家地址
     */
    @Column(name = "user_address")
    private String userAddress;

    /**
     * 买家微信openid
     */
    @Column(name = "user_openid")
    private String userOpenid;

    /**
     * 订单总额
     */
    private BigDecimal amount;

    /**
     * 订单状态，默认0：新下单
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 支付状态，默认0：未支付
     */
    @Column(name = "pay_status")
    private Byte payStatus;

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
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取买家名字
     *
     * @return user_name - 买家名字
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置买家名字
     *
     * @param userName 买家名字
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取买家电话
     *
     * @return user_phone - 买家电话
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置买家电话
     *
     * @param userPhone 买家电话
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取买家地址
     *
     * @return user_address - 买家地址
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 设置买家地址
     *
     * @param userAddress 买家地址
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * 获取买家微信openid
     *
     * @return user_openid - 买家微信openid
     */
    public String getUserOpenid() {
        return userOpenid;
    }

    /**
     * 设置买家微信openid
     *
     * @param userOpenid 买家微信openid
     */
    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid;
    }

    /**
     * 获取订单总额
     *
     * @return amount - 订单总额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置订单总额
     *
     * @param amount 订单总额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取订单状态，默认0：新下单
     *
     * @return order_status - 订单状态，默认0：新下单
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态，默认0：新下单
     *
     * @param orderStatus 订单状态，默认0：新下单
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取支付状态，默认0：未支付
     *
     * @return pay_status - 支付状态，默认0：未支付
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态，默认0：未支付
     *
     * @param payStatus 支付状态，默认0：未支付
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
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