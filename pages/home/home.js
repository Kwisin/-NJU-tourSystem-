 // pages/home/home.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    inputContent: "",
    activities: [],
    currentActivity: null,
    currentAnswerList: null,
    scrollHeight: ""

  },
  /** 
     *  搜索
     */

  searchInput: function (e) {
    this.data.inputContent = e.detail.value;
  },
  search: function (e) {
    console.log("you are searching for : " + this.data.inputContent)
    if (this.data.inputContent.length != 0) {
      wx.navigateTo({
        url: '../search_results/search_results?key=' + this.data.inputContent,
      })      
    }
  },
  /**
     * 提问按钮的监听事件
     * 跳到新的界面
     * 
     */
  ask: function (e) {
    wx.navigateTo({
      url: "../release_activity/release_activity"
    })
  },
  /*
   *转跳到问题和答案页
   */
  viewDetails: function (e) {
    console.log(e.currentTarget.dataset)
    var id = e.currentTarget.dataset.id;
    this.data.currentActivity = e.currentTarget.dataset.activity;
    wx.navigateTo({
      url: '../../pages/activity_detail/activity_detail?id='+id,
    })
    /*wx.request({
      url: app.globalData.serverIP + "/activity/activityInfo?problemId=" + id,
      success: res => {
        this.setData({
          currentAnswerList: res.data.data
        })

        // 将要打开的problem_detail存入本地缓存中
        wx.setStorage({
          key: "currentProblemDetail",
          data: {
            problem: this.data.currentProblem,
            answerList: this.data.currentAnswerList
          },
          success: res => {
            wx.navigateTo({
              url: '../activity_detail/activity_detail',
            })
          },
        })
      }
    })*/
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    wx.request({

      url: app.globalData.serverIP + "/login/addUserInfo",
      method: 'POST',
      data: {
        uid: app.globalData.uid,
        nickname: app.globalData.userInfo.nickName,
        url: app.globalData.userInfo.avatarUrl
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      success: resdata => {
        console.log("success")
      }
    });






    console.log("进入hot的onLoad")
   this.data.height = app.globalData.height * 750 / app.globalData.width - 80
    wx.request({
      url: app.globalData.serverIP + "/activity/activityList",
      success: res => {
        if (res.data.status == "ok") {
          this.setData({
            activities: res.data.data,
            height: this.data.height
          })
          //console.log(this.activities)
        }
        //console.log(this.questions)
      }
    })
  },
  /**
    *loadMore scroll的上拉监听 
    * @param 
    */

  loadMore: function (e) {
    wx.request({
      url: app.globalData.serverIP + "/activity/activityList",
      success: res => {
        if (res.data.status == "ok") {
          this.setData({
            activities: res.data.data
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
    console.log(app.globalData.userInfo)
    wx.request({
      url: app.globalData.serverIP + "/activity/activityList",
      success: res => {
        if (res.data.status == "ok") {
          this.setData({
            activities: res.data.data,
            height: this.data.height
          })
          //console.log(this.activities)
        }
      }
    })
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