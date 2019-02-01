// pages/hot/hot.js
const app = getApp()
Page({
    /**
     * 页面的初始数据
     */
    data: {
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        activities: [],
        currentProblem: null,
        currentAnswerList: null,
        scrollHeight: ""
    },
    
    /**
     *loadMore scroll的上拉监听 
     * @param 
     */

    loadMore: function (e) {
        wx.showToast({
            title: 'No More',
            duration:1000,
        })
        // console.log("No More")
        // wx.request({
        //     url: app.globalData.serverIP + "/api/problem/list",
        //     success: res => {
        //         console.log(res.data.data)
        //         if (res.data.status == 0) {
        //             // this.data.questions = this.data.questions.concat(res.data.data)
        //             this.setData({
        //                 questions: res.data.data
        //                 // currentProblem: res.data.data[1],
        //             })
        //         }
        //     }
        // })

    },

    /*
    *转跳到问题和答案页
    */
    viewDetails: function (e) {
      console.log(e.currentTarget.dataset)
      var id = e.currentTarget.dataset.id;
      this.data.currentActivity = e.currentTarget.dataset.activity;
      wx.navigateTo({
        url: '../../pages/activity_detail/activity_detail?id=' + id,
      })
    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

        // while(app.globalData.isLogined){
        // }
        var value = options.key
        console.log("进入search的onLoad : " + value)
        this.data.height = app.globalData.height * 750 / app.globalData.width
        // var list = util.getData2()
        // this.data.questions = this.data.questions.concat(list)
        // this.setData({ questions: this.data.questions, height: this.data.height })
        wx.request({
          header: { 'content-type': 'application/x-www-form-urlencoded;charset=utf-8', },
          url: app.globalData.serverIP + '/activity/searchActivity/' +value,
            success: res => {
                 console.log(res.data)
                if (res.data.status == "ok") {
                  if (res.data.data.length == 0) {
                    wx.showToast({
                      title: 'No Results',
                      duration: 2000,
                    })
                  }
                  else {  
                    console.log(res.data.data)
                    this.setData({
                        activities: res.data.data,
                        // currentProblem: res.data.data[1],
                        height: this.data.height
                    })
                    console.log(this.data.activities.length)
                  }
                }
            }
        })

    },



    /**
     * 生命周期函数--监听页面初次渲染完成
     */

    onReady: function () {
        console.log("进入search的onReady")

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
      console.log("进入search的onShow")
      
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
        
        // wx.request({
        //     url: app.globalData.serverIP + "/api/problem/list",
        //     success: res => {
        //         console.log(res.data.data)
        //         if (res.data.status == 0) {
        //             this.data.questions.concat(res.data.data)
        //             this.setData({
        //                 questions: this.data.questions
        //                 // currentProblem: res.data.data[1],
        //             })
        //         }
        //     }
        // })
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})