// pages/message/detail_message/detail_message.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    isHide: false,
    message:null,
    user:null,
    usermyself:null,
    reply_value:null,
  },

  reply_value:function(e){

    this.setData({
      reply_value: e.detail.value
    })
  },

  sub_reply:function(e){

    this.setData({
      isHide: true
    });

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.sid = options.id
    var userid = app.globalData.uid
    var that = this
    wx.request({
      url: app.globalData.serverIP + "/comment/newCommentInfoList/" + userid,
      method: 'GET',
      header: {
        "Content-Type": "application/json"
      },
      success: function (res) {
        console.log("请求成功");
        console.log(res.data.data);
        var messagelist = res.data.data;
        var message = null;
        var user = null;
        for(var i=0;i<messagelist.length;i++){
          if (messagelist[i].user.id == options.id)
              message = messagelist[i].commentList
              user = messagelist[i].user
        }
        that.setData({
          message: message,
          user:user ,
          usermyself: app.globalData.userInfo
        })
        console.log(user)
        console.log(message)
      }
    });
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