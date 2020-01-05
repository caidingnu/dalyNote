
$(function(){
		$("#dianji").on("click",ok)
	})
	
function ok(){
layer.confirm('您是如何看待前端开发？', {
  btn: ['重要','奇葩'] //按钮
}, function(){
	
  $("#test1").text("");
}, function(){
  layer.msg('也可以这样', {
    time: 20000, //20s后自动关闭
    btn: ['明白了', '知道了']
  });
});
}


