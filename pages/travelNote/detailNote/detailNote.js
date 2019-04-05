// pages/travelNote/detailNote/detailNote.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    noteid:null,
    detail:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options)
    this.noteid = options.noteid;
    var that = this;
    var searchid = this.noteid;
    console.log(searchid);
    wx.request({
      url: app.globalData.serverIP + "/tourRecord/getArticleInfo/" + searchid,
      method: 'get',
      success: function (res) {

        if (res.data.status == "ok") {
          that.setData({
            detail: res.data.data
          })
        }
      }

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