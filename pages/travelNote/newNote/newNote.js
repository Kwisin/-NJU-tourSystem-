// pages/travelNote/newNote/newNote.js

const app = getApp()

var Utils = require('../../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    input_title: "",
    input_content: "",
    createTime:"",
  },

  input_title: function (e) {
    this.data.input_title = e.detail.value;
    this.setData({
      input_title: this.data.input_title
    });
  },
  /**
   * 问题内容输入监听
   * 
   */
  input_content: function (e) {
    this.data.input_content = e.detail.value;
  },

  releaseNote:function(e){
    var userid = app.globalData.uid;
    var that = this;
    var TIME = Utils.formatDate(new Date());
    this.setData({
      createTime: TIME,
    });

    console.log(this.data)
    if (this.data.input_title.length != 0 && this.data.input_content.length != 0){
      wx.request({
        url: app.globalData.serverIP + "/tourRecord/publishArticle",
        method:"POST",
        data:{
          "content": that.data.input_content,
          "id": 0,
          "publishTime": that.data.createTime,
          "readNum": 0,
          "title": that.data.input_title,
          "uid": userid
        }
        ,
        header: {
          "Content-Type": "application/json"
        },

        success: res => {
              console.log("提交成功")
        }
      })



    } else if (this.data.input_title.length == 0) {
      wx.showToast({
        title: "标题不能为空",
      })
    } else if (this.data.input_content.length == 0) {
      wx.showToast({
        title: "内容不能为空",
      })
    }

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