// pages/personal_center/personal_application/personal_application.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
      myapplication : {},
      refuselist:{}
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
    var userid = app.globalData.uid
    wx.request({
      url: app.globalData.serverIP + "/activity/myapplicationList/" + userid,
      success: res => {
        if (res.data.status == "ok") {

          this.setData({
          
            myapplication: res.data.data
          })
        }
      }
    });
    wx.request({
      url: app.globalData.serverIP + "/activity/myRefusedList/" + userid,
      success: res => {
        if (res.data.status == "ok") {

          this.setData({

            refuselist: res.data.data
          })
        }
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