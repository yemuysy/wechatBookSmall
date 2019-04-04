var app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    isHidden: true, //判断编辑跟完成是否隐藏
    amount: 0, //总价
    checkAll: true, // 下单中的全选和选不选
    shopList: [], //商品列表
    shopCarInfo: {}, //购物车信息
    buyNumMin: 1, //购买最小数量
    buyNumMax: 99, //购买最大数量
    editActive: [], // 编辑状态是否激活，激活就是要删除
    checkAllEdit: false, //编辑是否全选
    imgUrl: app.imgUrl
  },
  toPay: function () {
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
  toIndexPage: function () {
    wx.switchTab({
      url: "/pages/main/main"
    });
  },
  /** 切换状态 */
  toggleHidden: function (e) {
    this.setData({
      isHidden: !this.data.isHidden,
      editActive: [],
      checkAllEdit: false
    })
  },
  /** 下单的 全选 和全不选 */
  select: function (e) {
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
  selectItem: function (e) {
    const shopCarInfo = this.data.shopCarInfo
    const shopList = this.data.shopList //获取购物车列表
    const index = e.currentTarget.dataset.index; //获取当前点击事件的下标索引
    //取反
    console.log(shopList[index].active)
    shopList[index].active = !shopList[index].active;
    shopCarInfo.shopList = shopList
    this.setData({
      shopCarInfo: shopCarInfo,
      shopList: shopList
    })
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  },
  /** 计算总价格 */
  getAmount: function () {
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
  add: function (e) {
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
  reduce: function (e) {
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
  del: function (e) {
    let shopList = this.data.shopList // 获取购物车列表
    let shopCarInfo = this.data.shopCarInfo // 获取购物车信息
    let editActive = this.data.editActive // 获取编辑的激活状态
    const length = shopList.length
    for (let index = 0; index < length; index++) {
      // 理论依据：判断中 undefined 为 false
      if (editActive[index]) {
        shopCarInfo.shopNum = shopCarInfo.shopNum - shopList[index].bookQuantity
        shopList.splice(index, 1)
      }
    }
    shopCarInfo.shopList = shopList
    this.setData({
      shopCarInfo: shopCarInfo,
      shopList: shopList
    })
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  },
  /** 商品选择 */
  editSelect: function (e) {
    const index = e.currentTarget.dataset.index
    let editActive = this.data.editActive
    editActive[index] = !editActive[index];
    this.setData({
      editActive: editActive
    })
  },
  /** 编辑商品的全选和全不选 */
  editSelectAll: function (e) {
    const editActive = this.data.editActive
    const length = this.data.shopList.length
    let checkAllEdit = this.data.checkAllEdit
    checkAllEdit = !checkAllEdit
    for (let index = 0; index < length; index++) {
      editActive[index] = checkAllEdit
    }
    this.setData({
      editActive: editActive,
      checkAllEdit: checkAllEdit
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // 获取购物车数据
    const shopCarInfo = wx.getStorageSync('shopCarInfo');
    const userInfo = app.getGlobalUserInfo()
    if (shopCarInfo && shopCarInfo.shopList) {
      const shopList = shopCarInfo.shopList
      this.setData({
        shopList: shopList,
        shopCarInfo: shopCarInfo,
        userInfo: userInfo
      })
      this.getAmount()
    }
  }
})