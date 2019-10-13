package com.e.word.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;

public class Pdf2word {


    public static void main(String[] args) throws InvalidFormatException {
        try {
            String pdfFileName = "C:\\Users\\cdn\\Desktop\\aa.pdf";
            PDDocument pdf = PDDocument.load(new File(pdfFileName));
            int pageNumber = pdf.getNumberOfPages();

            String docFileName = pdfFileName.substring(0, pdfFileName.lastIndexOf(".")) + ".doc";

            File file = new File(docFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            CustomXWPFDocument document = new CustomXWPFDocument();
            FileOutputStream fos = new FileOutputStream(docFileName);

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

                        File outImgFile1 = new File("d:\\img");
                        if (!outImgFile1.exists()) {
                            outImgFile1.mkdirs();
                        }
                        File outImgFile = new File("d:\\img\\" + System.currentTimeMillis() + ".jpg");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}