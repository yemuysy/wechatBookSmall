package cn.zxhysy.booksmall.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_book")
public class Book {
    @Id
    private String id;

    /**
     * 书本名称
     */
    private String name;

    /**
     * 书本作者
     */
    private String author;

    /**
     * 书本单价
     */
    private BigDecimal price;

    /**
     * 图片、小图地址
     */
    private String icon;

    /**
     * 书本库存
     */
    private Integer stock;

    /**
     * 书本类别
     */
    @Column(name = "category_id")
    private Integer categoryId;

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
     * 书本描述
     */
    private String description;

    /**
     * 大图片地址(轮播图未实现）
     */
    private String pic;

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
     * 获取书本名称
     *
     * @return name - 书本名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置书本名称
     *
     * @param name 书本名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取书本作者
     *
     * @return author - 书本作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置书本作者
     *
     * @param author 书本作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取书本单价
     *
     * @return price - 书本单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置书本单价
     *
     * @param price 书本单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取图片、小图地址
     *
     * @return icon - 图片、小图地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图片、小图地址
     *
     * @param icon 图片、小图地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取书本库存
     *
     * @return stock - 书本库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置书本库存
     *
     * @param stock 书本库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取书本类别
     *
     * @return category_id - 书本类别
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置书本类别
     *
     * @param categoryId 书本类别
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    /**
     * 获取书本描述
     *
     * @return description - 书本描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置书本描述
     *
     * @param description 书本描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取大图片地址(轮播图未实现）
     *
     * @return pic - 大图片地址(轮播图未实现）
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置大图片地址(轮播图未实现）
     *
     * @param pic 大图片地址(轮播图未实现）
     */
    public void setPic(String pic) {
        this.pic = pic;
    }
}