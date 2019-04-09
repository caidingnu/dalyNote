# Spring Boot 实现文件上传下载

文件的上传及下载功能是开发人员在日常应用及编程开发中经常会遇到的。正好最近开发需要用到此功能，虽然本人是 Android 开发人员，但还是业余客串了一下后台开发。

在本文中，您将学习如何使用 Spring Boot 实现 Web 服务中的文件上传和下载功能。首先会构建一个 REST APIs 实现上传及下载的功能，然后使用 Postman 工具来测试这些接口，最后创建一个 Web 界面使用 JavaScript 调用接口演示完整的功能。最终界面及功能如下：

![show_demo.png](https://i.loli.net/2019/03/29/5c9e3f3eeb13c.png)


## 项目环境
    
    - Spring Boot : 2.1.3.RELEASE
    - Gredle : 5.2.1
    - Java : 1.8
    - Intellij IDEA : 2018.3.3

## 项目创建
    
开发环境为 Intellij IDEA，项目创建很简单，按照下面的步骤创建即可：
    
1. File -> New -> Project...
2. 选择 **Spring Initializr**，点击 Next
3. 填写 Group (项目域名) 和 Artifact (项目别名)
4. 构建类型可以选择 Maven 或 Gradle， 看个人习惯
5. 添加 **Web** 依赖
6. 输入项目名称及保存路径，完成创建
    
项目创建完毕之后就可以进行开发，项目的完整结构如下图所示：

![project_structure.png](https://i.loli.net/2019/03/30/5c9e436fee3ca.png)

## 参数配置

项目创建完成之后，需要设置一些必要的参数，打开项目`resources`目录下配置文件`application.properties`，在其中添加以下参数：

```
server.port=80

## MULTIPART (MultipartProperties)
# 开启 multipart 上传功能
spring.servlet.multipart.enabled=true
# 文件写入磁盘的阈值
spring.servlet.multipart.file-size-threshold=2KB
# 最大文件大小
spring.servlet.multipart.max-file-size=200MB
# 最大请求大小
spring.servlet.multipart.max-request-size=215MB

## 文件存储所需参数
# 所有通过 REST APIs 上传的文件都将存储在此目录下
file.upload-dir=./uploads
```

其中`file.upload-dir=./uploads`参数为自定义的参数，创建`FileProperties.java`POJO类，使配置参数可以自动绑定到POJO类。

```java
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
```

然后在`@SpringBootApplication`注解的类中添加`@EnableConfigurationProperties`注解以开启`ConfigurationProperties`功能。

`SpringBootFileApplication.java`

```java
@SpringBootApplication
@EnableConfigurationProperties({
        FileProperties.class
})
public class SpringBootFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFileApplication.class, args);
    }
}
```

配置完成，以后若有`file`前缀开头的参数需要配置，可直接在`application.properties`配置文件中配置并更新`FileProperties.java`即可。

另外再创建一个上传文件成功之后的`Response`响应实体类`UploadFileResponse.java`及异常类`FileException.java`来处理异常信息。

`UploadFileResponse.java`

```java
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
    // getter and setter ...
}
```

`FileException.java`

```java
public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

## 创建接口

下面需要创建文件上传下载所需的 REST APIs 接口。创建文件`FileController.java`。

```java
import com.james.sample.file.dto.UploadFileResponse;
import com.james.sample.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
```

`FileController`类在接收到用户的请求后，使用`FileService`类提供的`storeFile()`方法将文件写入到系统中进行存储，其存储目录就是之前在`application.properties`配置文件中的`file.upload-dir`参数的值`./uploads`。

下载接口`downloadFile()`在接收到用户请求之后，使用`FileService`类提供的`loadFileAsResource()`方法获取存储在系统中文件并返回文件供用户下载。

`FileService.java`

```java
import com.james.sample.file.exception.FileException;
import com.james.sample.file.property.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path fileStorageLocation; // 文件在本地存储的地址

    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
}
```

## 接口测试

在完成上述的代码之后，打开`SpringBootFileApplication.java`并运行，运行完成之后就可以使用 Postman 进行测试了。

单个文件上传结果：

![upload_result1.png](https://i.loli.net/2019/03/30/5c9e4dd9ded46.png)

多个文件上传结果：

![upload_result2.png](https://i.loli.net/2019/03/30/5c9e4dda0c477.png)

文件下载结果：

![download_result.png](https://i.loli.net/2019/03/30/5c9e4dda130d6.png)

## Web 前端开发

`index.html`

```html
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Spring Boot File Upload / Download Rest API Example</title>

    <!-- Bootstrap CSS -->
    <link href="/css/main.css" rel="stylesheet"/>
</head>
<body>

<noscript>
    <h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>

<div class="upload-container">
    <div class="upload-header">
        <h2>Spring Boot File Upload / Download Rest API Example</h2>
    </div>
    <div class="upload-content">
        <div class="single-upload">
            <h3>Upload Single File</h3>
            <form id="singleUploadForm" name="singleUploadForm">
                <input id="singleFileUploadInput" type="file" name="file" class="file-input" required/>
                <button type="submit" class="primary submit-btn">Submit</button>
            </form>
            <div class="upload-response">
                <div id="singleFileUploadError"></div>
                <div id="singleFileUploadSuccess"></div>
            </div>
        </div>
        <div class="multiple-upload">
            <h3>Upload Multiple Files</h3>
            <form id="multipleUploadForm" name="multipleUploadForm">
                <input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required/>
                <button type="submit" class="primary submit-btn">Submit</button>
            </form>
            <div class="upload-response">
                <div id="multipleFileUploadError"></div>
                <div id="multipleFileUploadSuccess"></div>
            </div>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<script src="/js/main.js"></script>
</body>
</html>
```

`main.css`

```css
* {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

body {
    margin: 0;
    padding: 0;
    font-weight: 400;
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 1rem;
    line-height: 1.58;
    color: #333;
    background-color: #f4f4f4;
}

body:before {
    height: 50%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    background: #128ff2;
    content: "";
    z-index: 0;
}

.clearfix:after {
    display: block;
    content: "";
    clear: both;
}


h1, h2, h3, h4, h5, h6 {
    margin-top: 20px;
    margin-bottom: 20px;
}

h1 {
    font-size: 1.7em;
}

a {
    color: #128ff2;
}

button {
    box-shadow: none;
    border: 1px solid transparent;
    font-size: 14px;
    outline: none;
    line-height: 100%;
    white-space: nowrap;
    vertical-align: middle;
    padding: 0.6rem 1rem;
    border-radius: 2px;
    transition: all 0.2s ease-in-out;
    cursor: pointer;
    min-height: 38px;
}

button.primary {
    background-color: #128ff2;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
    color: #fff;
}

input {
    font-size: 1rem;
}

input[type="file"] {
    border: 1px solid #128ff2;
    padding: 6px;
    max-width: 100%;
}

.file-input {
    width: 100%;
}

.submit-btn {
    display: block;
    margin-top: 15px;
    min-width: 100px;
}

@media screen and (min-width: 500px) {
    .file-input {
        width: calc(100% - 115px);
    }

    .submit-btn {
        display: inline-block;
        margin-top: 0;
        margin-left: 10px;
    }
}

.upload-container {
    max-width: 700px;
    margin-left: auto;
    margin-right: auto;
    background-color: #fff;
    box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
    margin-top: 60px;
    min-height: 400px;
    position: relative;
    padding: 20px;
}

.upload-header {
    border-bottom: 1px solid #ececec;
}

.upload-header h2 {
    font-weight: 500;
}

.single-upload {
    padding-bottom: 20px;
    margin-bottom: 20px;
    border-bottom: 1px solid #e8e8e8;
}

.upload-response {
    overflow-x: hidden;
    word-break: break-all;
}
```

`main.js`

```javascript
'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

function uploadMultipleFiles(files) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadMultipleFiles");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p>All Files Uploaded Successfully</p>";
            for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        } else {
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}


singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);


multipleUploadForm.addEventListener('submit', function(event){
    var files = multipleFileUploadInput.files;
    if(files.length === 0) {
        multipleFileUploadError.innerHTML = "Please select at least one file";
        multipleFileUploadError.style.display = "block";
    }
    uploadMultipleFiles(files);
    event.preventDefault();
}, true);
```

## 总结

至此，文件的上传及下载功能已完成。在正式环境中可能还需要将上传的文件存储到数据库，此处按照实际需求去处理即可。

本文源代码地址：[https://github.com/JemGeek/SpringBoot-Sample/tree/master/SpringBoot-File](https://github.com/JemGeek/SpringBoot-Sample/tree/master/SpringBoot-File)

本文参考(需要翻墙)：[https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/](https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/)