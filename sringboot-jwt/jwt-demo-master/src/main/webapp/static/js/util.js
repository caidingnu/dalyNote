//获取url中的参数
var request = {
    getUrlParam: function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    },
}

function saveToken(xhr) {
    localStorage.setItem("token", xhr.getResponseHeader("Authorization"));
}

function getToken() {
    return localStorage.getItem("token");
}
