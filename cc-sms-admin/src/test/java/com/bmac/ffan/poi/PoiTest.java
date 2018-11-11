package com.bmac.ffan.poi;

import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class PoiTest {
    public static void main(String[] args) {
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + getDefaultCharSet());
        System.out.print(System.getProperty("file.encoding"));
        try {
            readWorkBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }

    //使用POI读入excel工作簿文件
    public static void readWorkBook() throws Exception {
        // poi读取excel
        //创建要读入的文件的输入流
        InputStream inp = new FileInputStream("C:\\Users\\vicya\\Desktop\\TMP\\lineinfo.xls");

        //根据上述创建的输入流 创建工作簿对象
        Workbook wb = WorkbookFactory.create(inp);
        //得到第一页 sheet
        //页Sheet是从0开始索引的
        Sheet sheet = wb.getSheet("Sheet1");
       // Sheet sheet = wb.getSheetAt(0);
        System.out.println(sheet.getSheetName());
        //利用foreach循环 遍历sheet中的所有行
        for (Row row : sheet) {
            //遍历row中的所有方格
            for (Cell cell : row) {
                //输出方格中的内容，以空格间隔
                System.out.print(cell.toString() + "  ");
            }
            //每一个行输出之后换行
            System.out.println();
        }
        //关闭输入流
        inp.close();
    }
}
