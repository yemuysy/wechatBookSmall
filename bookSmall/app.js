App({
  serverUrl: "http://192.168.43.172:9090",
  //serverUrl: "http://192.168.123.116:9090",
  //serverUrl: "http://10.50.131.220:9090",
  imgUrl: "http://www.zxhysy.cn:88/zxh/",
  
  setGlobalUserInfo(user) {
    wx.setStorageSync("userInfo", user);
  },
  getGlobalUserInfo() {
    return wx.getStorageSync("userInfo");
  },
  globalData: {
    userInfo: null,
    hasLogin: true
  }
})

