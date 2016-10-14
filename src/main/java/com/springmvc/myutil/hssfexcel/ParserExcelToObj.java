package com.springmvc.myutil.hssfexcel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParserExcelToObj {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        // List<String> studentString = new ArrayList<String>();
        // studentString.add("110036");
        // studentString.add("LiTao");
        // studentString.add("Male");
        // studentString.add("25");

        try {
            Class<?> clazz = Class.forName("com.springmvc.myutil.hssfexcel.Student");
            Method m = clazz.getDeclaredMethod("parserDate", ArrayList.class);

            InputStream in = new FileInputStream("D:/CSource/VIP_LIST_201610.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);
            List<String> studentString = new ArrayList<String>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                studentString.clear();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    XSSFCell cell1 = row.getCell(j);
                    // 获得每一列中的值
                    studentString.add(getStringValueFromCell(cell1));
                }
                Object object;

                object = m.invoke(clazz.newInstance(), studentString);

                Student student = (Student) object;
                System.out.println(student.getStudentNumber());
                System.out.println(student.getStudentName());
                System.out.println(student.getStudentSex());
                System.out.println(student.getStudentAge());

                in.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String getStringValueFromCell(Cell cell) {
        SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            cellValue = cell.getStringCellValue();
        }

        else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                double d = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(d);
                cellValue = sFormat.format(date);
            } else {
                cellValue = decimalFormat.format((cell.getNumericCellValue()));
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            cellValue = "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            cellValue = "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            cellValue = cell.getCellFormula().toString();
        }
        return cellValue;
    }

}
