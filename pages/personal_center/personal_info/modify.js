// pages/personal_center/personal_application/modify.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    modify_value:"",
    id:"",
    value:""
  },

  getValue:function(e){

    this.data.modify_value = e.detail.value
    this.setData({
      modify_value:this.data.modify_value
    })
  },

  submit_modify:function(e){

    wx.showToast({
      title: '修改成功',
      icon: 'success',
      duration: 3000
    });

    var userid = app.globalData.uid;


    wx.request({
      url: app.globalData.serverIP + "/user/updateUser",
      method: 'POST',
      data:{
        userid: userid,
        columnid:this.data.id,
        value: this.data.modify_value

      },
      header: {
        "Content-Type": "application/json"
      },
      success: function (res) {


        wx.navigateBack({

        })
      }
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.setData({
      id: options.id,
      value: options.value

    })
    console.log(this.data)
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