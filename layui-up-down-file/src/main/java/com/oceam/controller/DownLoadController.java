package com.oceam.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件下载
 * 〈〉
 *
 * @author caidingnu
 * @create 2019/2/14
 * @since 1.0.0
 */

@RestController
public class DownLoadController {

    @RequestMapping("download")
    public String downLoad(HttpServletResponse response){
        String filename="IDcard.jpg";
        String filePath = "F:/test" ;
        String outpath="F:/download";
        FileInputStream fis= null;


        try {
            fis = new FileInputStream(filePath + "/" + filename);
            BufferedInputStream bInputStream=new BufferedInputStream(fis);

            File file=new File(outpath);
            if (!file.exists()){
                file.mkdirs();
            }



            File file1=new File(file+"/"+filename);
            FileOutputStream fos=new FileOutputStream(file1);
            BufferedOutputStream bf=new BufferedOutputStream(fos);

            int tem=-1;
            while ((tem=bInputStream.read()) !=-1) {
                bf.write(tem);
                System.out.println("----");
            }
            bf.flush();
            fis.close();
            bInputStream.close();
            bf.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "下载成功";
    }







    }
