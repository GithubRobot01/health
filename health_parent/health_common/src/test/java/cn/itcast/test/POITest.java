package cn.itcast.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class POITest {
    /*@Test
    public void test1() throws Exception {
        //加载指定文件，创建一个excel对象（工作簿）
        XSSFWorkbook excel=new XSSFWorkbook(new FileInputStream(new File("D:\\poi.xlsx")));
        //读取Excel文件中的第一个sheet标签页
        XSSFSheet sheetAt = excel.getSheetAt(0);
        //遍历sheet标签,获得每一行数据
        for (Row row : sheetAt) {
            //遍历行,获得每个单元格数据
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());
            }
        }
        excel.close();
    }

    @Test
    public void test2() throws Exception {
        //加载指定文件，创建一个excel对象（工作簿）
        XSSFWorkbook excel=new XSSFWorkbook(new FileInputStream(new File("D:\\poi.xlsx")));
        //读取Excel文件中的第一个sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获取当前工作表中的最后一个行号,行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            //根据行号获取每一行
            XSSFRow row = sheet.getRow(i);
            //获得当前行最后一个单元格索引
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell=row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }
        }

        excel.close();
    }

    //使用poi向excel文件写入数据
    @Test
    public void test3() throws Exception {
        //在内存中创建一个excel文件(工作簿)
        XSSFWorkbook excel=new XSSFWorkbook();
        //创建一个工作表对象
        XSSFSheet sheet = excel.createSheet("测试");

        //在工作表创建行对象
        XSSFRow title=sheet.createRow(0);
        //在行中创建单元格对象
        title.createCell(0).setCellValue("姓名");
        title.createCell(1).setCellValue("年龄");
        title.createCell(2).setCellValue("地址");

        XSSFRow dataRow=sheet.createRow(1);

        dataRow.createCell(0).setCellValue("wqs");
        dataRow.createCell(1).setCellValue("23");
        dataRow.createCell(2).setCellValue("河南");

        //创建输出流,通过输出流将内存中的excel文件写到磁盘
        FileOutputStream out=new FileOutputStream(new File("D:\\hello.xlsx"));
        excel.write(out);
        out.flush();
        excel.close();
    }*/
}
