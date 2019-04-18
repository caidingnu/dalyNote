$(function () {


    //单文件上传
    $("#singleUploadForm").ajaxForm(function (data) {
        console.log(data)
        $("#singleFileUploadSuccess").css("display", "block");
        var url = '<a href="' + data.fileDownloadUri + '">下载</a>'
        $("#singleFileUploadSuccess").text("上传成功：" + url)
         $("#review").attr("src",url)
    });

//多文件上传
    $("#multipleUploadForm").ajaxForm(function (data) {
        console.log(data)
        $("#multipleFileUploadSuccess").css("display", "block");
        var url = '<a href="' + data.fileDownloadUri + '">下载</a>'
        $("#multipleFileUploadSuccess").text("上传成功：" + url)

    });

})



