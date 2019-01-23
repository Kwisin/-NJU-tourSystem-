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
        questions: [],
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
        var question_id = e.currentTarget.dataset.question_id;
        this.data.currentProblem = e.currentTarget.dataset.question;
        wx.request({
            url: app.globalData.serverIP + "/api/answer/list?problemId=" + question_id,
            success: res => {
                this.setData({
                    currentAnswerList: res.data.data
                })

                console.log(this.data.currentProblem)
                console.log(this.data.currentAnswerList)

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

        // while(app.globalData.isLogined){
        // }
        var value = options.key
        console.log("进入search的onLoad : " + value)
        this.data.height = app.globalData.height * 750 / app.globalData.width
        // var list = util.getData2()
        // this.data.questions = this.data.questions.concat(list)
        // this.setData({ questions: this.data.questions, height: this.data.height })
        wx.request({
            url: app.globalData.serverIP + '/api/problem/search?key='+value,
            success: res => {
                // console.log(res.data.data)
                if (res.data.status == 0) {
                    console.log(res.data.data)
                    this.setData({
                        questions: res.data.data,
                        // currentProblem: res.data.data[1],
                        height: this.data.height
                    })
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
        if(this.data.questions.length == 0){
            wx.showToast({
                title: 'No Results',
                duration: 2000,
            })
           
            
        }
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