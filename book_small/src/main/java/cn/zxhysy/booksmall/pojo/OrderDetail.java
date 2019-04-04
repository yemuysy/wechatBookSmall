package cn.zxhysy.booksmall.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @className: OrderDetail
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:03:44
 */
@Table(name = "tb_order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private String id;

    /**
     * 主表id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 书本id
     */
    @Column(name = "book_id")
    private String bookId;

    /**
     * 商品名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 商品价格
     */
    @Column(name = "book_price")
    private BigDecimal bookPrice;

    /**
     * 商品数量
     */
    @Column(name = "book_quantity")
    private Integer bookQuantity;

    /**
     * 商品小图
     */
    @Column(name = "book_icon")
    private String bookIcon;

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
     * 获取主表id
     *
     * @return order_id - 主表id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置主表id
     *
     * @param orderId 主表id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取书本id
     *
     * @return book_id - 书本id
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置书本id
     *
     * @param bookId 书本id
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取商品名称
     *
     * @return book_name - 商品名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置商品名称
     *
     * @param bookName 商品名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取商品价格
     *
     * @return book_price - 商品价格
     */
    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    /**
     * 设置商品价格
     *
     * @param bookPrice 商品价格
     */
    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * 获取商品数量
     *
     * @return book_quantity - 商品数量
     */
    public Integer getBookQuantity() {
        return bookQuantity;
    }

    /**
     * 设置商品数量
     *
     * @param bookQuantity 商品数量
     */
    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    /**
     * 获取商品小图
     *
     * @return book_icon - 商品小图
     */
    public String getBookIcon() {
        return bookIcon;
    }

    /**
     * 设置商品小图
     *
     * @param bookIcon 商品小图
     */
    public void setBookIcon(String bookIcon) {
        this.bookIcon = bookIcon;
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