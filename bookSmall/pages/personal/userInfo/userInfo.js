// pages/personal/userInfo/userInfo.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    hidden: true,
    disabled: true
  },
  /**
    * 生命周期函数--监听页面加载
    */
  onLoad: function (options) {
    const userInfo = app.getGlobalUserInfo()
    console.log(JSON.stringify(userInfo))
    this.setData({
      userInfo
    })
  },
  // 点击按钮换手机相册或者电脑本地图片
  changeFace: function() {
    wx.chooseImage({
      count: 1, // 默认9 
      sizeType: ['compressed'], // original 原图 compressed 压缩图,可以指定是原图还是压缩图，默认二者都有 
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: res => {
        const filePath = res.tempFilePaths[0];
        wx.showLoading({
          title: '上传中...',
        })
        wx.uploadFile({
          url: app.serverUrl + "/fdfs/upload",
          filePath,
          header: {
            'content-type': 'application/json', // 默认值
            'userId': this.data.userInfo.id, // 用户id
            "userToken": this.data.userInfo.userToken // 用户 token
          },
          name: 'file',
          formData: {
            userId: this.data.userInfo.id
          },
          fail: res => {
            wx.hideLoading()
            wx.showToast({
              title: '网络断开连接...',
              icon: 'none'
            })
          },
          success: res => {
            // 上传文件，默认不会对返回值进行 json 处理
            const result = JSON.parse(res.data)
            wx.hideLoading()
            console.log(result.status)

            if (result.status === 200) {
              this.setData({
                ['userInfo.avatarUrl']: result.data
              })
              app.setGlobalUserInfo(this.data.userInfo)
              wx.showToast({
                title: '上传成功！~~~',
                icon: 'success'
              })
            } else if (result.status === 500) {
              wx.showToast({
                title: result.data.msg,
                icon: 'none'
              })
            }
          }
        })
      }
    })
  },
  // 点击按钮换手机相册或者电脑本地图片  版本1.9.9
  // changeFaceToBase64: function() {
  //   wx.chooseImage({
  //     count: 1, // 默认9 
  //     sizeType: ['compressed'], // original 原图 compressed 压缩图,可以指定是原图还是压缩图，默认二者都有 
  //     sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
  //     success: res => {
  //       wx.getFileSystemManager().readFile({
  //         filePath: res.tempFilePaths[0], //选择图片返回的相对路径
  //         encoding: 'base64', //编码格式
  //         success: res => { //成功的回调
  //           const base64 = res.data
  //           const id = this.data.userInfo.id
  //           console.log('data:image/png;base64,' + base64)
  //           console.log(id)
  //           wx.request({
  //             url: app.serverUrl + "/fdfs/uploadFaceBase64",
  //             method: "POST",
  //             header: {
  //               "content-type": "application/x-www-form-urlencoded;charset=utf-8",
  //               'userId': this.data.userInfo.id, // 用户id
  //               "userToken": this.data.userInfo.userToken // 用户 token
  //             },
  //             data: {
  //               base64,
  //               userId: id
  //             },
  //             success: res => {
  //               this.setData({
  //                 ['userInfo.avatarUrl']: result.data.data
  //               })
  //               app.sesetGlobalUserInfo(this.data.userInfo)
  //             }
  //           })
  //         }
  //       })

  //       //以下两行注释的是同步方法，不过我不太喜欢用。
  //       //let base64 = wx.getFileSystemManager().readFileSync(res.tempFilePaths[0], 'base64') 
  //       //console.log(base64)
  //     }
  //   })
  // },
  /**
   * 编辑
   */
  editUser() {
    this.setData({
      hidden: false,
      disabled: false
    })
  },
  /**
   * 保存
   */
  saveUser(e) {
    let user = e.detail.value
    const userInfo = this.data.userInfo
    if (user.nickName === userInfo.nickName && user.phone === userInfo.phone && user.address === userInfo.address) {
      wx.showToast({
        title: '您未作修改',
        icon: 'none'
      })
      this.setData({
        hidden: true,
        disabled: true
      })
      return
    }
    user['id'] = userInfo.id
    wx.request({
      url: app.serverUrl + "/user",
      method: 'PUT',
      header: {
        'content-type': 'application/json', // 默认值
        'userId': this.data.userInfo.id, // 用户id
        "userToken": this.data.userInfo.userToken // 用户 token
      },
      data: user,
      success: res => {
        if (res.data.status === 200) {
          userInfo.nickName = user.nickName
          userInfo.phone = user.phone
          userInfo.address = user.address
          this.setData({
            hidden: true,
            disabled: true,
            userInfo: userInfo
          })
          app.setGlobalUserInfo(userInfo)
          wx.showToast({
            title: '更新成功',
            icon: 'success'
          }) 
        } else if (result.status === 500) {
          wx.showToast({
            title: result.data.msg,
            icon: 'none'
          })
          app.setGlobalUserInfo(null)
        }
      }
    })
  }
})