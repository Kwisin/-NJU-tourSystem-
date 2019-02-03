// pages/personal_center/personal_activity/check/check.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    aid: null,
    plist: [],

  },

  agree_sub: function (e) {
    var that = this
    var temp_id = e.target.dataset.id;
    wx.request({
      url: app.globalData.serverIP + "/activity/applicationAgreement/"+temp_id + ",已通过" ,
      method: 'GET',
      header: {
        "Content-Type": "application/json"
      },
     
      success: function (res) {
        console.log("审批成功");
        wx.showToast({
          title: '审批成功', success: res => {
            that.onShow()
          }})
        
      }

    })
  },
  refuse_sub: function (e) {
    var that = this
    var temp_id = e.target.dataset.id;
    wx.request({
      url: app.globalData.serverIP + "/activity/applicationAgreement/" + temp_id + ",未通过",
      method: 'GET',
      header: {
        "Content-Type": "application/json"
      },
   
      success: function (res) {
        console.log("审批成功");
        wx.showToast({
          title: '审批成功', success: res => {
            that.onShow()
          }
        })

      }

    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.aid = options.aid
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
    var that = this
    wx.request({
      url: app.globalData.serverIP + "/activity/getApplicationList/" + this.data.aid,
      method: 'GET',
      header: {
        "Content-Type": "application/json"
      },
      success: function (res) {
        console.log(res.data.data);
        that.setData({
          plist: res.data.data
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