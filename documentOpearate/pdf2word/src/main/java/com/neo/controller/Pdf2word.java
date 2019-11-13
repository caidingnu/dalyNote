package com.neo.controller;

import com.neo.pdf.CustomXWPFDocument;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

@Component
public class Pdf2word {

    @Value("${filePath}")
    private String UPLOADED_FOLDER;

    @RequestMapping("pdf2word")
    public    String pdf2word(String pdfFileName) throws InvalidFormatException, UnknownHostException {
        String docFileName="";
        try {
//             pdfFileName = "C:\\Users\\cdn\\Desktop\\aa.pdf";
            PDDocument pdf = PDDocument.load(new File(UPLOADED_FOLDER+pdfFileName));
            int pageNumber = pdf.getNumberOfPages();
             docFileName = pdfFileName.substring(0, pdfFileName.lastIndexOf(".")) + ".doc";
            File file = new File(UPLOADED_FOLDER+docFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            CustomXWPFDocument document = new CustomXWPFDocument();
            FileOutputStream fos = new FileOutputStream(UPLOADED_FOLDER+docFileName);

            //提取每一页的图片和文字，添加到 word 中
            for (int i = 0; i < pageNumber; i++) {

                PDPage page = pdf.getPage(i);
                PDResources resources = page.getResources();

                Iterable<COSName> names = resources.getXObjectNames();
                Iterator<COSName> iterator = names.iterator();
                while (iterator.hasNext()) {
                    COSName cosName = iterator.next();

                    if (resources.isImageXObject(cosName)) {
                        PDImageXObject imageXObject = (PDImageXObject) resources.getXObject(cosName);

                        File outImgFile1 = new File(UPLOADED_FOLDER+"\\img");
                        if (!outImgFile1.exists()) {
                            outImgFile1.mkdirs();
                        }
                        File outImgFile = new File(UPLOADED_FOLDER+"\\img\\" + System.currentTimeMillis() + ".jpg");
                        outImgFile.createNewFile();
                        Thumbnails.of(imageXObject.getImage()).scale(0.6).rotate(0).toFile(outImgFile);
                        BufferedImage bufferedImage = ImageIO.read(outImgFile);
                        int width = bufferedImage.getWidth();
                        int height = bufferedImage.getHeight();
                        if (width > 600) {
                            double ratio = Math.round((double) width / 550.0);
                            System.out.println("缩放比ratio：" + ratio);
                            width = (int) (width / ratio);
                            height = (int) (height / ratio);

                        }

                        System.out.println("width: " + width + ",  height: " + height);
                        FileInputStream in = new FileInputStream(outImgFile);
                        byte[] ba = new byte[in.available()];
                        in.read(ba);
                        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(ba);

                        XWPFParagraph picture = document.createParagraph();
                        //添加图片
                        document.addPictureData(byteInputStream, CustomXWPFDocument.PICTURE_TYPE_JPEG);
                        //图片大小、位置
                        document.createPicture(document.getAllPictures().size() - 1, width, height, picture);
                    }
                }

                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setSortByPosition(true);
                stripper.setStartPage(i);
                stripper.setEndPage(i);
                //当前页中的文字
                String text = stripper.getText(pdf);
//                    System.out.println(text+"---");
                String[] strings = text.split("\r");
                for (String string : strings) {
                    XWPFParagraph textParagraph = document.createParagraph();
//                textParagraph.setAlignment(ParagraphAlignment.CENTER);//对齐方式
//                    textParagraph.setFirstLineIndent(2);
                    XWPFRun textRun = textParagraph.createRun();
                    textRun.setFontFamily("微软雅黑");
                    textRun.setFontSize(11);
                    textRun.setText(string);
//                        System.out.println(string+"------------");
                }
            }
            document.write(fos);
            fos.close();
            pdf.close();
            System.out.println("pdf转换解析结束！！----");
            System.out.println(docFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://pdf.ynntwl.com"+"/app/"+docFileName;
    }

    @RequestMapping("download")
    public  String responseIo(HttpServletResponse response, String fileName) {
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            File file = new File(UPLOADED_FOLDER+fileName);
            if (!file.exists()) {
                return "文件不存在";
            }
            String pdf2word = pdf2word(file.toString());
            response.setContentType("octets/stream");
//        设置编码，避免文件名中文乱码
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("aa".getBytes("gb2312"), "ISO8859-1") + ".doc");
            fileInputStream = new FileInputStream(new File(pdf2word));
            outputStream = response.getOutputStream();
            int a = -1;
            while ((a = fileInputStream.read()) != -1) {
                outputStream.write(a);
            }
            outputStream.flush();
        } catch (IOException | InvalidFormatException e) {
            System.out.println("取消下载");
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "下载成功";
    }


  /**
   * desc:
   * param:
   * author: CDN
   * date: 2019/10/13
   */
  @Scheduled(cron = "0 0 3 * * ?")
  public void  delpath(){
      deleteDir("");
  }


    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */

    public static void deleteDir(String dirPath) {
        File file = new File(" c:/upload/");
        System.out.println(file.getName());
        if (file.isFile()) {
            System.out.println(file.delete());
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }
}