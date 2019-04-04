package cn.zxhysy.booksmall.pojo.vo;

import cn.zxhysy.booksmall.pojo.OrderDetail;
import cn.zxhysy.booksmall.pojo.OrderMaster;

import java.util.List;

/**
 * @program: book_small
 * @className: OrderVO
 * @description: 订单视图
 * @author: zxh
 * @date: 2019-04-01 23:38
 */
public class OrderVO {

    /**
     * 订单主表信息
     */
    private OrderMaster orderMaster;
    /**
     * 订单详情表信息
     */
    private List<OrderDetail> orderDetailList;

    public OrderMaster getOrderMaster() {
        return orderMaster;
    }

    public void setOrderMaster(OrderMaster orderMaster) {
        this.orderMaster = orderMaster;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "orderMaster=" + orderMaster +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
