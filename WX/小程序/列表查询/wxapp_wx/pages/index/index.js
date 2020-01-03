Page({
  onShareAppMessage() {
    return {
      title: 'swiper',
      path: 'page/component/pages/swiper/swiper'
    }
  },
  data: {
    background: ['../img/1.jpg', 'demo-text-2', 'demo-text-3'],
    indicatorDots: true,
    vertical: false,
    autoplay: true,
    interval: 5000,
    duration: 5000,
    news:[],
    cc:[]
  },
  
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  /**
     * 生命周期函数--监听页面加载
     */
  onLoad: function (options) {
    this.list()
  },
  list:function(){
    var _this=this;
    wx.request({
      url: 'http://127.0.0.1:81/news/page',
      data: {

      },
      header: {
        // 'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        if (res.data.code == "00") {
          console.log(res.data.data.records)
          _this.setData({
            news: res.data.data.records
          })
        }
        console.log(_this.data.news)
      },
      fail: function () {
        console.log("error")
      }
    })
  },
  changeIndicatorDots() {
    this.setData({
      indicatorDots: !this.data.indicatorDots
    })
  },

  changeAutoplay() {
    this.setData({
      autoplay: !this.data.autoplay
    })
  },

  intervalChange(e) {
    this.setData({
      interval: e.detail.value
    })
  },

  durationChange(e) {
    this.setData({
      duration: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // this.cc=[1]
    
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

  },
  tan:function(event){
    console.log(event)
    wx.showToast({
      // 该处获取传递来的参数
      title: event.currentTarget.dataset.title,
    })
  }
})