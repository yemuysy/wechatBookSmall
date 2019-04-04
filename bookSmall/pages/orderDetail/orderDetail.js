// pages/pay/pay.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order: null, // 订单
    amount: null, //总价格
    orderMaster: {}, // 购买用户信息
    imgUrl: null, // 图片根路劲
    orderDetailList: null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.orderVO)
    const orderVO = JSON.parse(decodeURIComponent(options.orderVO))
    this.setData({
      orderMaster: orderVO.orderMaster,
      orderDetailList: orderVO.orderDetailList,
      imgUrl: app.imgUrl
    })
  }
})