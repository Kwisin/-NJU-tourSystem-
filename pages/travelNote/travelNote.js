// pages/travelNote/travelNote.js
const app = getApp()


Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputContent:"",
    travelNotes:null,
  },

  searchInput: function (e) {
    this.data.inputContent = e.detail.value;
  },

  search:function(e){
    console.log("search note" + this.data.inputContent);
    var userid = app.globalData.uid;
    if (this.data.inputContent.length != 0){
      wx.request({
        url: app.globalData.serverIP + '/tourRecord/getArticleByContent/',
        data: {
          content: this.data.inputContent
        },
        success: res => {
          if (res.data.status == "ok") {
            this.setData({
              travelNotes: res.data.data
            })
          }
        }
      })
    }
    else{
      wx.request({
        url: app.globalData.serverIP + '/tourRecord/getHotArticle',
        success: res => {
          if (res.data.status == "ok") {
            this.setData({
              travelNotes: res.data.data
            })
          }
        }
      })
    }
    console.log(this.travelNotes)

  },

  newNote: function (e) {
    wx.navigateTo({
      url: "newNote/newNote"
    })
  },

  detailsNote: function (e) {
    // var noteid = e.id;//获取id,写好了确定怎么取;
    var noteid = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: 'detailNote/detailNote?noteid=' + noteid,
    })


  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.request({
      url: app.globalData.serverIP + '/tourRecord/getHotArticle',
      success: res => {
        if (res.data.data.length == 0) {
          wx.showToast({
            title: '没有相关游记',
            image: '../../img/error.png'
          })
        }
        else {
          this.setData({
            travelNotes: res.data.data
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