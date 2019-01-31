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
        console.log("request")
        wx.request({
          
          url: this.globalData.serverIP + "/login/decodeUserInfo",
          method: 'POST',
          data: {
              code: res.code,
              appid: this.globalData.aid,
              appSecret: this.globalData.sid
              
          },
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          success:resdata =>{
            this.globalData.uid = resdata.data.data.o_id
            // var pc = new WXBizDataCrypt(this.globalData.aid, resdata.data.data.s_key)
            console.log(this.globalData)

            // wx.getSetting({
            //   success: res => {
            //     if (res.authSetting['scope.userInfo']) {
            //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
            //       wx.getUserInfo({
            //         success: res => {
            //           // 可以将 res 发送给后台解码出 unionId
            //           this.globalData.userInfo = res.userInfo
            //           console.log("success")
            //           //拿到getUserInfo（）取得的res.encryptedData, res.iv，调用decryptData（）解密
            //           var data = pc.decryptData(res.encryptedData, res.iv)
            //           console.log(data)
            //           // data.unionId就是咱们要的东西了
            //           this.globalData.uid = data.openId
            //           console.log('解密后 unionid: ', this.globalData.unionid)


            //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
            //           // 所以此处加入 callback 以防止这种情况
            //           if (this.userInfoReadyCallback) {
            //             this.userInfoReadyCallback(res)
            //           }
            //         },
            //         fail: function (res) {
            //           console.log(res)
            //         }
            //       })
            //     }
            //   }
            // })



          
           
          }
        })
        
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

              
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
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
    uid: null,
    serverIP: "http://localhost:8080"
  }
})