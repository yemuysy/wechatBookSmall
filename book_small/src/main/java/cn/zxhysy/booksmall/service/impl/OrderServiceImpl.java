package cn.zxhysy.booksmall.service.impl;

import cn.zxhysy.booksmall.mapper.OrderDetailMapper;
import cn.zxhysy.booksmall.mapper.OrderMasterMapper;
import cn.zxhysy.booksmall.pojo.OrderDetail;
import cn.zxhysy.booksmall.pojo.OrderMaster;
import cn.zxhysy.booksmall.pojo.vo.OrderVO;
import cn.zxhysy.booksmall.service.OrderService;
import cn.zxhysy.booksmall.utils.IDUtil;
import cn.zxhysy.booksmall.utils.MyPageInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: book_small
 * @className: OrderServiceImpl
 * @description:
 * @author: zxh
 * @date: 2019-04-01 23:41
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMasterMapper orderMasterMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderServiceImpl(OrderMasterMapper orderMasterMapper, OrderDetailMapper orderDetailMapper) {
        this.orderMasterMapper = orderMasterMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderMaster orderMaster, List<OrderDetail> orderDetailList) {
        String itemId = IDUtil.getMasterId();
        orderMaster.setId(itemId);

        orderMaster.setOrderStatus(new Byte("0"));
        orderMaster.setPayStatus(new Byte("1"));
        for(OrderDetail orderDetail : orderDetailList){
            orderDetail.setOrderId(itemId);
            orderDetail.setId(IDUtil.getDetailId());
        }
        orderMasterMapper.insert(orderMaster);
        orderDetailMapper.inserDetailList(orderDetailList);

        /* 不知道为啥就是差不如 id 列， 打印的sql中少了id列 */
        /* 记录一个通用mapper的一个小坑，MySQLMapper的insertList方法中传入list时，这个Entity的主键必须为自增主键，否则他在执行sql是不会去插入主键，自然就会报一些奇怪的错误了，比如DB2的-407*/
//        orderDetailMapper.insertList(orderDetailList);
    }

    @Override
    public MyPageInfo<OrderVO> getAllOrder(Integer pageNum, Integer pageSize, Byte payStatus, String openid) {
        PageHelper.startPage(pageNum,pageSize);
        Example exampleMaster = new Example(OrderMaster.class);
        exampleMaster.setOrderByClause("create_time DESC");
        Example.Criteria criteria = exampleMaster.createCriteria();
        criteria.andEqualTo("payStatus", payStatus).andEqualTo("userOpenid",openid);
        List<OrderMaster> orderMasters = orderMasterMapper.selectByExample(exampleMaster);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasters);

        List<OrderVO> orderVOList = new ArrayList<>();
        for (OrderMaster orderMaster: orderMasters){
            Example exampleDetail = new Example(OrderDetail.class);

            Example.Criteria exampleDetailCriteria = exampleDetail.createCriteria();
            exampleDetailCriteria.andEqualTo("orderId", orderMaster.getId());
            List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(exampleDetail);
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderMaster(orderMaster);
            orderVO.setOrderDetailList(orderDetails);
            orderVOList.add(orderVO);
        }

        MyPageInfo<OrderVO> myPageInfo = new MyPageInfo<>();
        myPageInfo.setList(orderVOList);
        myPageInfo.setPageNum(pageInfo.getPageNum());
        myPageInfo.setPageSize(pageInfo.getPageSize());
        myPageInfo.setTotal(pageInfo.getTotal());
        myPageInfo.setPages(pageInfo.getPages());
        return myPageInfo;
    }
}
