// pages/map/map.js
Page({
  data: {
    longitude: 113.324520,
    latitude: 23.099994,
    address:"上海",
    markers: [{
      id: 0,
      iconPath: "../../img/location.png",
      latitude: 23.099994,
      longitude: 113.324520,
      width: 50,
      height: 50
    }]
  },
  onLoad: function () {
    var that = this;
      wx.getLocation({
        type: "gcj02",
        success: function (res) {
          var latitude = res.latitude;
          var longitude = res.longitude;
          //console.log(res.latitude);
          wx.openLocation({
            latitude: res.latitude,
            longitude: res.longitude,
            success:function(res){
              wx.chooseLocation({
                success: function(res) {
                  that.setData({
                    latitude: res.latitude,
                    longitude: res.longitude,
                    address:res.address,
                    markers: [{
                      latitude: res.latitude,
                      longitude: res.longitude
                    }]
                  })
                  //console.log(res.address)
                  wx.setStorageSync('address', res.address)
                  wx.navigateBack({
                    
                  })
                },
              })
            }
          })      
        }
      })
  },
  
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