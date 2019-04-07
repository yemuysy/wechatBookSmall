// pages/main/good/good.js
var app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    book: {}, //商品详情信息
    shopNum: 0, //购物车的总商品数量
    hideShopPopup: true, //是否显示购买模态框
    buyNumber: 0, //加入购物车的数量
    buyNumMin: 0, //加入购物车的最小数量
    buyNumMax: 100, //加入购物车的最大数量（库存量
    shopCarInfo: {}, //购物车信息
    pic: '', // 轮播图,
    width: 0, // 轮播图宽度,
    imgUrl: null //图片地址
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // 商品传递过来的数据
    // 对对象的特殊数据进行反转义
    let item = decodeURIComponent(options.item)
    const book = JSON.parse(item)
    const width = wx.getSystemInfoSync().screenWidth; //获取屏幕宽度
    this.setData({
      book,
      width,
      pic: book.pic.split(','),
      imgUrl: app.imgUrl
    })
    //获取本地购物车
    wx.getStorage({
      key: 'shopCarInfo',
      success: res => {
        this.setData({
          shopCarInfo: res.data,
          shopNum: res.data.shopNum
        })
      }
    })
  },
  /** 跳转到购物车*/
  goShopCar() {
    wx.reLaunch({
      url: "/pages/cart/cart"
    });
  },
  /** 规格选择弹出框*/
  toAddShopCar() {
    //规格选择弹出框
    this.bindGuiGeTap()
  },
  /**规格选择弹出框*/
  bindGuiGeTap() {
    this.setData({
      hideShopPopup: false
    })
  },
  /**规格选择弹出框隐藏*/
  closePopupTap() {
    this.setData({
      hideShopPopup: true
    })
  },
  /** 减少购买数量*/
  reduce() {
    //判断
    let buyNumber = this.data.buyNumber
    if (buyNumber > this.data.buyNumMin) {
      buyNumber--
      this.setData({
        buyNumber
      })
    }
  },
  /** 增加购买数量 */
  add() {
    let buyNumber = this.data.buyNumber
    if (buyNumber < this.data.buyNumMax) {
      buyNumber++
      this.setData({
        buyNumber
      })
    }
  },
  /** 加入购物车 */
  addShopCar() {
    if (this.data.buyNumber < 1) {
      wx.showModal({
        title: '提示',
        content: '购买数量不能为0！',
        showCancel: false
      })
      return
    }
    //组建购物车
    var shopCarInfo = this.bulidShopCarInfo();
    this.setData({
      shopCarInfo: shopCarInfo,
      shopNum: shopCarInfo.shopNum
    })
    // 写入本地存储
    wx.setStorage({
      key: 'shopCarInfo',
      data: shopCarInfo
    })
    // 关闭规格框
    this.closePopupTap()
    wx.showToast({
      title: '加入书单成功',
      icon: 'success',
      duration: 2000
    })
  },

  /** 组建购物车信息*/
  bulidShopCarInfo() {
    // 商品加入购物车时准备的商品信息，单个
    var shopCarMap = {}
    shopCarMap.bookId = this.data.book.id
    shopCarMap.bookIcon = this.data.book.icon
    shopCarMap.bookName = this.data.book.name
    shopCarMap.bookPrice = this.data.book.price
    shopCarMap.active = true // 判断商品是否激活状态
    shopCarMap.bookQuantity = this.data.buyNumber
    shopCarMap.delActive = false // 判断商品删除时是否激活

    // 获取原的购物车信息
    var shopCarInfo = this.data.shopCarInfo
    // 判读原购物车信息是否有初始化信息，如果不存在，就初始化数据，设置购买数量为 0
    if (!shopCarInfo.shopNum) {
      shopCarInfo.shopNum = 0;
    }
    // 判读原购物车信息是否有初始化信息，如果不存在，就初始化数据，设置购买数量为 0
    if (!shopCarInfo.shopList) {
      shopCarInfo.shopList = [];
    }
    // 判断是否有同样的物品
    var hasSameBookIndex = -1
    for (var i = 0; i < shopCarInfo.shopList.length; i++) {
      var tmpShopCarMap = shopCarInfo.shopList[i];
      // 如果在遍历商品列表中，如果id一直，就将购买数量加入
      if (tmpShopCarMap.bookId == shopCarMap.bookId) {
        hasSameBookIndex = i
        // 新的商品购物车信息
        shopCarMap.bookQuantity += tmpShopCarMap.bookQuantity
        break
      }
    }
    // 购物车购买的总数量，等于原数量加上现在购买的数量  这里不能用 shopCarMap.bookQuantity 的数量
    shopCarInfo.shopNum += this.data.buyNumber
    if (hasSameBookIndex > -1) {
      //如果商品存在，则找到对应商品的位置，删除他，再替换成 新的信息商品
      shopCarInfo.shopList.splice(hasSameBookIndex, 1, shopCarMap);
    } else {
      //不过商品不存在，则 将商品信息直接加入到列表中
      shopCarInfo.shopList.push(shopCarMap);
    }
    //返回购物车信息
    return shopCarInfo;
  }
})