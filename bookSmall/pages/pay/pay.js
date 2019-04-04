// pages/pay/pay.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order: null, // 订单
    amount: null, //总价格
    orderUser: {}, // 购买用户信息
    imgUrl: null, // 图片路劲
    userInfo: null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log(JSON.parse(options.order))
    const order = JSON.parse(options.order)
    const imgUrl = app.imgUrl
    console.log(options.order)

    const userInfo = app.getGlobalUserInfo()
    console.log(userInfo)
    const orderUser = {}
    if (userInfo) {
      orderUser.userName = userInfo.nickName
      orderUser.userPhone = userInfo.phone
      orderUser.userAddress = userInfo.address
      orderUser.userOpenid = userInfo.openid
    }
    this.setData({
      order: order,
      amount: order.amount,
      orderUser: orderUser,
      imgUrl: imgUrl,
      userInfo: userInfo
    })
  },

  /** 生成订单消息 */
  createOrder(e) {
    const orderUser = this.data.orderUser
    const order = this.data.order
    orderUser.amount = this.data.amount
    const pay = {}
    pay.orderMaster = orderUser
    pay.orderDetailList = order.itemList
    console.log(pay)

    wx.request({
      url: app.serverUrl + "/order",
      method: "POST",
      header: {
        'content-type': 'application/json', // 默认值
        'userId': this.data.userInfo.id, // 用户id
        "userToken": this.data.userInfo.userToken // 用户 token
      },
      data: pay,
      success: e => {
        if (e.data.status === 200) {
          // 清除购物车对应的东西
          const shopCarInfo = wx.getStorageSync('shopCarInfo')
          let shopList = shopCarInfo.shopList
          const itemList = order.itemList
          let shopNum = shopCarInfo.shopNum
          itemList.forEach(res => {
            shopList.splice(shopList.findIndex(e => {
              if (res.bookId === e.bookId) {
                shopNum = shopNum - res.bookQuantity
                return true
              }
            }), 1)
          })
          shopCarInfo.shopNum = shopNum
          wx.setStorageSync('shopCarInfo', shopCarInfo)
          wx.showToast({
            title: '支付成功',
            icon: 'success',
            duration: 2000,
            success: res => {
              setTimeout(() => {
                wx.navigateBack({
                  delta: 1
                });
              }, 2000)
            }
          })
        } else if (e.data.status === 500) {
          wx.showToast({
            title: result.data.msg,
            icon: 'none'
          })
          app.setGlobalUserInfo(null)
        }
      }
    })
  },
  /** input 的值 */
  inputChange(e) {
    const name = e.currentTarget.dataset.name
    const value = e.detail.value
    const orderUser = this.data.orderUser
    orderUser[name] = value
    this.setData({
      orderUser: orderUser
    })
  }
})