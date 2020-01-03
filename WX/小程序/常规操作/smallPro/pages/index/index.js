//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    status: true,
    test: "测试",
    formData: {},
    name: '试试',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function() {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  clickMe: function() {
    console.log(111)
  },
  formSubmit: function(re) {
    this.data.formData = re.detail.value;
    console.log(re.detail.value)
    wx.request({
      // 发送本地请求必须关闭域名有效性验证
      url: 'http://192.168.1.3:80/wx',
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: re.detail.value,
      success: function(resp) {
        console.log(resp)
        if (resp.statusCode == 200) {
          wx.showToast({
            title: '登录成功',
            // icon:"loading",
            icon: "success",
            // 时间
            duration: 2000
          })
        }
      },
      fail: function() {
        console.log("error")
      }
    })
  },
  to: function() {
    // 页面跳转
    wx.navigateTo({
      url: '../bb/bb?name=传递的参数',
    })
  },
  getData: function(e) {
    console.log(e)
    this.data.test = e.currentTarget.dataset.ccc;
    this.setData(this.data)
  },
  /**
 * 用户点击右上角分享
 */
  onShareAppMessage: function () {
    wx.showToast({
      title: '分享',
    })
  },
  toList:function(){
    wx.navigateTo({
      url: '../list/list',
    })
  }
})