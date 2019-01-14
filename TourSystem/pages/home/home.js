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
    currentProblem: null,
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
      // wx.request({
      //     url: app.globalData.serverIP +'/api/problem/search?key='+this.data.inputContent,
      //     success: res=>{
      //         console.log("请求")
      //         if(res.data.status == 0){
      //             console.log("成功")
      //             console.log(res.data)
      //             console.log(res.data.data)
      //             this.data.questions = res.data.data
      //             this.setData({questions:this.data.questions})

      //         }
      //     }
      // })
    }
  },
  /**
     * 提问按钮的监听事件
     * 跳到新的界面
     * 
     */
  ask: function (e) {
    wx.navigateTo({
      url: "../release_problem/release_problem"
    })
  },
  /*
   *转跳到问题和答案页
   */
  viewDetails: function (e) {
    var question_id = e.currentTarget.dataset.question_id;
    this.data.currentProblem = e.currentTarget.dataset.question;
    wx.request({
      url: app.globalData.serverIP + "/api/answer/list?problemId=" + question_id,
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
              url: '../problem_detail/problem_detail',
            })
          },
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
          console.log(this.activities)
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
            questions: res.data.data
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
    wx.request({
      url: app.globalData.serverIP + "/activity/activityList",
      success: res => {
        if (res.data.status=="ok") {
          this.setData({
            questions: res.data.data
          })
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