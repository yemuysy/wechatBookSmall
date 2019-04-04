package cn.zxhysy.booksmall.mapper;

import cn.zxhysy.booksmall.pojo.OrderDetail;
import cn.zxhysy.booksmall.utils.mapper.MyMapper;

import java.util.List;

/**
 * @className: OrderDetailMapper
 * @description:
 * @author: zxh
 * @date: 2019-3-29 10:01:01
 */
public interface OrderDetailMapper extends MyMapper<OrderDetail> {

    Integer inserDetailList(List<OrderDetail> list);

}