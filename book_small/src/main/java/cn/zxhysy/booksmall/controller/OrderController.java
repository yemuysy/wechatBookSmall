package cn.zxhysy.booksmall.controller;

import cn.zxhysy.booksmall.pojo.OrderDetail;
import cn.zxhysy.booksmall.pojo.OrderMaster;
import cn.zxhysy.booksmall.pojo.vo.OrderVO;
import cn.zxhysy.booksmall.utils.ApiJSONResult;
import cn.zxhysy.booksmall.utils.MyPageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: book_small
 * @className: OrderController
 * @description: 订单服务
 * @author: zxh
 * @date: 2019-04-02 00:21
 */
@Api(value = "订单接口", tags = "订单操作")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final BasicController basicController;

    @Autowired
    public OrderController(BasicController basicController){
        this.basicController = basicController;
    }

    @ApiOperation(value = "订单保存", notes = "用户下单")
    @PostMapping
    public ApiJSONResult saveOrder(@ApiParam(value="订单信息", required = true) @RequestBody OrderVO orderVO){
        OrderMaster orderMaster = orderVO.getOrderMaster();
        List<OrderDetail> orderDetailList = orderVO.getOrderDetailList();
        basicController.orderService.saveOrder(orderMaster,orderDetailList);
        return ApiJSONResult.ok();
    }

    @ApiOperation(value = "订单查询", notes = "用户查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "显示记录数", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "payStatus", value = "支付状态", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "openid", value = "openid", required = true, dataType = "String")
    })
    @GetMapping("/openid")
    public ApiJSONResult getAllOrder(Integer pageNum, Integer pageSize, String payStatus, String openid){
        MyPageInfo<OrderVO> allOrder = basicController.orderService.getAllOrder(pageNum, pageSize, new Byte(payStatus), openid);
        return ApiJSONResult.ok(allOrder);
    }

}
