//app.js
var WXBizDataCrypt = require("utils/cryptojs/RdWXBizDataCrypt.js")

App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    var info = wx.getSystemInfoSync()
    if (info) {
      this.globalData.width = info.windowWidth
      this.globalData.height = info.windowHeight
    }
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        
       
        
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo
              success: res => {
                console.log("request")
                
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    aid: "wxee1a29d2bd12370b",
    sid: "7d52c5f0d7f11ea3397c6d436af69cd7",
    uid: "odTDj5IHaQPRGUsDDTSGCPALu2bk",
    serverIP: "http://localhost:8080"
  }
})