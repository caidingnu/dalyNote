package com.oceam.controller;


import com.oceam.entity.User;
import com.oceam.entity.UserInfo;
import com.oceam.mapper.UserInfoMapper;
import com.oceam.utils.ExcelExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类功能简述：
 * 〈〉
 *
 * @author caidingnu
 * @create 2019/3/5
 * @since 1.0.0
 */
@RestController
public class TestListexcelOut {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "chu")
    public void name(HttpServletResponse response){
        URL url = ExcelExportUtil.class.getClassLoader().getResource("model/muban.xlsx");
        String templateFileName=url.getPath();
        // 生成的xls全限定名
        String destFileName = "D:\\导出的表.xlsx";
        String excelName="测试用的表.xlsx";

        List<UserInfo> list=new ArrayList<>();
        list=userInfoMapper.getitem();


        // 调用excel工具,生成excel、下载
        new ExcelExportUtil().createExcel(templateFileName, list, destFileName,excelName,response);
    }

}

