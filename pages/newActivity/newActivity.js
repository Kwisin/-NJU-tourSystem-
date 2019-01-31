// pages/activity-acked/activity-acked.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activities: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url: app.globalData.serverIP + '/activity/myNewActivityList/' + app.globalData.userId,
      success: res => {
        if (res.data.data.length == 0) {
          wx.showToast({
            title: '没有相关活动',
            image: '../../img/error.png'
          })
        }
        else {
          this.setData({
            activities: res.data.data
          })
        }
      }
    })
  },

  viewDetails: function (e) {
    console.log(e.currentTarget.dataset)
    var id = e.currentTarget.dataset.id;
    this.data.currentActivity = e.currentTarget.dataset.activity;
    wx.navigateTo({
      url: '../../pages/activity_detail/activity_detail?id=' + id,
    })
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