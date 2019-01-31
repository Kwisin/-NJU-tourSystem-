// pages/release_problem/release_problem.js
const app = getApp()
var Utils = require('../../utils/util.js')
Page({
    /**
     * 页面的初始数据
     */
    data: {
        input_title:"",
        input_content:"",
        input_maxnum:0,
        input_payment:0,
        anonymous:0,
        createTime:"2018-09-01",
        date_deadline:"2018-09-01",
        date_begin:"2018-09-01",
        date_end:"2018-12-01",
        address:"上海"
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
    },
    input_maxnum: function (e) {      
      this.data.input_maxnum = e.detail.value;
      
    },

    input_payment: function (e) {      
      this.data.input_payment = e.detail.value;      
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
    commit_activity:function(e){
      const value = wx.getStorageSync("userInfo")   
      var TIME = Utils.formatDate(new Date());
      this.setData({
        createTime: TIME,
      });   
      if (this.data.input_title.length != 0 && this.data.input_content.length !=0 && this.data.date_end>this.data.date_begin){
          wx.request({
            url: app.globalData.serverIP + "/activity/addActivity",
              method:"POST",
              data:{
                address: this.data.address,
                createdTime: this.data.createTime,
                deadline: this.data.date_deadline,
                description: this.data.input_content,
                endTime: this.data.date_end,                
                maxNum: this.data.input_maxnum,
                name: this.data.input_title,
                organizerId: app.globalData.userId,
                payment: this.data.input_payment,
                startTime: this.data.date_begin,
                state: true            
              },
              success: res => {
                  console.log("提交成功")
                  wx.navigateBack({
                      
                  })
              }
          })
      } else if (this.data.input_title.length == 0){
        wx.showToast({
          title: "标题不能为空",
        })
      } else if (this.data.input_content.length == 0){
        wx.showToast({
          title: "内容不能为空",
        })
      } else {
        wx.showToast({
          title: "截止日期应在开始日期之后",
        })
      }
     },

  openmap:function(){
        wx.navigateTo({
          url: '../map/map',
        })
     },

  bindDateChange1(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date_begin: e.detail.value
    })
  },

  bindDateChange2(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date_end: e.detail.value
    })
  },

  bindDateChange3(e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date_deadline: e.detail.value
    })
  },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      const value = wx.getStorageSync("userInfo") 
      console.log(value)
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
      //console.log(this.data.address)
      var address = wx.getStorageSync('address')
      if (address.length != 0){
        this.setData({
          address: address
        })
      }
      console.log(this.data.address)
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