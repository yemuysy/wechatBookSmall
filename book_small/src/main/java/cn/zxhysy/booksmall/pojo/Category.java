package cn.zxhysy.booksmall.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @className: Category
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:03:35
 */
@Table(name = "tb_category")
public class Category {
    /**
     * 类目编号
     */
    @Id
    private Integer id;

    /**
     * 类目名字
     */
    private String name;

    /**
     * 类目图片地址
     */
    private String icon;

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
     * 获取类目编号
     *
     * @return id - 类目编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置类目编号
     *
     * @param id 类目编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类目名字
     *
     * @return name - 类目名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类目名字
     *
     * @param name 类目名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类目图片地址
     *
     * @return icon - 类目图片地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置类目图片地址
     *
     * @param icon 类目图片地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
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