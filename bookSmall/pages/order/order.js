// pages/myOrder/orderList.js
let app = getApp()
Page({
  data: {
    currtab: 1, // 当前支付状态 也是激活分类信息
    swipertab: [{
      name: '已完成',
      index: 1
    }, {
      name: '待付款',
      index: 0
    }, {
      name: '已取消',
      index: 2
    }],
    payStatus: 0, // 支付状态
    pageNum: 1, // 当前页
    pages: 1, // 总页数
    pageSize: 6,
    userInfo: null,
    orderVOList: null,
    imgUrl: null
  },
  onLoad: function (options) {
    const userInfo = app.getGlobalUserInfo()
    this.setData({
      userInfo: userInfo,
      imgUrl: app.imgUrl
    })
    this.showAllOrder(this.data.pageNum, this.data.pageSize, this.data.currtab, userInfo.openid)
  },
  showAllOrder(pageNum, pageSize, payStatus, openid) {
    wx.request({
      url: app.serverUrl + "/order/openid",
      method: "GET",
      header: {
        'content-type': 'application/json', // 默认值
        'userId': this.data.userInfo.id, // 用户id
        "userToken": this.data.userInfo.userToken // 用户 token
      },
      data: {
        pageNum: pageNum,
        pageSize: pageSize,
        payStatus: payStatus,
        openid: openid
      },
      fail: res => {
        // 隐藏导航栏加载动画
        wx.hideNavigationBarLoading()
        // 停止下拉刷新的动画
        wx.stopPullDownRefresh()
        wx.hideLoading({
          title: '与网络断开连接'
        })
      },
      success: e => {
        // 隐藏导航栏加载动画
        wx.hideNavigationBarLoading()
        // 停止下拉刷新的动画
        wx.stopPullDownRefresh()
        if (e.data.status === 200) {
          //判断是不是上拉刷新，是的话就将列表数据清空
          if (pageNum === 1) {
            this.setData({
              orderVOList: []
            })
          }
          const pageInfo = e.data.data

          const orderVOList = pageInfo.list
          //将图片都存储起来
          orderVOList.forEach(orderVO => {
            let imgs = []
            orderVO.orderDetailList.forEach(orderDetail => {
              imgs.push(orderDetail.bookIcon)
            })
            orderVO.orderMaster.imgs = imgs
          })
          console.log(orderVOList)
          // 将查询出来的拼接上去
          const list = this.data.orderVOList.concat(orderVOList)
          this.setData({
            pageNum: pageNum,
            pages: pageInfo.pages,
            orderVOList: list
          })
        } else if (e.data.status === 500 ){
          wx.showToast({
            title: e.data.msg,
            icon: "none"
          })
          app.setGlobalUserInfo(null)
        }
      }
    })
  },
  /**
   * @Explain：选项卡点击切换
   */
  tabSwitch(e) {
    var that = this
    if (this.data.currtab === e.target.dataset.current) {
      return false
    } else {
      // 切换类别
      const pageNum = 1
      const pageSize = this.data.pageSize
      const currtab = e.target.dataset.current
      const openid = this.data.userInfo.openid
      this.showAllOrder(pageNum, pageSize, currtab, openid)
      that.setData({
        currtab: e.target.dataset.current
      })
    }
  },
  toDetail(e) {
    let orderVO = this.data.orderVOList[e.currentTarget.dataset.index]
    //这里需要将特殊符号进行转义，不然在转换 JSON格式的时候会报错
    // encodeURIComponent 转移字符串 主要是 书本描述
    orderVO = encodeURIComponent(JSON.stringify(orderVO))
    wx.navigateTo({
      url: "/pages/orderDetail/orderDetail?orderVO=" + orderVO
    })
  },
  /**
   * 上拉刷新，当页面上拉触底部时出发
   */
  onReachBottom: function () {
    console.log('')
    let currentPage = this.data.pageNum
    const pageSize = this.data.pageSize
    let pages = this.data.pages
    //判断当前页数和总页数是否相等，如果相等的就无需查询
    if (pages === currentPage) {
      return
    }
    // 判断是否书名查询
    const currtab = this.data.currtab
    const openid = this.data.userInfo.openid
    currentPage++
    this.showAllOrder(currentPage, pageSize, currtab, openid)
  },
  // 下拉刷新 触碰顶部刷新页面 
  onPullDownRefresh: function () {
    const pageSize = this.data.pageSize
    const currentPage = 1
    // 当前页面显示导航条加载页面
    wx.showNavigationBarLoading()
    const currtab = this.data.currtab
    const openid = this.data.userInfo.openid
    this.showAllOrder(currentPage, pageSize, currtab, openid)
  }
})