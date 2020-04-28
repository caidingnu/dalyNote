import requests from "./requests";


function getPersonInfo(data) {
    return requests({
        url: '/user/',
        method: 'post',
        data
    })
}

export {
    getPersonInfo
}