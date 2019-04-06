var app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    isEdit: false, //判断编辑是编辑
    amount: 0, //总价
    checkAll: null, // 下单中的全选和选不选
    shopList: [], //商品列表
    shopCarInfo: {}, //购物车信息
    buyNumMin: 1, //购买最小数量
    buyNumMax: 99, //购买最大数量
    delCheckAll: null, //编辑是否全选
    imgUrl: app.imgUrl
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    // 获取购物车数据
    const shopCarInfo = wx.getStorageSync('shopCarInfo')
    const userInfo = app.getGlobalUserInfo()
    if (shopCarInfo && shopCarInfo.shopList.length) {
      const shopList = shopCarInfo.shopList
      const checkAll = this.isCheckAll(shopList)
      const delCheckAll = this.isDelCheckAll(shopList)

      this.setData({
        shopList,
        shopCarInfo,
        userInfo,
        checkAll,
        delCheckAll
      })
      this.getAmount()
    } else {
      this.setData({
        shopList: [],
        shopCarInfo: {}
      })
    }
  },
  /**跳转到支付页面 */
  toPay() {
    if (!this.data.userInfo) {
      wx.showToast({
        title: '请先登录，再下订单',
        icon: 'none'
      })
      return
    }
    const shopList = this.data.shopList
    const order = {
      itemList: [],
      amount: this.data.amount
    }
    shopList.forEach(res => {
      if (res.active) {
        let item = []
        item.book
        order.itemList.push(res)
      }
    })
    if (order.itemList.length === 0) {
      wx.showToast({
        title: '请选择商品',
        icon: none
      })
    }
    wx.navigateTo({
      url: '../pay/pay?order=' + JSON.stringify(order)
    })
  },
  /**跳转到首页 */
  toIndexPage() {
    wx.switchTab({
      url: "/pages/main/main"
    });
  },
  /** 切换编辑页面跟下单页面 */
  toggleHidden() {
    this.setData({
      isEdit: !this.data.isEdit,
    })
  },
  /** 下单的 全选 和全不选 */
  select() {
    var checkAll = this.data.checkAll;
    checkAll = !checkAll
    var shopList = this.data.shopList
    for (var i = 0; i < shopList.length; i++) {
      shopList[i].active = checkAll
    }
    this.setData({
      shopList: shopList,
      checkAll: checkAll
    })
    this.getAmount()
  },
  //判断全选按钮为true还是false
  isCheckAll(shopList) {
    for (const index in shopList) {
      if (!shopList[index].active) {
        return false;
      }
    }
    return true;
  },
  //判断删除全选按钮
  isDelCheckAll(shopList) {
    for (const index in shopList) {
      if (!shopList[index].delActive) {
        return false;
      }
    }
    return true;
  },
  //商品选择状态 （下单的选择和删除的选择）
  selectItem(e) {
    const shopCarInfo = this.data.shopCarInfo
    const shopList = this.data.shopList //获取购物车列表
    const index = e.currentTarget.dataset.index //获取当前点击事件的下标索引
    const isEdit = this.data.isEdit // 判断是编辑还是完成  false是编辑页面（删除），true是完成（下单）
    if (isEdit) {
      // 是编辑 商品删除取反
      shopList[index].delActive = !shopList[index].delActive;
      const delCheckAll = this.isDelCheckAll(shopList)
      this.setData({
        delCheckAll
      })
    } else {
      // 是下单 商品购买选择取反
      shopList[index].active = !shopList[index].active;
      const checkAll = this.isCheckAll(shopList)
      this.setData({
        checkAll
      })
      this.getAmount()
    }
    // this.setData() 这个主要是用来告诉页面要重新渲染
    this.setData({
      shopList
    })
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  },
  /** 计算总价格 */
  getAmount() {
    let shopList = this.data.shopList; // 获取购物车列表
    let amount = 0;
    for (let i = 0; i < shopList.length; i++) { // 循环列表得到每个数据
      if (shopList[i].active) { // 判断选中才会计算价格
        amount += shopList[i].bookQuantity * shopList[i].bookPrice; // 所有价格加起来
      }
    }
    this.setData({ // 最后赋值到data中渲染到页面
      amount: amount.toFixed(2)
    })
  },

  /** 商品增加 */
  add(e) {
    const shopCarInfo = this.data.shopCarInfo // 获取购物车信息
    const shopList = this.data.shopList //获取购物车列表
    const index = e.currentTarget.dataset.index //获取当前点击事件的下标索引
    let bookQuantity = shopList[index].bookQuantity //获取购物车里面的value值
    bookQuantity = bookQuantity + 1
    shopList[index].bookQuantity = bookQuantity
    shopCarInfo.shopNum = shopCarInfo.shopNum + 1
    shopCarInfo.shopList = shopList
    this.setData({
      shopList: shopList,
      shopCarInfo: shopCarInfo
    });
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo) //存缓存
  },

  /** 商品减少 */
  reduce(e) {
    const shopCarInfo = this.data.shopCarInfo // 获取购物车信息
    const shopList = this.data.shopList //获取购物车列表
    const index = e.currentTarget.dataset.index //获取当前点击事件的下标索引
    let bookQuantity = shopList[index].bookQuantity // 获取当前数量
    if (bookQuantity == 1) {
      shopList[index].bookQuantity = 1
    } else {
      bookQuantity--
      shopList[index].bookQuantity = bookQuantity;
    }
    shopCarInfo.shopNum = shopCarInfo.shopNum - 1
    shopCarInfo.shopList = shopList
    this.setData({
      shopCarInfo: shopCarInfo,
      shopList: shopList
    });
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  },

  /** 删除商品信息 */
  del() {
    let shopList = JSON.parse(JSON.stringify(this.data.shopList)) // 获取购物车列表
    let shopCarInfo = this.data.shopCarInfo // 获取购物车信息
    const length = shopList.length
    console.log("前：", shopList)
    shopList = shopList.filter(res => {
      if (res.delActive) {
        shopCarInfo.shopNum -= res.bookQuantity
        return false
      } else {
        return true
      }
    })
    console.log("后：", shopList)
    shopCarInfo.shopList = shopList
    this.setData({
      shopCarInfo: shopCarInfo,
      shopList: shopList
    })
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  }
})