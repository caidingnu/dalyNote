import {Message} from 'iview';
// import {Modal} from 'iview';
// 配置API接口地址(加上export其他组件才能使用)
export const serverIp = 'http://127.0.0.1:82';
// 引用axios
const axios = require('axios');

// 自定义判断元素类型JS
function toType(obj) {
    return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
}

// 参数过滤函数
function filterNull(o) {
    for (const key in o) {
        if (o[key] === null) {
            delete o[key]
        }
        if (toType(o[key]) === 'string') {
            o[key] = o[key].trim()
        } else if (toType(o[key]) === 'object') {
            o[key] = filterNull(o[key])
        } else if (toType(o[key]) === 'array') {
            o[key] = filterNull(o[key])
        }
    }
    return o
}


function apiAxios(method, url, params, success, failure) {

    if (params) {
        params = filterNull(params)
    }
    axios({
        headers: {
            // 'Content-Type': 'application/x-www-form-urlencoded'   //设置头信息
        },
        method: method,
        url: url,
        data: method === 'POST' || method === 'PUT' ? params : null,
        params: method === 'GET' || method === 'DELETE' ? params : null,
        baseURL: serverIp,
        withCredentials: true  //此处为false的时候会造成多次请求不是一个会话
    })
        .then((res) => {
            if (res.data.code === "00" || res.data.code === "98") {
                if (success) {
                    success(res.data)
                }
            } else {
                if (failure) {
                    failure(res.data)
                } else {
                    Message.error({
                       content:'服务器错误:' + JSON.stringify(res.data.msg),
                       duration:5
                   });
                }
            }
        })
        .catch((err) => {
            if (err) {
                Message.error({
                    content:'axios请求出错' + err,
                    duration:5
                });
            }
        })
}



// 返回在vue模板中的调用接口
export default {
    get: function (url, params, success, failure) {
        return apiAxios('GET', url, params, success, failure)
    },
    post: function (url, params, success, failure) {
        return apiAxios('POST', url, params, success, failure)
    },
    put: function (url, params, success, failure) {
        return apiAxios('PUT', url, params, success, failure)
    },
    delete: function (url, params, success, failure) {
        return apiAxios('DELETE', url, params, success, failure)
    }
}
