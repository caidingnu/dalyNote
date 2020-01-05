//常规用法
laydate.render({
  elem: '#test1'
});


setInterval("ss()",1000);

var x=0;
function ss(){
	var myDate = new Date();
//获取当前年
var year=myDate.getFullYear();
//获取当前月
var month=myDate.getMonth()+1;
//获取当前日
var date=myDate.getDate(); 
var h=myDate.getHours();       //获取当前小时数(0-23)
var m=myDate.getMinutes();     //获取当前分钟数(0-59)
var s=myDate.getSeconds();  
$("#test1").text(year+" "+month+" "+date+"  "+h+":"+m+":"+s)
	
}
