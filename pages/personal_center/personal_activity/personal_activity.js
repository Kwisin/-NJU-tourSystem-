// pages/personal_center/personal_activity/personal_activity.js

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    myactivity:[]
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
    console.log(userid)
    wx.request({
      url: app.globalData.serverIP + "/activity/myOrganizedActivityList/" + userid,
      success: res => {
        if (res.data.status == "ok") {
          console.log(res.data.data)
          this.setData({
            myactivity: res.data.data
          })
        }
      }
    })
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