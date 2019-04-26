package com.neo.controller;

import com.neo.controller.utils.BaseResponseEntity;
import com.neo.controller.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {

    @Value("${filePath}")
    private  String UPLOADED_FOLDER;

    @Value("${serverIp}")
    private  String IP;




    //    单文件上传
    @PostMapping(value = "/upload")
    @ResponseBody
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, String> map = new HashMap<>();
        if (file.isEmpty()) {
            map.put("data", "文件空");
            return JsonUtil.toJSon(new BaseResponseEntity(map));
        }
        if (!new File(UPLOADED_FOLDER).exists()) {
            new File(UPLOADED_FOLDER).mkdir();
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(file.getOriginalFilename());
            map.put("data", file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonUtil.toJSon(new BaseResponseEntity(map));
    }


    //多文件上传
    @PostMapping("/many")
    @ResponseBody
    public String singleFileUpload2(@RequestParam("files") MultipartFile[] files) {
        Map<String, String> map = new HashMap<>();
        if (files.length == 0) {
            map.put("data", "文件空");
            return JsonUtil.toJSon(new BaseResponseEntity(map));
        }
        if (!new File(UPLOADED_FOLDER).exists()) {
            new File(UPLOADED_FOLDER).mkdir();
        }
        List<String> list=new ArrayList<>();
        try {
            for (int i = 0; i < files.length; i++) {
                byte[] bytes = files[i].getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + files[i].getOriginalFilename());
                Files.write(path, bytes);
                list.add(files[i].getOriginalFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonUtil.toJSon(new BaseResponseEntity(list,list.size()));
    }

}