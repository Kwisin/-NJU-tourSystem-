// pages/personal_center/personal_info/personal_info.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    userInfo: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
      var that = this;
      var userid = "1";
      wx.request({
        url: app.globalData.serverIP + "/user/userinfo/"+userid,
        method: 'GET',
        header: {
          "Content-Type": "application/json"
        },
        success: function (res) {
          console.log("请求成功")
          console.log(res.data.data)
          
          that.setData({
            user: res.data.data,
            userInfo: app.globalData.userInfo

          })
        }
      });
    },


  

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})