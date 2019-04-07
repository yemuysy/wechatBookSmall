// pages/main/main.js
// const datas = require('../../static/data/data');
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    activeCategoryId: 1, //激活的类别id
    books: [], // 展示的图书列表
    categories: [], // 分类导航数据
    imgUrl: app.imgUrl, //图片路劲
    pageNum: 1, // 当前页
    pageSize: 8, // 显示数据个数
    pages: 1, // 所有页
    hasNextPage: true, //判断是否还有下一页
    searchInput: null, // 查询的内容
    isSearchName: false // 判断是否是查询
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const pageNum = this.data.pageNum
    const pageSize = this.data.pageSize
    const activeCategoryId = this.data.activeCategoryId
    //获取所有的分类
    this.getAllCategorie()
    //获取当前分类的书籍
    this.getAllCategorieForbookList(pageNum, pageSize, activeCategoryId)
  },
  /**跳转到详情页面 */
  toDetailsTap(e) {
    // 获取当前自定义属性数据
    const index = e.currentTarget.dataset.index
    let item = this.data.books[index]
    //这里需要将特殊符号进行转义，不然在转换 JSON格式的时候会报错
    // encodeURIComponent 转移字符串 主要是 书本描述
    item = encodeURIComponent(JSON.stringify(item))
    wx.navigateTo({
      url: "/pages/goods/goods?item=" + item
    })
  },

  /**点击分类获取分类物品 */
  tabClick(e) {
    // 初始化pageNum 和 activeCategoryId
    const pageNum = 1
    const activeCategoryId = e.currentTarget.dataset.id
    const pageSize = this.data.pageSize
    this.setData({
      activeCategoryId
    })
    this.getAllCategorieForbookList(pageNum, pageSize, activeCategoryId)
  },
  /** 获取全部类别 */
  getAllCategorie() {
    wx.request({
      url: app.serverUrl + "/category",
      method: "GET",
      header: {
        'content-type': 'application/json', // 默认值
      },
      fail: res => {
        wx.hideLoading({
          title: '与网络断开连接'
        })
      },
      success: e => {
        // e.data.data 分类对象数据
        this.setData({
          categories: e.data.data
        })
      }
    })
  },
  /** 获取同个类别的书籍 */
  getAllCategorieForbookList(pageNum, pageSize, categoryId) {
    wx.request({
      url: app.serverUrl + "/book/category",
      method: "GET",
      header: {
        'content-type': 'application/json', // 默认值
      },
      data: {
        pageNum,
        pageSize,
        categoryId
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
        // 判断是不是上拉刷新，是的话就将列表数据清空 也是说判断当前是否是第一页，第一页就刷新
        if (pageNum === 1) {
          this.setData({
            books: []
          })
        }
        // 分页对象数据   list 书籍列表 pages 总页码  hasNextPage 是否有下一页
        const { pages, hasNextPage, list } = e.data.data
        // 将查询出来的拼接上去
        const books = this.data.books.concat(list)
        this.setData({
          books,
          pageNum,
          pages,
          hasNextPage,
          isSearchName: false
        })
      }
    })
  },
  /**
   * 上拉刷新，当页面上拉触底部时出发
   */
  onReachBottom: function () {
    let currentPage = this.data.pageNum
    const pageSize = this.data.pageSize
    let pages = this.data.pages
    let isSearchName = this.data.isSearchName

    //判断当前页数和总页数是否相等，如果相等的就无需查询
    if (pages === currentPage) {
      return
    }
    // 判断是否书名查询
    if (!isSearchName) {
      const activeCategoryId = this.data.activeCategoryId
      currentPage++
      this.getAllCategorieForbookList(currentPage, pageSize, activeCategoryId)
    } else {
      const searchInput = this.data.searchInput
      currentPage++
      this.search(currentPage, pageSize, searchInput)
    }
  },
  // 下拉刷新 触碰顶部刷新页面 
  onPullDownRefresh: function () {
    const pageSize = this.data.pageSize
    const currentPage = 1
    const isSearchName = this.data.isSearchName
    // 当前页面显示导航条加载页面
    wx.showNavigationBarLoading()
    if (!isSearchName) {
      const activeCategoryId = this.data.activeCategoryId
      this.getAllCategorieForbookList(currentPage, pageSize, activeCategoryId)
    } else {
      const searchInput = this.data.searchInput
      this.search(currentPage, pageSize, searchInput)
    }
  },
  // 监听查询输入的内容
  listenerSearchInput(e) {
    const searchInput = e.detail.value
    this.setData({
      searchInput: searchInput
    })
  },
  // 跳转查询
  toSearch() {
    const searchInput = this.data.searchInput
    if (!searchInput) {
      wx.showToast({
        title: '查询内容不能为空',
        icon: 'none'
      })
      return
    }
    //每次查询清空数据
    this.setData({
      books: []
    })
    const pageNum = 1
    const pageSize = this.data.pageSize
    this.search(pageNum, pageSize, searchInput)
  },
  // 查询方法
  search(pageNum, pageSize, name) {
    wx.request({
      url: app.serverUrl + '/book/search',
      method: "GET",
      header: {
        'content-type': 'application/json', // 默认值
      },
      data: {
        pageNum,
        pageSize,
        name
      },
      success: e => {
        // 隐藏导航栏加载动画
        wx.hideNavigationBarLoading()
        // 停止下拉刷新的动画
        wx.stopPullDownRefresh()
        // 分行数据
        const { pages, list, hasNextPage} = e.data.data
        // 判断查询时是否是第一页，第一页就清空之前数据
        if (pageNum === 1) {
          this.setData({
            books: []
          })
        }
         // 将查询出来的拼接上去
        const books = this.data.books.concat(list)
        this.setData({
          books,
          pageNum,
          pages,
          hasNextPage,
          isSearchName: true
        })
      }
    })
  }
})