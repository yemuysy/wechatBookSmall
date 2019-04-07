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
  onShow: function () {
    const shopCarInfo = wx.getStorageSync('shopCarInfo') // 获取购物车数据
    const userInfo = app.getGlobalUserInfo() // 获取用户数据
    if (shopCarInfo && shopCarInfo.shopList.length) {
      const shopList = shopCarInfo.shopList
      const isEdit = true
      //isCheckAll： 0(false)代表下单全部选择的状态 1(true)代表删除全部选中的状态
      const checkAll = this.isCheckAll(shopList, !isEdit)
      const delCheckAll = this.isCheckAll(shopList, isEdit)
      this.setData({
        shopList,
        shopCarInfo,
        userInfo,
        checkAll,
        delCheckAll,
        isEdit: !isEdit //让每次打开购物车都是下单页面 isEdit=false
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
    // 页面跳转传递的数据
    const order = {
      itemList: [],
      amount: this.data.amount
    }
    // 遍历商品信息，选中的加入order集合
    shopList.forEach(res => {
      if (res.active) {
        order.itemList.push(res)
      }
    })
    // 判断是否选中了商品，虽然是做了禁用，但还是最好判断一下
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
  /** 全选 和全不选 */
  selectAll() {
    const isEdit = this.data.isEdit
    const act = isEdit ? 'delActive' : 'active' // 判断要哪个激活状态
    const all = isEdit ? 'delCheckAll' : 'checkAll' // 判断要哪个全选状态
    const check = !this.data[all] // 获取当前全选状态 并取反
    const shopList = this.data.shopList
    const shopCarInfo = this.data.shopCarInfo
    // 遍历购物车激活状态信息并赋值全选状态
    for (let i = 0; i < shopList.length; i++) {
      shopList[i][act] = check
    }
    shopCarInfo.shopList = shopList
    this.setData({
      shopList,
      shopCarInfo,
      [all]: check
    })
    // 判断需不需要重新计算价格
    if (!isEdit) {
      this.getAmount()
    }
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  },
  //判断全选按钮为true还是false   check 0(false)=>active, 1(true)=>delActive
  // 接收 check 而不直接使用 this.data.isEdit 是因为如果同时需要判断两个状态时，就会有问题
  isCheckAll(shopList, check) {
    const act = check ? 'delActive' : 'active'
    for (const index in shopList) {
      // 判断是否有属性是false,是的话就直接返回全选false
      if (!shopList[index][act]) {
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
    const isEdit = this.data.isEdit // 判断是编辑还是完成  true是编辑页面（删除)false是完成（下单）
    const act = isEdit ? 'delActive' : 'active'
    const all = isEdit ? 'delCheckAll' : 'checkAll'
    // 商品对应状态取反
    shopList[index][act] = !shopList[index][act]
    const check = this.isCheckAll(shopList, isEdit)
    shopCarInfo.shopList = shopList
    this.setData({
      shopCarInfo,
      shopList,
      [all]: check
    })
    //判断需不需要重新计算价格
    if (!isEdit) {
      this.getAmount()
    }
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
    // 购买数量和购物车总数量加1
    shopList[index].bookQuantity++
    shopCarInfo.shopNum++
    shopCarInfo.shopList = shopList
    this.setData({
      shopList,
      shopCarInfo
    })
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo) //存缓存
  },

  /** 商品减少 */
  reduce(e) {
    const shopCarInfo = this.data.shopCarInfo // 获取购物车信息
    const shopList = this.data.shopList //获取购物车列表
    const index = e.currentTarget.dataset.index //获取当前点击事件的下标索引
    // 当前数量-1
    if (shopList[index].bookQuantity !== 1) {
      shopList[index].bookQuantity--
      shopCarInfo.shopNum--
    }
    shopCarInfo.shopList = shopList
    this.setData({
      shopCarInfo,
      shopList
    })
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  },

  /** 删除商品信息 */
  del() {
    let shopList = this.data.shopList // 获取购物车列表
    let shopCarInfo = this.data.shopCarInfo // 获取购物车信息
    shopList = shopList.filter(res => {
      if (res.delActive) {
        shopCarInfo.shopNum -= res.bookQuantity
        return false
      } else {
        return true
      }
    })
    shopCarInfo.shopList = shopList
    this.setData({
      shopCarInfo,
      shopList
    })
    this.getAmount()
    wx.setStorageSync("shopCarInfo", shopCarInfo)
  }
})