// pages/pay/pay.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showAddress: false,
    itemList: null, // 订单列表
    amount: null, //总价格
    orderUser: {}, // 购买用户信息
    imgUrl: null, // 图片路劲
    userInfo: null
  },
  /** 获取用户收货地址 */
  getAddress() {
    wx.chooseAddress({
      success: res => {
        if(res){
          const orderUser = this.data.orderUser
          orderUser.userName = res.userName
          orderUser.userPhone = res.telNumber
          orderUser.userAddress = res.provinceName + ' ' + res.cityName + ' ' + res.countyName + ' ' + res.detailInfo
          console.log(orderUser)
          this.setData({
            showAddress: true,
            orderUser
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const {itemList, amount} = JSON.parse(options.order)
    const imgUrl = app.imgUrl

    const userInfo = app.getGlobalUserInfo()
    this.setData({
      itemList,
      amount,
      imgUrl,
      userInfo
    })
  },

  /** 生成订单消息 */
  createOrder(e) {
    const orderUser = this.data.orderUser
    if (JSON.stringify(orderUser) === '{}'){
      wx.showToast({
        title: '请先添加收货地址',
        icon: 'none'
      })
      return 
    }
    const userInfo = this.data.userInfo
    orderUser.userOpenid = userInfo.openid
    orderUser.amount = this.data.amount
    const pay = {}
    pay.orderMaster = orderUser
    pay.orderDetailList = this.data.itemList
    console.log(pay)

    wx.request({
      url: app.serverUrl + "/order",
      method: "POST",
      header: {
        'content-type': 'application/json', // 默认值
        'userId': userInfo.id, // 用户id
        "userToken": userInfo.userToken // 用户 token
      },
      data: pay,
      success: e => {
        if (e.data.status === 200) {
          // 清除购物车对应的东西
          const shopCarInfo = wx.getStorageSync('shopCarInfo')
          let shopList = shopCarInfo.shopList
          const itemList = this.data.itemList
          let shopNum = shopCarInfo.shopNum
          // 移除对应购物车数据
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