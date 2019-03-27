// pages/activity_detail/activity_detail.js
// 获取App实例
var app = getApp();
var Utils = require("../../utils/util.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    activity: null,
    answerList: null,
    currentDate: null,
    deadline: null,
    startTime: null,
    endTime: null,
    maxNum: null,
    currentNum: null,
    payment: null,
    currentSwiperItemIndex: 0,
    // 当前回答ID，在页面加载时应该初始化为anwserList中首个answer的id，并且在每次切换answer时，赋值为切换的answer的id
    currentAnswer: null,
    // 当前用户是否和提问者是同一人，不是同一人的话就可以回答问题，关闭问题，关注问题，取关问题
    currentUserSameWithProblemOwner: null,
    hideFollowProblem:null,
    hideStarAnswer:null,
    // 当前用户是否和回答者是同一人，不是同一人的话就可以评论问题，关注用户，关注回答
    currentUserSameWithAnswerOwner: null,

    // 是否隐藏删除问题弹窗
    hideDeleteProblemModal: true,

    // 是否隐藏关闭问题弹窗
    hideCloseProblemModal: true,
    hideCloseProblem: null,

    // 是否隐藏释放问题弹窗
    hideResolveProblemModal: true,

    swiperBoxHeight: 0,
  },

  // 关注问题
  followProblem: function () {
    console.log("关注问题");
    console.log(app.globalData.userId);
    console.log(this.data.activity.id)
    wx.request({
      url: app.globalData.serverIP + "/activity/joinActivity/" + app.globalData.uid + "," + this.data.activity.id,
      success: res => {
        if (res.data.status == "ok") {
          //util.insert(this.data.userInfo.followProblem, this.data.problem.problemId);
          //wx.setStorageSync("userInfo", this.data.userInfo);
          wx.showToast({
            title: "参加活动成功",
          });
          /*
          this.setData({
            hideFollowProblem: true
          });*/
        } else {
          wx.showToast({
            title: "参加活动失败",
          });
        }
      }
    })
  },

  onLoad: function (options) {
    var TIME = Utils.formatDate(new Date());
    this.setData({
      currentDate: TIME,
    });
    var id = options.id;
    wx.request({
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8',
      },
      url: app.globalData.serverIP + '/activity/activityInfo/' + id,
      success: res => {
        this.setData({
          activity: res.data.data,
          deadline: res.data.data.deadline,
          startTime: res.data.data.startTime,
          endTime: res.data.data.endTime,
          maxNum: res.data.data.maxNum,
          payment: res.data.data.payment
        })        
      },
    })
    wx.request({
      url: app.globalData.serverIP + '/activity/getMemberList/' + id,
      success: res => {
        this.setData(
          {
              currentNum: res.data.data.length
          },
          console.log(res.data.data.length)
        )
      }
    })
    
  },
  /**
   * 事件监听
   
  upper(e) {
    // console.log(e)
  },
  lower (e) {
    // console.log(e)
  },
  // 刷新问题列表
  refreshAnswerList(e) {
    // console.log("refreshAnswerList");
    var swiperItemIndexBeforeDelete = this.data.currentSwiperItemIndex; 
    var answerNumBeforeDelete = this.data.answerList.length;
    console.log(swiperItemIndexBeforeDelete)
    console.log(answerNumBeforeDelete)
    // 向服务器请求当前问题的回答列表，更新页面
    wx.request({
      url: app.globalData.serverIP + "/api/answer/list?problemId=" + this.data.problem.problemId,
      success: res => {
        this.setData({
          answerList: res.data.data
        })

        // 如果删除的是回答列表中的最后一个元素，则将swiper当前item设置为它的前一个
        if (swiperItemIndexBeforeDelete == answerNumBeforeDelete - 1) {
          this.setData({
            currentSwiperItemIndex: swiperItemIndexBeforeDelete - 1
          })
        }
      }
    })

    console.log(this.data.currentSwiperItemIndex)
  },
  // 刷新评论列表
  refreshCommentList(e) {
    wx.request({
      url: app.globalData.serverIP + "/api/answer/list?problemId=" + this.data.problem.problemId,
      success: res => {
        this.setData({
          answerList: res.data.data
        })
      }
    })
  },
  
  releaseAnswer(e) {
    console.log("写回答");
    wx.navigateTo({
      url: "/pages/release_answer/release_answer?userId=" + this.data.userInfo.userId + "&problemId=" + this.data.problem.problemId,
    })
  },
  getAllAnswers(e) {
    // console.log("查看所有回答");
  },
  releaseComment(e) {
    console.log("添加评论")
    wx.navigateTo({
      url: "/pages/release_comment/release_comment?userId=" + this.data.userInfo.userId + "&problemId=" + this.data.problem.problemId + "&answerId=" + this.data.currentAnswer.answerId,
    })
  },
  // 点赞回答
  starAnswer(e) {
    console.log("点赞回答");
    wx.request({
      url: app.globalData.serverIP + "/api/answer/star",
      method: "POST",
      data: {
        userId: this.data.userInfo.userId,
        answerId: this.data.currentAnswer.answerId
      },
      success: res => {
        if (res.data.status == 0) {
          util.insert(this.data.currentAnswer.staredBy, this.data.userInfo.userId);
          wx.setStorageSync("userInfo", this.data.userInfo);
          wx.showToast({
            title: "点赞成功",
          });
          this.setData({
            hideStarAnswer: true
          });
        } else {
          wx.showToast({
            title: "点赞失败",
          });
        }
      }
    })
  },
  // 取消点赞回答
  unstarAnswer(e) {
    console.log("取消点赞回答——没写");
    wx.request({
      url: app.globalData.serverIP + "/api/answer/unstar",
      method: "POST",
      data: {
        userId: this.data.userInfo.userId,
        answerId: this.data.currentAnswer.answerId,
      },
      success: res => {
        if (res.data.status == 0) {
          util.remove(this.data.currentAnswer.staredBy, this.data.userInfo.userId);
          wx.setStorageSync("userInfo", this.data.userInfo);
          wx.showToast({
            title: "取消点赞成功",
          });
          this.setData({
            hideStarAnswer: false
          });
        } else {
          wx.showToast({
            title: "取消点赞失败",
          });
        }
      }
    })
  },
  
  switchAnswer(e) {
    console.log("切换回答");
    var index = e.detail.current;
    this.setData({
      currentAnswer: this.data.answerList[index].answer,
      currentUserSameWithProblemOwner: this.data.userInfo.userId == this.data.problem.user.userId,
      currentUserSameWithAnswerOwner: this.data.userInfo.userId == this.data.answerList[index].answer.user.userId,
    });

    this.setData({
      currentSwiperItemIndex: index,
      hideStarAnswer: util.contains(this.data.currentAnswer.staredBy, this.data.userInfo.userId),
    })
  },
  */
  
  /**
  // 取消关注问题
  unFollowProblem: function () {
    console.log("取消关注问题");
    wx.request({
      url: app.globalData.serverIP + "/api/problem/unfollow",
      method: "POST",
      data: {
        problemId: this.data.problem.problemId,
        userId: this.data.userInfo.userId
      },
      success: res => {
        if (res.data.status == 0) {
          util.remove(this.data.userInfo.followProblem, this.data.problem.problemId);
          wx.setStorageSync("userInfo", this.data.userInfo);
          wx.showToast({
            title: "取消关注成功",
          });
          this.setData({
            hideFollowProblem: false
          });
        } else {
          wx.showToast({
            title: "取消关注失败",
          });
        }
      }
    })
  },

  // 删除问题
  deleteProblem(e) {
    this.setData({
      hideDeleteProblemModal: false,
    });
  },
  confirmDeleteProblem(e) {
    this.setData({
      hideDeleteProblemModal: true
    });

    // 向服务器发送删除问题的请求
    wx.request({
      url: app.globalData.serverIP + "/api/problem/delete",
      method: "POST",
      data: {
        problemId: this.data.problem.problemId,
        userId: this.data.userInfo.userId
      },
      success: res => {
        // console.log(res)
        if (res.data.status == 0) {
            // 跳转到上一个页面
            wx.navigateBack({
              delta: 1,
              success: res => {
                console.log(res)
              }
            });
        } else {
          wx.showToast({
            title: "删除失败",
          })
        }
      }
    })
  },
  cancelDeleteProblem(e) {
    this.setData({
      hideDeleteProblemModal: true
    });
  },

  // 关闭问题
  closeProblem(e) {
    this.setData({
      hideCloseProblemModal: false
    });
  },
  confirmCloseProblem(e) {
    // 向服务器发送关闭问题的请求
    wx.request({
      url: app.globalData.serverIP + "/api/problem/close",
      method: "POST",
      data: {
        problemId: this.data.problem.problemId,
        userId: this.data.userInfo.userId
      },
      success: res => {
        // console.log(res)
        if (res.data.status == 0) {
          this.setData({
            hideCloseProblemModal: true,
            hideCloseProblem: true,
          });
          this.data.problem.status = 0;
        }
      }
      
    })
  },
  cancelCloseProblem(e) {
    this.setData({
      hideCloseProblemModal: true
    });
  },
  // 解除关闭问题
  resolveProblem(e) {
    this.setData({
      hideResloveProblemModal: false
    });
  },
  confirmResolveProblem(e) {
    this.setData({
      hideResloveProblemModal: true
    });
    // 向服务器发送解除关闭问题的请求
    // wx.request({
    //   url: app.globalData.serverIP + "/api/problem/resolve",
    //   method: "POST",
    //   data: {
    //     problemId: this.data.problem.problemId,
    //     userId: this.data.userInfo.userId
    //   },
    //   success: res => {
    //     console.log(res)
    //     if (res.data.status == 0) {
    //       this.setData({
    //         hideResolveProblemModal: true,
    //         hideCloseProblem: false,
    //       });
    //       this.data.problem.status = 1;
    //     }
    //   }
    // })
  },
  cancelResolveProblem(e) {
    this.setData({
      hideResloveProblemModal: true
    });
  },*/

  /**
   * 生命周期函数--监听页面加载
   */
 

    /*
    wx.getSystemInfo({
      success: res => {

        this.setData({
          swiperBoxHeight: res.windowHeight - res.windowWidth / 750 * 222
        })
      },
    });

    // 获取用户信息
    var userInfoRes = wx.getStorageSync("userInfo");
    if (userInfoRes) {
      this.setData({
        userInfo: userInfoRes
      })
    }

    // 从缓存中获取问题详细信息(problem + answerList)
    var currentProblemDetailRes = wx.getStorageSync("currentProblemDetail"); // 返回的是data
    console.log(currentProblemDetailRes)
    if (currentProblemDetailRes) {
      this.setData({
        problem: currentProblemDetailRes.problem,
        answerList: currentProblemDetailRes.answerList,
        currentUserSameWithProblemOwner: this.data.userInfo.userId == currentProblemDetailRes.problem.userId,
        hideFollowProblem: util.contains(userInfoRes.followProblem, currentProblemDetailRes.problem.problemId),
        hideCloseProblem: currentProblemDetailRes.problem.status == 0,
      });
      if (currentProblemDetailRes.answerList.length != 0) { // 不显示bottom tab bar
        this.setData({
          currentAnswer: currentProblemDetailRes.answerList[0].answer,
          hideStarAnswer: util.contains(currentProblemDetailRes.answerList[0].answer.staredBy, this.data.userInfo.userId),
          currentUserSameWithAnswerOwner: this.data.userInfo.userId == currentProblemDetailRes.answerList[0].answer.user.userId,
        });
      }
    }*/
    
  //},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // 避免在问题详情页面将回答删除完（当回答只有一个，且删除之后，current...Index = -1），再创建新的回答之后新的回答不显示
    /*if(this.data.currentSwiperItemIndex < 0) {
      this.setData({
        currentSwiperItemIndex: 0
      })
    }
    // 重新请求回答列表
    // 重新请求回答列表
    if(this.data.answerList.length != 1){
      console.log("不重新请求回答列表")
      wx.request({
        url: app.globalData.serverIP + "/api/answer/list?problemId=" + this.data.problem.problemId,
        success: res => {
          this.setData({
            answerList: res.data.data
          })
        }
      })
    }*/
     
    
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