// pages/personal/personal.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    disabled: false,
    aboutShow: true,
    userInfo: null,
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    let userInfo = app.getGlobalUserInfo()
    if (userInfo) {
      this.setData({
        userInfo,
        disabled: true
      })
    } else {
      const userInfo = {
        nickName: '点击登录',
        avatarUrl: 'https://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png'
      }
      this.setData({
        disabled: false,
        userInfo
      })
    }
  },
  //登录方法
  doLogin(e) {
    var that = this
    //获取授权
    if (e.detail.userInfo) {
      wx.showLoading({
        title: '请等候'
      })
      wx.login({
        fail: res => {
          wx.hideLoading({
            title: '与网络断开连接'
          })
        },
        success: res => {
          //获取临时登录凭证
          // console.log("3" + app.serverUrl)
          var code = res.code;
          if (code) {
            //获取用户信息接口
            wx.getUserInfo({
              fail: res => {
                wx.hideLoading({
                  title: '与网络断开连接'
                })
              },
              success: e => {
                // 向服务器请求获取解密数据
                wx.request({
                  url: app.serverUrl + "/user/decodeUser",
                  method: "POST",
                  header: {
                    "content-type": "application/x-www-form-urlencoded;charset=utf-8"
                  },
                  data: {
                    encryptedData: e.encryptedData,
                    iv: e.iv,
                    code: code
                  },
                  fail: res => {
                    wx.hideLoading({
                      title: '与网络断开连接'
                    })
                  },
                  success: e => {
                    if (e.data.status === 200) {
                      wx.hideLoading()
                      this.setData({
                        userInfo: e.data.data,
                        disabled: true
                      })
                      app.setGlobalUserInfo(e.data.data)
                    } else if (e.data.status === 500) {
                      wx.hideLoading()
                      wx.showToast({
                        title: '登录失败',
                        icon: 'none',
                        duration: 1000
                      })
                    }
                  }
                })
              }
            })
          }
        }
      })
    } else {
      wx.showToast({
        title: '未授权',
        icon: 'none',
        duration: 1000
      })
    }
  },
  goInfo(e) {
    let userInfo = this.data.userInfo
    if (!userInfo) {
      wx.showToast({
        title: '你还未登录',
        icon: 'none'
      })
      return
    }
    wx.navigateTo({
      url: '/pages/userInfo/userInfo'
    })
  },
  goOrder() {
    let userInfo = this.data.userInfo
    if (!userInfo) {
      wx.showToast({
        title: '你还未登录',
        icon: 'none'
      })
      return
    }
    wx.navigateTo({
      url: '../order/order'
    })
  }
})