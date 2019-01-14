// pages/release_problem/release_problem.js
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        input_title:"",
        input_content:"",
        anonymous:0
    },

    /**
     * 问题标题输入监听
     * 
     */
    input_title:function(e){
        this.data.input_title = e.detail.value;
        this.setData({
          input_title:this.data.input_title
        });
    },
    /**
     * 问题内容输入监听
     * 
     */
    input_content: function (e) {
        this.data.input_content = e.detail.value;
        this.setData({
          input_content: this.data.input_content
        });

    },
    /**
     * 监听是否匿名
     */
    set_anonymous:function(e){
        if(e.detail.value){
            this.data.anonymous = 1
        }else{
            this.data.anonymous = 0
        }
    },

    /**
     * 监听是否取消
     */
    cancel:function(e){
        wx.navigateBack({
            
        })
    },
    /**
     * 提交问题监听
     */
    commit_question:function(e){
      const value = wx.getStorageSync("userInfo")
      if (this.data.input_title.length != 0 && this.data.input_content.length !=0){
          wx.request({
              url: app.globalData.serverIP + "/api/problem/create",
              method:"POST",
              data:{
                  title: this.data.input_title,
                  content: this.data.input_content,
                  userId: value.userId,
                  anonymous: this.data.anonymous
              },
              success: res => {
                  console.log("提问成功")
                  wx.navigateBack({
                      
                  })
              }
          })
      } else if (this.data.input_title.length == 0){
        wx.showToast({
          title: "标题不能为空",
        })
      } else {
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